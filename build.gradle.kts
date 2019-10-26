buildscript {
    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:3.5.0")
        classpath(kotlin("gradle-plugin", version = "1.3.50"))
        classpath("com.github.dcendents:android-maven-gradle-plugin:2.1")
        classpath(atomicFU("gradle-plugin"))
    }
}

subprojects {
    version="2.0-SNAPSHOT"
    group="com.github.InkApplications.Switchgear"

    repositories {
        google()
        jcenter()
    }
}
