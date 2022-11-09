package co.touchlab.kmmbridgekickstart

object AppAnalytics {
    fun appStarted() {
        sendEvent("appStarted")
    }
}