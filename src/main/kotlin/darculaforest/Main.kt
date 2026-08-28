package darculaforest

import java.io.File

fun main(args: Array<String>) {
    when (args.firstOrNull()) {
        null -> generate()
        "hex" -> args.drop(1).forEach { println("#${it.removePrefix("#")} → ${oklchOfHex(it)}") }
        else -> {
            System.err.println("usage: darcula-forest            # regenerate ${Dirs.out}/")
            System.err.println("       darcula-forest hex <hex>… # convert sRGB hex to oklch")
            kotlin.system.exitProcess(2)
        }
    }
}

private fun generate() {
    val outDir = Dirs.out
    println("Generated:")
    for (f in generateAll()) {
        File(outDir, f.path).apply { parentFile.mkdirs() }.writeText(f.contents)
        println("  $outDir/${f.path}")
    }
}
