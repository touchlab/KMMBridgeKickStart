package co.touchlab.brownfieldsdk

import co.touchlab.brownfieldsdk.ktor.DogApi
import co.touchlab.brownfieldsdk.ktor.DogApiImpl
import co.touchlab.brownfieldsdk.repository.BreedRepository
import com.russhwolf.settings.Settings
import com.squareup.sqldelight.db.SqlDriver
import io.ktor.client.engine.HttpClientEngine
import kotlinx.coroutines.Dispatchers
import kotlinx.datetime.Clock

abstract class BaseServiceLocator : ServiceLocator {

    override val breedRepository: BreedRepository by lazy {
        BreedRepository(
            dbHelper = databaseHelper,
            settings = settings,
            dogApi = dogApi,
            clock = Clock.System,
            breedAnalytics = breedAnalytics
        )
    }

    private val databaseHelper: DatabaseHelper by lazy {
        DatabaseHelper(sqlDriver = sqlDriver, backgroundDispatcher = Dispatchers.Default, breedAnalytics)
    }

    private val dogApi: DogApi by lazy {
        DogApiImpl(engine = clientEngine, httpClientAnalytics, breedAnalytics)
    }

    protected abstract val sqlDriver: SqlDriver
    protected abstract val settings: Settings
    protected abstract val clientEngine: HttpClientEngine
}
