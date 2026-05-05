package darculaforest

import java.io.File

fun main() {
    val outDir = File("darcula")

    val css = File(outDir, "css").apply { mkdirs() }
    val jetbrains = File(outDir, "jetbrains").apply { mkdirs() }
    val alacritty = File(outDir, "alacritty").apply { mkdirs() }
    val zed = File(outDir, "zed").apply { mkdirs() }

    File(css, "palette.css").writeText(generateCss())
    File(jetbrains, "Darcula_Forest.icls").writeText(generateIcls() + "\n")
    File(alacritty, "alacritty.toml").writeText(generateAlacritty() + "\n")
    File(zed, "darcula-forest.json").writeText(generateZed() + "\n")

    println("Generated:")
    println("  darcula/css/palette.css")
    println("  darcula/jetbrains/Darcula_Forest.icls")
    println("  darcula/alacritty/alacritty.toml")
    println("  darcula/zed/darcula-forest.json")
}
