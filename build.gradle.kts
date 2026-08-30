import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpack

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.kotlin.serialization)
}

repositories {
    mavenCentral()
}

kotlin {
    jvmToolchain(25)
    jvm()
    js {
        outputModuleName.set("darcula-forest") // also the global the UMD bundle registers: window["darcula-forest"]
        browser()
        binaries.executable()
    }

    sourceSets {
        commonMain.dependencies {
            implementation(libs.kotlinx.serialization.json)
        }
        jvmTest.dependencies {
            implementation(kotlin("test"))
        }
    }
}

val jvmMain = kotlin.targets.getByName("jvm").compilations.getByName("main")

tasks.register<JavaExec>("run") {
    group = "application"
    description = "Regenerate the theme files under darcula/"
    mainClass.set("darculaforest.MainKt")
    classpath = files(jvmMain.output.allOutputs, jvmMain.runtimeDependencyFiles)
    workingDir = rootDir
}

tasks.named<Test>("jvmTest") {
    useJUnitPlatform()
    workingDir = rootDir
}

tasks.register<Copy>("copyJsBundle") {
    group = "build"
    description = "Copy the production JS bundle and palette.css into site/ so index.html can load them"
    val webpack = tasks.named<KotlinWebpack>("jsBrowserProductionWebpack")
    from(webpack.map { it.outputDirectory }) { include("darcula-forest.js") }
    from(layout.projectDirectory.file("darcula/css/palette.css"))
    into(layout.projectDirectory.dir("site"))
}

tasks.register<Exec>("site") {
    group = "application"
    description = "Build the JS bundle and open site/index.html in the default browser"
    dependsOn("copyJsBundle")
    commandLine("open", layout.projectDirectory.file("site/index.html").asFile.path)
}
