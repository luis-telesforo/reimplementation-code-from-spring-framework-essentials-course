plugins {
    alias(libs.plugins.java.library.conventions)
}

group = "example"
version = "unspecified"

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.jakarta.persistence.api)
    testImplementation(platform(libs.junit.platform.bom))
    testImplementation(libs.junit.jupiter)
    testRuntimeOnly(libs.junit.platform.launcher)
}

tasks.test {
    useJUnitPlatform()
}