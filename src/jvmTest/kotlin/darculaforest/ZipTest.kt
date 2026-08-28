package darculaforest

import java.nio.file.Files
import java.util.zip.CRC32
import java.util.zip.ZipFile
import kotlin.test.Test
import kotlin.test.assertEquals

class ZipTest {
    private val files = generateAll() + GeneratedFile("ünïcödé/naïve → ✓.txt", "héllo ✓ 日本語\n") + GeneratedFile("empty", "")

    /** java.util.zip reads via the central directory and checks every CRC, so this validates the whole structure. */
    @Test
    fun `archive round-trips through ZipFile`() {
        val path = Files.createTempFile("darcula-forest-", ".zip")
        try {
            Files.write(path, zip(files))
            ZipFile(path.toFile(), Charsets.UTF_8).use { zf ->
                assertEquals(files.map { it.path }, zf.entries().toList().map { it.name })
                for (f in files) {
                    val e = zf.getEntry(f.path)
                    assertEquals(f.contents, zf.getInputStream(e).use { it.readAllBytes() }.toString(Charsets.UTF_8), f.path)
                }
            }
        } finally {
            Files.deleteIfExists(path)
        }
    }

    @Test
    fun `crc32 matches java implementation`() {
        val inputs = listOf(ByteArray(0), "123456789".encodeToByteArray(), ByteArray(100_003) { (it * 31 + 7).toByte() }) +
            files.map { it.contents.encodeToByteArray() }
        for (input in inputs) {
            val expected = CRC32().apply { update(input) }.value
            assertEquals(expected, crc32(input).toLong() and 0xFFFFFFFFL, "crc of ${input.size} bytes")
        }
    }
}
