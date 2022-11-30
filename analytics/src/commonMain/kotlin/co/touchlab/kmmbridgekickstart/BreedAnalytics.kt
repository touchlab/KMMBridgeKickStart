package co.touchlab.kmmbridgekickstart

class BreedAnalytics internal constructor() {

    fun favoriteClicked(id: Long) {
        sendEvent("favoriteClicked", "favoriteId" to id)
    }

    fun favoriteSaved(id: Long, favorite: Boolean) {
        sendEvent("favoriteSaved", "favoriteId" to id, "favoriteValue" to favorite)
    }

    fun displayingBreeds(size: Int) {
        sendEvent("viewUpdatingWithBreeds", "size" to size)
    }

    fun displayingError(message: String) {
        sendEvent("viewDisplayingError", "message" to message)
    }

    fun refreshingBreeds() {
        sendEvent("refreshingBreeds")
    }

    fun updatingBreedsError(throwable: Throwable) {
        sendEvent("errorDownloadingBreedList", "throwable" to throwable)
    }

    fun clearingBreedViewModel() {
        sendEvent("clearingBreedViewModel")
    }

    fun fetchingBreedsFromNetwork() {
        sendEvent("breedsFetching")
    }

    fun breedsFetchedFromNetwork(size: Int) {
        sendEvent("breedsFetched", "size" to size)
    }

    fun breedsNotFetchedFromNetwork(reason: String) {
        sendEvent("breedsNotFetchedFromNetwork", "reason" to reason)
    }

    fun insertingBreedsToDatabase(size: Int) {
        sendEvent("insertingBreedsToDatabase", "size" to size)
    }

    fun databaseCleared() {
        sendEvent("breedDatabaseCleared")
    }
}