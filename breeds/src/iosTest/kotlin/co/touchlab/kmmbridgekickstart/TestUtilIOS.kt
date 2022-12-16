package co.touchlab.kmmbridgekickstart

import co.touchlab.kmmbridgekickstart.db.KMMBridgeKickStartDb
import co.touchlab.sqliter.DatabaseConfiguration
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import app.cash.sqldelight.driver.native.wrapConnection

internal actual fun testDbConnection(): SqlDriver {
    val schema = KMMBridgeKickStartDb.Schema
    return NativeSqliteDriver(
        DatabaseConfiguration(
            name = "KMMBridgeKickStartDb",
            version = schema.version,
            create = { connection ->
                wrapConnection(connection) { schema.create(it) }
            },
            upgrade = { connection, oldVersion, newVersion ->
                wrapConnection(connection) { schema.migrate(it, oldVersion, newVersion) }
            },
            inMemory = true
        )
    )
}
