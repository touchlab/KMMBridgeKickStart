package co.touchlab.brownfieldsdk

import co.touchlab.brownfieldsdk.repository.BreedRepository

interface ServiceLocator {
    val breedRepository: BreedRepository

    val appAnalytics: AppAnalytics

    val httpClientAnalytics: HttpClientAnalytics

    val breedAnalytics: BreedAnalytics
}
