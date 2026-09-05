package darculaforest

// ── Minimal ZIP writer ──────────────────────────────────────────────
// Dependency-free common Kotlin (no java.*, no kotlin.js.*) so it can run
// on both the JVM and in the browser. Entries are STORED (method 0, no
// compression), timestamps are fixed, so the output is fully deterministic:
// the same input always yields the same bytes.
//
// Layout (all integers little-endian, per APPNOTE.TXT):
//   [local file header + name + data] × n
//   [central directory header + name] × n
//   end of central directory record

/** Builds a .zip archive containing [files], stored uncompressed. */
fun zip(files: List<GeneratedFile>): ByteArray {
    val names = files.map { it.path.encodeToByteArray() }
    val datas = files.map { it.bytes }
    val crcs = datas.map(::crc32)

    val localSize = names.indices.sumOf { LOCAL_HEADER_LEN + names[it].size + datas[it].size }
    val centralSize = names.sumOf { CENTRAL_HEADER_LEN + it.size }
    val out = Writer(ByteArray(localSize + centralSize + EOCD_LEN))

    for (i in names.indices) {
        out.u32(LOCAL_FILE_HEADER_SIG)
        out.u16(VERSION_NEEDED)
        out.u16(GENERAL_PURPOSE_FLAGS)
        out.u16(METHOD_STORED)
        out.u16(DOS_TIME)
        out.u16(DOS_DATE)
        out.u32(crcs[i])
        out.u32(datas[i].size)   // compressed size (== uncompressed for STORED)
        out.u32(datas[i].size)   // uncompressed size
        out.u16(names[i].size)
        out.u16(0)               // extra field length
        out.bytes(names[i])
        out.bytes(datas[i])
    }

    var localOffset = 0
    for (i in names.indices) {
        out.u32(CENTRAL_DIR_HEADER_SIG)
        out.u16(VERSION_MADE_BY)
        out.u16(VERSION_NEEDED)
        out.u16(GENERAL_PURPOSE_FLAGS)
        out.u16(METHOD_STORED)
        out.u16(DOS_TIME)
        out.u16(DOS_DATE)
        out.u32(crcs[i])
        out.u32(datas[i].size)
        out.u32(datas[i].size)
        out.u16(names[i].size)
        out.u16(0)               // extra field length
        out.u16(0)               // file comment length
        out.u16(0)               // disk number start
        out.u16(0)               // internal file attributes
        out.u32(0)               // external file attributes
        out.u32(localOffset)
        out.bytes(names[i])
        localOffset += LOCAL_HEADER_LEN + names[i].size + datas[i].size
    }

    out.u32(END_OF_CENTRAL_DIR_SIG)
    out.u16(0)                   // number of this disk
    out.u16(0)                   // disk where central directory starts
    out.u16(files.size)          // entries on this disk
    out.u16(files.size)          // total entries
    out.u32(centralSize)
    out.u32(localSize)           // central directory offset
    out.u16(0)                   // comment length

    check(out.pos == out.buf.size) { "internal: zip size mismatch" }
    return out.buf
}

private const val LOCAL_HEADER_LEN = 30
private const val CENTRAL_HEADER_LEN = 46
private const val EOCD_LEN = 22

private const val LOCAL_FILE_HEADER_SIG = 0x04034b50
private const val CENTRAL_DIR_HEADER_SIG = 0x02014b50
private const val END_OF_CENTRAL_DIR_SIG = 0x06054b50

private const val VERSION_NEEDED = 10            // 1.0 — plain STORED entries need nothing newer
private const val VERSION_MADE_BY = 20           // upper byte 0 = MS-DOS attrs, lower byte = spec 2.0
private const val GENERAL_PURPOSE_FLAGS = 0x0800 // bit 11: file names are UTF-8
private const val METHOD_STORED = 0

// Fixed MS-DOS timestamp: 1980-01-01 00:00:00 (the earliest representable).
// date = (year-1980) << 9 | month << 5 | day ; time = hour << 11 | minute << 5 | second/2
private const val DOS_DATE = (0 shl 9) or (1 shl 5) or 1
private const val DOS_TIME = 0

// ── CRC-32 (IEEE 802.3, reflected polynomial 0xEDB88320) ────────────

private val CRC_TABLE: IntArray = IntArray(256) { n ->
    var c = n
    repeat(8) { c = if (c and 1 != 0) 0xEDB88320.toInt() xor (c ushr 1) else c ushr 1 }
    c
}

/** CRC-32 of [data], returned as the raw 32 bits in an Int (interpret as unsigned). */
internal fun crc32(data: ByteArray): Int {
    var c = -1 // 0xFFFFFFFF
    for (b in data) c = CRC_TABLE[(c xor b.toInt()) and 0xFF] xor (c ushr 8)
    return c.inv()
}

// ── Little-endian writer over a preallocated buffer ─────────────────

private class Writer(val buf: ByteArray) {
    var pos = 0

    fun u16(v: Int) { buf[pos++] = v.toByte(); buf[pos++] = (v ushr 8).toByte() }

    fun u32(v: Int) { u16(v); u16(v ushr 16) }

    fun bytes(b: ByteArray) { b.copyInto(buf, pos); pos += b.size }
}
