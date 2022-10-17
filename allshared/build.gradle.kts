@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    id("co.touchlab.faktory.kmmbridge")
}

val GROUP: String by project
val VERSION_NAME: String by project

group = GROUP
version = VERSION_NAME

kotlin {
    ios()
    // Note: iosSimulatorArm64 target requires that all dependencies have M1 support
    iosSimulatorArm64()

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(project(":breeds"))
                api(project(":analytics"))
                implementation(libs.coroutines.core)
                implementation(libs.bundles.ktor.common)
                implementation(libs.multiplatformSettings.common)
                implementation(libs.kotlinx.dateTime)
                implementation(libs.touchlab.kermit)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(libs.bundles.shared.commonTest)
            }
        }
        val iosMain by getting {
            dependencies {
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

    cocoapods {
        summary = "Brownfield sample"
        homepage = "https://www.touchlab.co"
        ios.deploymentTarget = "13.5"
        framework {
            export(project(":analytics"))
            export(project(":breeds"))
            isStatic = true
        }
    }
}

kmmbridge {
    githubReleaseArtifacts()
    githubReleaseVersions()
    cocoapods("git@github.com:touchlab/PodSpecs.git")
    versionPrefix.set("1.0")
}
