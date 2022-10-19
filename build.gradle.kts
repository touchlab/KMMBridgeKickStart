@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    kotlin("multiplatform") version libs.versions.kotlin.get() apply false
    kotlin("plugin.serialization") version libs.versions.kotlin.get() apply false
    kotlin("native.cocoapods") version libs.versions.cocoapods.get() apply false
    id("com.android.library") version libs.versions.android.gradle.plugin.get() apply false
    id("co.touchlab.faktory.kmmbridge") version libs.versions.kmmBridge.get() apply false
    id("com.squareup.sqldelight") version libs.versions.sqlDelight.get() apply false
    id("maven-publish")
    alias(libs.plugins.ktlint)
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

subprojects {
    // TODO libs doesn't resolve if we do this
    // apply(plugin = libs.plugins.ktlint.get().pluginId)
    apply(plugin = "org.jlleitschuh.gradle.ktlint")

    configure<org.jlleitschuh.gradle.ktlint.KtlintExtension> {
        enableExperimentalRules.set(true)
        verbose.set(true)
        filter {
            exclude { it.file.path.contains("build/") }
        }
    }

    afterEvaluate {
        tasks.named("check").configure {
            dependsOn(tasks.getByName("ktlintCheck"))
        }
    }
}

tasks.register<Delete>("clean") {
    delete(rootProject.buildDir)
}
