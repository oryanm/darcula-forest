import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpack

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.kotlin.serialization)
}

repositories {
    mavenCentral()
}

kotlin {
    jvmToolchain(21)

    jvm {
        // Built-in KMP run support (Kotlin 2.0+): registers `jvmRun`, which IntelliJ uses for gutter runs.
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        mainRun {
            mainClass.set("darculaforest.MainKt")
        }
    }

    js {
        moduleName = "darcula-forest"
        browser {
            commonWebpackConfig {
                outputFileName = "darcula-forest.js"
            }
        }
        binaries.executable()
        useEsModules()
        generateTypeScriptDefinitions()
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

// ── JVM ─────────────────────────────────────────────────────────────

// `jvmRun` is registered lazily by KGP, so match it by name.
tasks.withType<JavaExec>().configureEach {
    if (name == "jvmRun") workingDir = rootDir
}

// `./gradlew run` — same as the old `application` plugin task. Regenerates darcula/.
val jvmMainCompilation = kotlin.targets.getByName("jvm").compilations.getByName("main")

tasks.register<JavaExec>("run") {
    group = "application"
    description = "Regenerate the theme files under darcula/"
    mainClass.set("darculaforest.MainKt")
    classpath = files(jvmMainCompilation.output.allOutputs, jvmMainCompilation.runtimeDependencyFiles)
    workingDir = rootDir
}

tasks.named<Test>("jvmTest") {
    useJUnitPlatform()
    workingDir = rootDir
}

// ── JS ──────────────────────────────────────────────────────────────

// Copies the production webpack bundle into site/ so preview.html can load it (works from file://).
tasks.register<Copy>("copyJsBundle") {
    group = "build"
    description = "Copy the production JS bundle into site/"
    val webpack = tasks.named<KotlinWebpack>("jsBrowserProductionWebpack")
    from(webpack.map { it.outputDirectory }) {
        include("darcula-forest.js", "darcula-forest.js.map")
    }
    into(layout.projectDirectory.dir("site"))
}
