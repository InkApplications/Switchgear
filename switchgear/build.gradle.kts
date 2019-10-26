import org.jetbrains.kotlin.config.KotlinCompilerVersion

plugins {
    kotlin("jvm")
    id("kotlinx-atomicfu")
    id("maven-publish")
}

publishing {
    publications {
        create<MavenPublication>("default") {
            from(components["java"])
        }
    }
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib:${KotlinCompilerVersion.VERSION}")
    implementation(atomicFU())

    testImplementation("junit:junit:4.12")
}
