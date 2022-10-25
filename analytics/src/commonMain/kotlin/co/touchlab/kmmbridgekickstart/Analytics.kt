package co.touchlab.kmmbridgekickstart

import co.touchlab.stately.concurrency.AtomicReference

interface Analytics {
    fun sendEvent(eventName: String, eventArgs: Map<String, Any>)
}

fun initAnalytics(analytics: Analytics) {
    if (!AnalyticsHandler.analyticsAtom.compareAndSet(null, analytics)) {
        throw IllegalStateException("Analytics can only be set once")
    }
}

internal fun sendEvent(name: String, vararg args: Pair<String, Any>) {
    AnalyticsHandler.analyticsAtom.get()!!.sendEvent(name, args.toMap())
}

internal object AnalyticsHandler {
    val analyticsAtom: AtomicReference<Analytics?> = AtomicReference(null)
}