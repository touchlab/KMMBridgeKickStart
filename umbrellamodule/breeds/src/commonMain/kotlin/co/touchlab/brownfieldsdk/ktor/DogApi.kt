package co.touchlab.brownfieldsdk.ktor

import co.touchlab.brownfieldsdk.response.BreedResult

interface DogApi {
    suspend fun getJsonFromApi(): BreedResult
}
