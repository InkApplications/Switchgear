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
        minSdkVersion(14)
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
    api(project(":provider-sharedpreferences"))

    implementation("org.jetbrains.kotlin:kotlin-stdlib:${KotlinCompilerVersion.VERSION}")

    implementation("androidx.appcompat:appcompat:1.0.2")
    implementation("androidx.recyclerview:recyclerview:1.0.0")
    implementation("com.android.support.constraint:constraint-layout:1.1.3")
    implementation("com.xwray:groupie:2.5.1")
    implementation("com.xwray:groupie-kotlin-android-extensions:2.5.1")

    testImplementation("junit:junit:4.12")
}
