plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("com.squareup.sqldelight")
    id("com.android.library")
    `maven-publish`
}

kotlin {
    android {
        publishAllLibraryVariants()
    }
    ios()
    // Note: iosSimulatorArm64 target requires that all dependencies have M1 support
    iosSimulatorArm64()

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(project(":analytics"))
                implementation(libs.coroutines.core)
                implementation(libs.bundles.ktor.common)
                implementation(libs.multiplatformSettings.common)
                implementation(libs.kotlinx.dateTime)
                implementation(libs.touchlab.kermit)
                implementation(libs.sqlDelight.coroutinesExt)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(libs.bundles.shared.commonTest)
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(libs.sqlDelight.android)
                implementation(libs.ktor.client.okHttp)
            }
        }
        val androidTest by getting {
            dependencies {
                implementation(libs.bundles.shared.androidTest)
            }
        }
        val iosMain by getting {
            dependencies {
                implementation(libs.sqlDelight.native)
                implementation(libs.ktor.client.ios)
            }
        }
        val iosTest by getting
        val iosSimulatorArm64Main by getting {
            dependsOn(iosMain)
        }
        val iosSimulatorArm64Test by getting {
            dependsOn(iosTest)
        }
    }
}

android {
    compileSdk = libs.versions.compileSdk.get().toInt()
    defaultConfig {
        @Suppress("UnstableApiUsage")
        minSdk = libs.versions.minSdk.get().toInt()
    }
}

addGithubPackagesRepository()

sqldelight {
    database("KMMBridgeKickStartDb") {
        packageName = "co.touchlab.kmmbridgekickstart.db"
    }
}
