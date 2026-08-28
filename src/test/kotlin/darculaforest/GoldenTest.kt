package darculaforest

import java.io.File
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

/**
 * The committed files under darcula/ are the golden output of the generator.
 * If this fails, either run `./gradlew run` to refresh them, or someone hand-edited a generated file.
 */
class GoldenTest {
    @Test
    fun `generated output matches committed darcula directory`() {
        val outDir = Dirs.out
        for (f in generateAll()) {
            val committed = File(outDir, f.path)
            assertTrue(committed.exists(), "missing $committed — run ./gradlew run")
            assertEquals(f.contents, committed.readText(), "$committed is stale — run ./gradlew run")
        }
    }

    @Test
    fun `no unexpected files in generated directory`() {
        val expected = generateAll().map { it.path }.toSet()
        val actual = Dirs.out.walk().filter { it.isFile }.map { it.relativeTo(Dirs.out).path }.toSet()
        assertEquals(expected, actual, "darcula/ must contain only generator output")
    }

    @Test
    fun `hex roundtrips through oklch`() {
        for (hex in listOf("20231e", "acafa9", "5a792c", "ff0000", "000000", "ffffff")) {
            val o = oklchOfHex(hex)
            assertEquals(hex, hexOf(oklch(o.l, o.c, o.h)), "roundtrip $hex")
        }
    }
}
