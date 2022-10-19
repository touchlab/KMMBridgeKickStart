package co.touchlab.brownfieldsdk

import co.touchlab.brownfieldsdk.db.BrownfieldSdkDb
import com.russhwolf.settings.NSUserDefaultsSettings
import com.russhwolf.settings.Settings
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.darwin.Darwin
import platform.Foundation.NSUserDefaults

class IOSServiceLocator(
    userDefaults: NSUserDefaults,
    analytics: Analytics,
) : BaseServiceLocator() {

    override val sqlDriver: SqlDriver by lazy {
        NativeSqliteDriver(BrownfieldSdkDb.Schema, "BrownfieldSdkDb")
    }

    override val settings: Settings by lazy { NSUserDefaultsSettings(userDefaults) }

    override val clientEngine: HttpClientEngine by lazy { Darwin.create() }

    override val appAnalytics by lazy { AppAnalytics(analytics) }
    override val breedAnalytics by lazy { BreedAnalytics(analytics) }
    override val httpClientAnalytics by lazy { HttpClientAnalytics(analytics) }
}
