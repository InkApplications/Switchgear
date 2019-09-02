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
    api(project(":provider-sharedpreferences"))
    implementation(project(":panel-android"))

    testImplementation("junit:junit:4.12")
}
