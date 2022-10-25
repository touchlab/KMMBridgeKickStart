package co.touchlab.kmmbridgekickstart

import co.touchlab.kmmbridgekickstart.repository.BreedRepository

internal interface ServiceLocator {
    val breedRepository: BreedRepository
}
