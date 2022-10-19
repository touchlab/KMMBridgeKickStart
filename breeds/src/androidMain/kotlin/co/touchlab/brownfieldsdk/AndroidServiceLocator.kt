package co.touchlab.brownfieldsdk

import android.content.Context
import android.content.SharedPreferences
import co.touchlab.brownfieldsdk.db.BrownfieldSdkDb
import com.russhwolf.settings.Settings
import com.russhwolf.settings.SharedPreferencesSettings
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.okhttp.OkHttp

class AndroidServiceLocator(
    context: Context,
    sharedPreferences: SharedPreferences = context.getSharedPreferences(
        "BrownfieldSDKSettings",
        Context.MODE_PRIVATE
    )
) : BaseServiceLocator() {

    override val sqlDriver: SqlDriver by lazy {
        AndroidSqliteDriver(
            schema = BrownfieldSdkDb.Schema,
            context = context,
            name = "BrownfieldSdkDb"
        )
    }

    override val settings: Settings by lazy { SharedPreferencesSettings(delegate = sharedPreferences) }

    override val clientEngine: HttpClientEngine by lazy { OkHttp.create() }
}
