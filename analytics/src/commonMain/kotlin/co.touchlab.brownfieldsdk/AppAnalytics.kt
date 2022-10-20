package co.touchlab.brownfieldsdk

object AppAnalytics {
    
    fun appStarted() {
        sendEvent("appStarted")
    }
}