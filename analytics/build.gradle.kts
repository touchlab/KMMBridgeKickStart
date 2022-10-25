plugins {
    kotlin("multiplatform")
    id("com.android.library")
    `maven-publish`
}

kotlin {
    android {
        publishAllLibraryVariants()
    }
    ios()
    iosSimulatorArm64()

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("co.touchlab:stately-concurrency:1.2.3")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val iosMain by getting
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