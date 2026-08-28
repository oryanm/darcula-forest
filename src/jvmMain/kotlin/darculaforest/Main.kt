package darculaforest

import java.io.File
import kotlin.system.exitProcess

private const val USAGE = """
usage: darcula-forest [--hue H] [--offset O] [--chroma C] [--out DIR]   regenerate theme files (default: darcula/)
       darcula-forest zip [--hue H] [--offset O] [--chroma C] --out FILE  write the themes as a zip
       darcula-forest hex <hex>...                                        convert sRGB hex to oklch
"""

fun main(args: Array<String>) {
    when (args.firstOrNull()) {
        "hex" -> args.drop(1).forEach { println("#${it.removePrefix("#")} → ${oklchOfHex(it)}") }
        "zip" -> {
            val (params, out) = parse(args.drop(1))
            val file = File(out ?: fail("zip requires --out FILE"))
            file.absoluteFile.parentFile?.mkdirs()
            file.writeBytes(zip(generateAll(params)))
            println("Wrote $file")
        }
        "-h", "--help" -> println(USAGE.trim())
        else -> {
            val (params, out) = parse(args.toList())
            generate(params, out?.let(::File) ?: Dirs.out)
        }
    }
}

private fun generate(params: ThemeParams, outDir: File) {
    println("Generated:")
    for (f in generateAll(params)) {
        File(outDir, f.path).apply { parentFile.mkdirs() }.writeText(f.contents)
        println("  $outDir/${f.path}")
    }
}

private fun parse(args: List<String>): Pair<ThemeParams, String?> {
    var params = ThemeParams()
    var out: String? = null
    val it = args.iterator()
    while (it.hasNext()) {
        val flag = it.next()
        val value = if (it.hasNext()) it.next() else fail("missing value for $flag")
        fun num() = value.toDoubleOrNull() ?: fail("$flag expects a number, got '$value'")
        params = when (flag) {
            "--hue"    -> params.copy(mainHue = num())
            "--offset" -> params.copy(complementaryColorOffset = num())
            "--chroma" -> params.copy(baseChroma = num())
            "--out"    -> { out = value; params }
            else       -> fail("unknown option $flag")
        }
    }
    return params.clamped() to out
}

private fun fail(msg: String): Nothing {
    System.err.println(msg)
    System.err.println(USAGE.trim())
    exitProcess(2)
}
