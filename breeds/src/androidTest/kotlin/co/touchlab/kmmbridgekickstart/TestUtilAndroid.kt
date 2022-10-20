package co.touchlab.kmmbridgekickstart

import android.app.Application
import androidx.test.core.app.ApplicationProvider
import co.touchlab.kmmbridgekickstart.db.KMMBridgeKickStartDb
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.sqlite.driver.JdbcSqliteDriver

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
