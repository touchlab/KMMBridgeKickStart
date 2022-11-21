package co.touchlab.kmmbridgekickstart

import android.content.Context

fun startSDK(analytics: Analytics, context: Context): SDKHandle {
    val analyticsHandle = initAnalytics(analytics)
    val breedRepository = breedStartup(context, analyticsHandle)
    return SDKHandle(
        breedRepository = breedRepository,
        appAnalytics = analyticsHandle.appAnalytics,
        breedAnalytics = analyticsHandle.breedAnalytics
    )
}