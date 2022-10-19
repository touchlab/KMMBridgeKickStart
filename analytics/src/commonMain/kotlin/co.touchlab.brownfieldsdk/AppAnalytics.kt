package co.touchlab.brownfieldsdk

class AppAnalytics(private val analytics: Analytics) {
    fun appStarted() {
        analytics.sendEvent("appStarted")
    }
}