package co.touchlab.kmmbridgekickstart

import co.touchlab.kmmbridgekickstart.db.KMMBridgeKickStartDb
import co.touchlab.kmmbridgekickstart.repository.BreedRepository
import com.russhwolf.settings.NSUserDefaultsSettings
import com.russhwolf.settings.Settings
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver
import io.ktor.client.engine.*
import io.ktor.client.engine.darwin.*
import platform.Foundation.NSUserDefaults

fun breedStartup(analyticsHandle: AnalyticsHandle): BreedRepository {
    val locator = IOSServiceLocator(NSUserDefaults(suiteName = SETTINGS_KEY), analyticsHandle = analyticsHandle)
    return locator.breedRepository
}

internal class IOSServiceLocator(
    userDefaults: NSUserDefaults,
    analyticsHandle: AnalyticsHandle
) : BaseServiceLocator(analyticsHandle) {

    override val sqlDriver: SqlDriver by lazy {
        NativeSqliteDriver(KMMBridgeKickStartDb.Schema, DB_NAME)
    }

    override val settings: Settings by lazy { NSUserDefaultsSettings(userDefaults) }

    override val clientEngine: HttpClientEngine by lazy { Darwin.create() }
}
