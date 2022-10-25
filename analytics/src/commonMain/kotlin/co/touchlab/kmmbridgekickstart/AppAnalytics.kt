package co.touchlab.kmmbridgekickstart

internal object AppAnalytics {
    fun appStarted() {
        sendEvent("appStarted")
    }
}