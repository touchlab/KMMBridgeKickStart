package co.touchlab.brownfieldsdk

import android.content.Context
import android.content.SharedPreferences
import co.touchlab.brownfieldsdk.db.BrownfieldSdkDb
import com.russhwolf.settings.Settings
import com.russhwolf.settings.SharedPreferencesSettings
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import io.ktor.client.engine.okhttp.OkHttp
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModule: Module = module {
    single<SqlDriver> {
        AndroidSqliteDriver(
            BrownfieldSdkDb.Schema,
            get(),
            "BrownfieldSdkDb"
        )
    }

    single<Settings> {
        SharedPreferencesSettings(get())
    }

    single {
        OkHttp.create()
    }

    single<SharedPreferences> {
        get<Context>().getSharedPreferences("BROWNFIELD_SETTINGS", Context.MODE_PRIVATE)
    }
}
