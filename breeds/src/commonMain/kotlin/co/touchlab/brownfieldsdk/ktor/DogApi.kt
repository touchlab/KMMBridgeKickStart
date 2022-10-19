package co.touchlab.brownfieldsdk.ktor

import co.touchlab.brownfieldsdk.response.BreedResult

internal interface DogApi {
    suspend fun getJsonFromApi(): BreedResult
}
