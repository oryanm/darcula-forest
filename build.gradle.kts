plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kotlin.serialization)
    application
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.kotlinx.serialization.json)
}

kotlin {
    jvmToolchain(21)
}

application {
    mainClass = "darculaforest.MainKt"
}

tasks.named<JavaExec>("run") {
    workingDir = rootDir
}
