package co.touchlab.brownfieldsdk

import co.touchlab.brownfieldsdk.db.Breed
import co.touchlab.brownfieldsdk.db.BrownfieldSdkDb
import co.touchlab.brownfieldsdk.sqldelight.transactionWithContext
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

internal class DatabaseHelper(
    sqlDriver: SqlDriver,
    private val backgroundDispatcher: CoroutineDispatcher
) {
    private val dbRef: BrownfieldSdkDb = BrownfieldSdkDb(sqlDriver)

    fun selectAllItems(): Flow<List<Breed>> =
        dbRef.tableQueries
            .selectAll()
            .asFlow()
            .mapToList()
            .flowOn(backgroundDispatcher)

    suspend fun insertBreeds(breeds: List<String>) {
        // log.d { "Inserting ${breeds.size} breeds into database" }
        dbRef.transactionWithContext(backgroundDispatcher) {
            breeds.forEach { breed ->
                dbRef.tableQueries.insertBreed(breed)
            }
        }
    }

    fun selectById(id: Long): Flow<List<Breed>> =
        dbRef.tableQueries
            .selectById(id)
            .asFlow()
            .mapToList()
            .flowOn(backgroundDispatcher)

    suspend fun deleteAll() {
        // log.i { "Database Cleared" }
        dbRef.transactionWithContext(backgroundDispatcher) {
            dbRef.tableQueries.deleteAll()
        }
    }

    suspend fun updateFavorite(breedId: Long, favorite: Boolean) {
        // log.i { "Breed $breedId: Favorited $favorite" }
        dbRef.transactionWithContext(backgroundDispatcher) {
            dbRef.tableQueries.updateFavorite(favorite, breedId)
        }
    }
}
