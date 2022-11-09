package co.touchlab.kmmbridgekickstart

fun startSDK(analytics: Analytics): SDKHandle {
    initAnalytics(analytics)
    return SDKHandle(breedRepository = CallbackBreedRepository(breedRepository = breedStartup()))
}