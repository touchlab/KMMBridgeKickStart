package co.touchlab.kmmbridgekickstart

import co.touchlab.kmmbridgekickstart.repository.BreedRepository

internal interface ServiceLocator {
    val breedRepository: BreedRepository
    val appAnalytics: AppAnalytics
    val breedAnalytics: BreedAnalytics
    val httpClientAnalytics: HttpClientAnalytics
}
