plugins {
    alias(libs.plugins.kotlin.jvm)
    application
}

repositories {
    mavenCentral()
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
