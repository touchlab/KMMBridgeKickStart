package co.touchlab.kmmbridgekickstart.ktor

import co.touchlab.kmmbridgekickstart.response.BreedResult

internal interface DogApi {
    suspend fun getJsonFromApi(): BreedResult
}
