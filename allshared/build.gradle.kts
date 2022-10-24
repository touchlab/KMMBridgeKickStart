import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    id("co.touchlab.faktory.kmmbridge")
    `maven-publish`
}

kotlin {
    ios {
//        binaries {
//            this.framework {
//                this.linkerOpts += "-lsqlite3"
//            }
//        }
    }
    // Note: iosSimulatorArm64 target requires that all dependencies have M1 support
    iosSimulatorArm64 {
//        binaries {
//            this.framework {
//                this.linkerOpts += "-lsqlite3"
//            }
//        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(project(":breeds"))
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

//    targets.withType<KotlinNativeTarget> {
//        this.compilations.forEach {
//            it.kotlinOptions.freeCompilerArgs += listOf("-linker-options", "-lsqlite3")
//        }
//    }

    cocoapods {
        summary = "KMMBridgeKickStart sample"
        homepage = "https://www.touchlab.co"
        ios.deploymentTarget = "14.1"
        extraSpecAttributes["libraries"] = "'c++', 'sqlite3'"

        framework {
            export(project(":analytics"))
            isStatic = true
//            embedBitcode = org.jetbrains.kotlin.gradle.plugin.mpp.BitcodeEmbeddingMode.DISABLE
        }
    }

//    val list = listOf(
//        "_sqlite3_bind_blob",
//        "_sqlite3_bind_double",
//        "_sqlite3_bind_int64",
//        "_sqlite3_bind_null",
//        "_sqlite3_bind_parameter_index",
//        "_sqlite3_bind_text",
//        "_sqlite3_busy_timeout",
//        "_sqlite3_changes",
//        "_sqlite3_clear_bindings",
//        "_sqlite3_close",
//        "_sqlite3_close_v2",
//        "_sqlite3_column_blob",
//        "_sqlite3_column_bytes",
//        "_sqlite3_column_count",
//        "_sqlite3_column_double",
//        "_sqlite3_column_int64",
//        "_sqlite3_column_name",
//        "_sqlite3_column_text",
//        "_sqlite3_column_type",
//        "_sqlite3_db_config",
//        "_sqlite3_db_readonly",
//        "_sqlite3_errmsg",
//        "_sqlite3_finalize",
//        "_sqlite3_last_insert_rowid",
//        "_sqlite3_open_v2",
//        "_sqlite3_prepare16_v2",
//        "_sqlite3_reset",
//        "_sqlite3_step",
//    )
//    crashLinkerConfig(
//        list.joinToString(" ") { "-U $it" }
//    )
}

addGithubPackagesRepository()

kmmbridge {
    mavenPublishArtifacts()
    githubReleaseVersions()
    spm()
//    cocoapods("git@github.com:touchlab/PodSpecs.git")
}

fun org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension.crashLinkerConfig(linkerOpts: String) {
    targets.withType(KotlinNativeTarget::class.java)
        .map { target ->
            val mainCompilation = target.compilations.getByName("main")
            val dynamicFrameworks =
                target.binaries.filterIsInstance<org.jetbrains.kotlin.gradle.plugin.mpp.Framework>()
                    .filter { framework -> !framework.isStatic }

            Pair(mainCompilation, dynamicFrameworks)
        }
        .forEach { pair ->
            if (!pair.second.isEmpty()) {
                pair.first.kotlinOptions.freeCompilerArgs += listOf("-linker-options", linkerOpts)
            }
        }
}