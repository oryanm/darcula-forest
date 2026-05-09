package darculaforest

import java.io.File

fun main() {
    val outDir = File("darcula")
    println("Generated:")
    for (f in generateAll()) {
        File(outDir, f.path).apply { parentFile.mkdirs() }.writeText(f.contents)
        println("  darcula/${f.path}")
    }
}
