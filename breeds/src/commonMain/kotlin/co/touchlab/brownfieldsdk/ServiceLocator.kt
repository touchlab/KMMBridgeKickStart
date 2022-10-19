package co.touchlab.brownfieldsdk

import co.touchlab.brownfieldsdk.repository.BreedRepository

interface ServiceLocator {
    val breedRepository: BreedRepository
}
