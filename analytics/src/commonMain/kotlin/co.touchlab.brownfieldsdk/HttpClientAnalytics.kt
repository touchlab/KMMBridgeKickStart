package co.touchlab.brownfieldsdk

object HttpClientAnalytics {
    
    fun logMessage(message: String) {
        sendEvent("httpClientMessage", "message" to message)
    }
}