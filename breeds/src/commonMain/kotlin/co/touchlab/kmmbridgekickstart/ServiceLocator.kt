package co.touchlab.kmmbridgekickstart

import co.touchlab.kmmbridgekickstart.repository.BreedRepository

interface ServiceLocator {
    val breedRepository: BreedRepository
}
