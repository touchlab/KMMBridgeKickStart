package co.touchlab.brownfieldsdk

import co.touchlab.brownfieldsdk.db.BrownfieldSdkDb
import co.touchlab.brownfieldsdk.repository.BreedRepository
import co.touchlab.kermit.Logger
import com.russhwolf.settings.NSUserDefaultsSettings
import com.russhwolf.settings.Settings
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver
import io.ktor.client.engine.darwin.Darwin
import org.koin.core.Koin
import org.koin.core.KoinApplication
import org.koin.core.component.KoinComponent
import org.koin.core.parameter.parametersOf
import org.koin.dsl.module
import platform.Foundation.NSUserDefaults

fun initKoinIos(
    userDefaults: NSUserDefaults,
): KoinApplication = initKoin(
    module {
        single<Settings> { NSUserDefaultsSettings(userDefaults) }
    }
)

actual val platformModule = module {
    single<SqlDriver> { NativeSqliteDriver(BrownfieldSdkDb.Schema, "KampkitDb") }

    single { Darwin.create() }
}

@Suppress("unused") // Called from Swift
object KotlinDependencies : KoinComponent {
    fun getBreedRepository() = getKoin().get<BreedRepository>()
}