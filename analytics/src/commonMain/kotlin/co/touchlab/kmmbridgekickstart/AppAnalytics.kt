package co.touchlab.kmmbridgekickstart

class AppAnalytics internal constructor() {
    
    fun appStarted() {
        sendEvent("appStarted")
    }
}