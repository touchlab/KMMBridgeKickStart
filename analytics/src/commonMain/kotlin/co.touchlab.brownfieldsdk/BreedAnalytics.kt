package co.touchlab.brownfieldsdk

class BreedAnalytics(private val analytics: Analytics) {

    fun favoriteClicked(id: Long) {
        analytics.sendEvent("favoriteClicked", "favoriteId" to id)
    }

    fun favoriteSaved(id: Long, favorite: Boolean) {
        analytics.sendEvent("favoriteSaved", "favoriteId" to id, "favoriteValue" to favorite)
    }

    fun displayingBreeds(size: Int) {
        analytics.sendEvent("viewUpdatingWithBreeds", "size" to size)
    }

    fun displayingError(message: String) {
        analytics.sendEvent("viewDisplayingError", "message" to message)
    }

    fun refreshingBreeds() {
        analytics.sendEvent("refreshingBreeds")
    }

    fun updatingBreedsError(throwable: Throwable) {
        analytics.sendEvent("errorDownloadingBreedList", "throwable" to throwable)
    }

    fun clearingBreedViewModel() {
        analytics.sendEvent("clearingBreedViewModel")
    }

    fun fetchingBreedsFromNetwork() {
        analytics.sendEvent("breedsFetching")
    }

    fun breedsFetchedFromNetwork(size: Int) {
        analytics.sendEvent("breedsFetched", "size" to size)
    }

    fun breedsNotFetchedFromNetwork(reason: String) {
        analytics.sendEvent("breedsNotFetchedFromNetwork", "reason" to reason)
    }

    fun insertingBreedsToDatabase(size: Int) {
        analytics.sendEvent("insertingBreedsToDatabase", "size" to size)
    }

    fun databaseCleared() {
        analytics.sendEvent("breedDatabaseCleared")
    }
}