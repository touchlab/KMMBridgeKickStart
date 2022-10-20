package co.touchlab.kmmbridgekickstart

import co.touchlab.kmmbridgekickstart.ktor.DogApiImpl
import co.touchlab.kmmbridgekickstart.response.BreedResult
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.plugins.ClientRequestException
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

@OptIn(ExperimentalCoroutinesApi::class)
class DogApiTest {
    @Test
    fun success() = runTest {
        val engine = MockEngine {
            assertEquals("https://dog.ceo/api/breeds/list/all", it.url.toString())
            respond(
                content = """{"message":{"affenpinscher":[],"african":["shepherd"]},"status":"success"}""",
                headers = headersOf(HttpHeaders.ContentType, ContentType.Application.Json.toString())
            )
        }
        val dogApi = DogApiImpl(engine)

        val result = dogApi.getJsonFromApi()
        assertEquals(
            BreedResult(
                mapOf(
                    "affenpinscher" to emptyList(),
                    "african" to listOf("shepherd")
                ),
                "success"
            ),
            result
        )
    }

    @Test
    fun failure() = runTest {
        val engine = MockEngine {
            respond(
                content = "",
                status = HttpStatusCode.NotFound
            )
        }
        val dogApi = DogApiImpl(engine)

        assertFailsWith<ClientRequestException> {
            dogApi.getJsonFromApi()
        }
    }
}
