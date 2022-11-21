package co.touchlab.kmmbridgekickstart.repository

import co.touchlab.kmmbridgekickstart.BreedAnalytics
import co.touchlab.kmmbridgekickstart.DatabaseHelper
import co.touchlab.kmmbridgekickstart.db.Breed
import co.touchlab.kmmbridgekickstart.ktor.DogApi
import com.russhwolf.settings.Settings
import kotlinx.coroutines.flow.Flow
import kotlinx.datetime.Clock

class BreedRepository internal constructor(
    private val dbHelper: DatabaseHelper,
    private val settings: Settings,
    private val dogApi: DogApi,
    private val clock: Clock,
    private val breedAnalytics: BreedAnalytics
) {

    companion object {
        internal const val DB_TIMESTAMP_KEY = "DbTimestampKey"
    }

    fun getBreeds(): Flow<List<Breed>> = dbHelper.selectAllItems()

    suspend fun refreshBreedsIfStale() {
        if (isBreedListStale()) {
            refreshBreeds()
        }
    }

    suspend fun refreshBreeds() {
        val breedResult = dogApi.getJsonFromApi()
        val breedList = breedResult.message.keys.sorted().toList()
        breedAnalytics.breedsFetchedFromNetwork(breedList.size)
        settings.putLong(DB_TIMESTAMP_KEY, clock.now().toEpochMilliseconds())

        if (breedList.isNotEmpty()) {
            dbHelper.insertBreeds(breedList)
        }
    }

    suspend fun updateBreedFavorite(breed: Breed) {
        dbHelper.updateFavorite(breed.id, !breed.favorite)
    }

    private fun isBreedListStale(): Boolean {
        val lastDownloadTimeMS = settings.getLong(DB_TIMESTAMP_KEY, 0)
        val oneHourMS = 60 * 60 * 1000
        val stale = lastDownloadTimeMS + oneHourMS < clock.now().toEpochMilliseconds()
        if (!stale) {
            breedAnalytics.breedsNotFetchedFromNetwork("Recently updated")
        }
        return stale
    }
}
