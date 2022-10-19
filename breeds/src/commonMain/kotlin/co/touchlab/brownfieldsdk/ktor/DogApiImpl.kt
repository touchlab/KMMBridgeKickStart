package co.touchlab.brownfieldsdk.ktor

import co.touchlab.brownfieldsdk.BreedAnalytics
import co.touchlab.brownfieldsdk.HttpClientAnalytics
import co.touchlab.brownfieldsdk.response.BreedResult
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*

internal class DogApiImpl(
    engine: HttpClientEngine,
    private val httpClientAnalytics: HttpClientAnalytics,
    private val breedAnalytics: BreedAnalytics,
) : DogApi {

    private val client = HttpClient(engine) {
        expectSuccess = true
        install(ContentNegotiation) {
            json()
        }
        install(Logging) {
            logger = object : Logger {
                override fun log(message: String) {
                    httpClientAnalytics.logMessage(message)
                }
            }

            level = LogLevel.INFO
        }
        install(HttpTimeout) {
            val timeout = 30000L
            connectTimeoutMillis = timeout
            requestTimeoutMillis = timeout
            socketTimeoutMillis = timeout
        }
    }

    override suspend fun getJsonFromApi(): BreedResult {
        breedAnalytics.fetchingBreedsFromNetwork()
        return client.get {
            dogs("api/breeds/list/all")
        }.body()
    }

    private fun HttpRequestBuilder.dogs(path: String) {
        url {
            takeFrom("https://dog.ceo/")
            encodedPath = path
        }
    }
}
