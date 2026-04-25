package darculaforest

import java.io.File

fun main() {
    val outDir = File("darcula")

    File(outDir, "palette.css").writeText(generateCss())
    File(outDir, "Darcula_Forest.icls").writeText(generateIcls() + "\n")
    File(outDir, "alacritty.toml").writeText(generateAlacritty() + "\n")
    File(outDir, "darcula-forest.json").writeText(generateZed() + "\n")

    println("Generated:")
    println("  darcula/palette.css")
    println("  darcula/Darcula_Forest.icls")
    println("  darcula/alacritty.toml")
    println("  darcula/darcula-forest.json")
}
