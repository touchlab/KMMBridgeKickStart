pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
    resolutionStrategy {
        eachPlugin {
            if (requested.id.namespace == "com.android" || requested.id.name == "kotlin-android-extensions") {
                useModule("com.android.tools.build:gradle:7.0.4")
            }
        }
    }
}
rootProject.name = "BrownfieldSDK"

enableFeaturePreview("VERSION_CATALOGS")

include("allshared")
include("analytics")
include("breeds")
