package co.touchlab.kmmbridgekickstart

import android.app.Application
import androidx.test.core.app.ApplicationProvider
import co.touchlab.kmmbridgekickstart.db.KMMBridgeKickStartDb
import app.cash.sqldelight.android.AndroidSqliteDriver
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.sqlite.driver.JdbcSqliteDriver

internal actual fun testDbConnection(): SqlDriver {
    // Try to use the android driver (which only works if we're on robolectric).
    // Fall back to jdbc if that fails.
    return try {
        val app = ApplicationProvider.getApplicationContext<Application>()
        AndroidSqliteDriver(KMMBridgeKickStartDb.Schema, app, "KmmBridgeKickStartDb")
    } catch (exception: IllegalStateException) {
        JdbcSqliteDriver(JdbcSqliteDriver.IN_MEMORY)
            .also { KMMBridgeKickStartDb.Schema.create(it) }
    }
}
