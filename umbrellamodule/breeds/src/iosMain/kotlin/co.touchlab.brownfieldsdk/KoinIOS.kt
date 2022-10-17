package co.touchlab.brownfieldsdk

import co.touchlab.brownfieldsdk.db.BrownfieldSdkDb
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver
import io.ktor.client.engine.darwin.Darwin
import org.koin.dsl.module

actual val platformModule = module {
    single<SqlDriver> { NativeSqliteDriver(BrownfieldSdkDb.Schema, "BrownfieldSdkDb") }

    single { Darwin.create() }
}
