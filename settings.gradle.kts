pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}
rootProject.name = "BrownfieldSDK"

enableFeaturePreview("VERSION_CATALOGS")

include("allshared")
include("analytics")
include("breeds")
