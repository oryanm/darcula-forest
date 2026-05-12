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
    implementation(libs.ktor.server.core)
    implementation(libs.ktor.server.cio)
    implementation(libs.ktor.server.content.negotiation)
    implementation(libs.ktor.serialization.kotlinx.json)
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

tasks.register<JavaExec>("serve") {
    group = "application"
    description = "Run the theme generator HTTP server"
    mainClass = "darculaforest.server.AppKt"
    classpath = sourceSets["main"].runtimeClasspath
    workingDir = rootDir
}
