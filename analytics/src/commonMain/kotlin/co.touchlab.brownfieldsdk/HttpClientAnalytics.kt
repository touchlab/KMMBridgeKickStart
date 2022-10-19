package co.touchlab.brownfieldsdk

class HttpClientAnalytics(private val analytics: Analytics) {
    fun logMessage(message: String) {
        analytics.sendEvent("httpClientMessage", "message" to message)
    }
}