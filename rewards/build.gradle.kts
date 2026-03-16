plugins {
    alias(libs.plugins.java.library)
}

group = "example"
version = "unspecified"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform(libs.junit.platform.bom))
    testImplementation(libs.junit.jupiter)
    testRuntimeOnly(libs.junit.platform)
}

tasks.test {
    useJUnitPlatform()
}