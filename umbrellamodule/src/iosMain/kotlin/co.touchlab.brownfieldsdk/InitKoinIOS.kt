package co.touchlab.brownfieldsdk

import co.touchlab.brownfieldsdk.repository.BreedRepository
import com.russhwolf.settings.NSUserDefaultsSettings
import com.russhwolf.settings.Settings
import org.koin.core.KoinApplication
import org.koin.core.component.KoinComponent
import org.koin.dsl.module
import platform.Foundation.NSUserDefaults

fun initKoinIos(
    userDefaults: NSUserDefaults
): KoinApplication = initKoin(
    module {
        single<Settings> { NSUserDefaultsSettings(userDefaults) }
    }
)

@Suppress("unused") // Called from Swift
object KotlinDependencies : KoinComponent {
    fun getBreedRepository() = getKoin().get<BreedRepository>()
}
