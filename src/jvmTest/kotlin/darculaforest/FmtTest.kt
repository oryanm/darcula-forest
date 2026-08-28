package darculaforest

import kotlin.test.Test
import kotlin.test.assertEquals

class FmtTest {
    @Test
    fun `fixed-point formatting`() {
        assertEquals("0.110", fmtFixed(0.11, 3))
        assertEquals("0.080", fmtFixed(0.110 - 0.03, 3))
        assertEquals("128", fmtFixed(128.0, 0))
        assertEquals("98", fmtFixed(128.0 - 30.0, 0))
        assertEquals("0.55", fmtFixed(0.55, 2))
        assertEquals("1.000", fmtFixed(0.9996, 3))
        assertEquals("0.000", fmtFixed(0.0, 3))
        assertEquals("0.000", fmtFixed(-0.0, 3))
        assertEquals("-0.500", fmtFixed(-0.5, 3))
        assertEquals("2", fmtFixed(2.5, 0))   // half-to-even
        assertEquals("4", fmtFixed(3.5, 0))
        assertEquals("0.001", fmtFixed(0.0005 + 0.0001, 3))
    }

    @Test
    fun `hex2 is two lowercase hex digits`() {
        assertEquals("00", hex2(0)); assertEquals("0a", hex2(10)); assertEquals("ff", hex2(255))
    }
}
