package co.touchlab.brownfieldsdk.response

import kotlinx.serialization.Serializable

@Serializable
data class BreedResult(
    val message: Map<String, List<String>>,
    var status: String
)
