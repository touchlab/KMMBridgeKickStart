package co.touchlab.brownfieldsdk.mock

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant

class ClockMock(var currentInstant: Instant) : Clock {
    override fun now(): Instant = currentInstant
}
