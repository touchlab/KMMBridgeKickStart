package co.touchlab.kmmbridgekickstart

object HttpClientAnalytics {
    fun logMessage(message: String) {
        sendEvent("httpClientMessage", "message" to message)
    }
}