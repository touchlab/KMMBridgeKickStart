package co.touchlab.kmmbridgekickstart

import android.content.Context
import android.content.SharedPreferences
import co.touchlab.kmmbridgekickstart.db.KMMBridgeKickStartDb
import com.russhwolf.settings.Settings
import com.russhwolf.settings.SharedPreferencesSettings
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.okhttp.OkHttp

class AndroidServiceLocator(
    context: Context,
    sharedPreferences: SharedPreferences = context.getSharedPreferences(
        "KMMBridgeKickStartSettings",
        Context.MODE_PRIVATE
    )
) : BaseServiceLocator() {

    override val sqlDriver: SqlDriver by lazy {
        AndroidSqliteDriver(
            schema = KMMBridgeKickStartDb.Schema,
            context = context,
            name = "KMMBridgeKickStartDb"
        )
    }

    override val settings: Settings by lazy { SharedPreferencesSettings(delegate = sharedPreferences) }

    override val clientEngine: HttpClientEngine by lazy { OkHttp.create() }
}
