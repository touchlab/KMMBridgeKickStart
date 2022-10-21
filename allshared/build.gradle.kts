@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    id("com.android.library")
    id("co.touchlab.faktory.kmmbridge")
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
                api(project(":breeds"))
                api(project(":analytics"))
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(libs.bundles.shared.commonTest)
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
        summary = "KMMBridgeKickStart sample"
        homepage = "https://www.touchlab.co"
        ios.deploymentTarget = "13.5"
        extraSpecAttributes["libraries"] = "'c++', 'sqlite3'"
        framework {
            export(project(":analytics"))
            export(project(":breeds"))
            isStatic = true
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

kmmbridge {
    mavenPublishArtifacts()
    githubReleaseVersions()
    spm()
    cocoapods()
//    cocoapods("git@github.com:touchlab/PodSpecs.git")
}
