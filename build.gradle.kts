@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    kotlin("multiplatform") version libs.versions.kotlin.get() apply false
    kotlin("plugin.serialization") version libs.versions.kotlin.get() apply false
    kotlin("native.cocoapods") version libs.versions.cocoapods.get() apply false
    id("com.android.library") version libs.versions.android.gradle.plugin.get() apply false
    id("co.touchlab.faktory.kmmbridge") version libs.versions.kmmBridge.get() apply false
    id("com.squareup.sqldelight") version libs.versions.sqlDelight.get() apply false
    id("maven-publish")
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

tasks.register<Delete>("clean") {
    delete(rootProject.buildDir)
}
