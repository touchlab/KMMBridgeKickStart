package co.touchlab.brownfieldsdk

import kotlinx.datetime.Clock

interface Analytics {
    fun sendEvent(eventName: String, vararg eventArgs: Pair<String, Any>)
}

fun initAnalytics(analytics: Analytics) : Analytics {
    return object : Analytics {
        override fun sendEvent(eventName: String, vararg eventArgs: Pair<String, Any>) {
            // add a standard set of args to the ones supplied by the client, then, delegate the call
            val additionalArgs = mutableListOf<Pair<String, Any>>().apply {
                addAll(eventArgs)
                add("_timestamp" to Clock.System.now().toEpochMilliseconds())
            }

            analytics.sendEvent(eventName, *additionalArgs.toTypedArray())
        }
    }
}
