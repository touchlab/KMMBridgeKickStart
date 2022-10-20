pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenLocal()
        mavenCentral()
    }
}
rootProject.name = "KMMBridgeKickStart"

enableFeaturePreview("VERSION_CATALOGS")

include("allshared")
include("analytics")
include("breeds")
