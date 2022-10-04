plugins {
    kotlin("multiplatform") version "1.7.10"
    id("com.android.library")
    id("kotlin-android-extensions")
    id("maven-publish")
    id("co.touchlab.faktory.kmmbridge") version "0.1.2-SNAPSHOT"
    kotlin("native.cocoapods") version "1.7.20"
}

group = "co.touchlab"
version = "1.0"

repositories {
    google()
    mavenCentral()
}

kotlin {
    android {
        publishAllLibraryVariants()
    }
    ios()
    // Note: iosSimulatorArm64 target requires that all dependencies have M1 support
    iosSimulatorArm64()

    sourceSets {
        val commonMain by getting
        val commonTest by getting {
            dependencies {
//                implementation(kotlin("test"))
            }
        }
        val androidMain by getting {
            dependencies {
//                implementation("com.google.android.material:material:1.5.0")
            }
        }
        val androidTest by getting {
            dependencies {
                implementation("junit:junit:4.13.2")
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

    cocoapods {
        summary = "Brownfield sample"
        homepage = "https://www.touchlab.co"
        ios.deploymentTarget = "13.5"
        framework {
            isStatic = false
        }
    }
}

android {
    compileSdk = 33
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = 21
        targetSdk = 33
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}

kmmbridge {
    faktoryReadKey.set("1EE4B4A7CFEF438A8C0DAF8981")
    cocoapods("git@github.com:touchlab/PodSpecs.git")
}