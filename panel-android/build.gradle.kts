import org.jetbrains.kotlin.config.KotlinCompilerVersion

plugins {
    id("com.android.library")
    kotlin("android")
    id("kotlin-android-extensions")
    id("com.github.dcendents.android-maven")
}

android {
    compileSdkVersion(28)
    defaultConfig {
        minSdkVersion(21)
    }
    lintOptions {
        tasks {
            lint {
                enabled = false
            }
        }
    }
}

dependencies {
    api(project(":switchgear"))
    api("org.jetbrains.kotlin:kotlin-stdlib:${KotlinCompilerVersion.VERSION}")
    api("androidx.appcompat:appcompat:1.0.2")
    api("androidx.recyclerview:recyclerview:1.0.0")
    api("com.android.support.constraint:constraint-layout:1.1.3")
    api("com.xwray:groupie:2.5.1")
    api("com.xwray:groupie-kotlin-android-extensions:2.5.1")

    testImplementation("junit:junit:4.12")
}
