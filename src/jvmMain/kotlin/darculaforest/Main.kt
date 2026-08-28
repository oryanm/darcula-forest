package darculaforest

import java.io.File

/** `darcula-forest` regenerates darcula/; `darcula-forest hex <hex>...` converts sRGB hexes to oklch. */
fun main(args: Array<String>) {
    if (args.firstOrNull() == "hex") {
        args.drop(1).forEach { println("#${it.removePrefix("#")} → ${oklchOfHex(it)}") }
        return
    }
    println("Generated:")
    for (file in generateAll()) {
        File(OUT_DIR, file.path).apply { parentFile.mkdirs() }.writeText(file.contents)
        println("  $OUT_DIR/${file.path}")
    }
}

/** Generator output, relative to the repo root (the Gradle tasks set the working dir). */
val OUT_DIR = File("darcula")
