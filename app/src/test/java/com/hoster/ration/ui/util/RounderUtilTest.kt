package com.hoster.ration.ui.util

import org.junit.Assert.assertEquals
import org.junit.Test

class RounderUtilTest {

    private val rounderUtil = RounderUtil()

    @Test
    fun testRoundToPrecision() {
        // Test pour un pas de 1.5, arrondir à 1 chiffre après la virgule
        val result1 = rounderUtil.roundToPrecision(123.456789, 1)
        assertEquals(123.5, result1, 0.0001)

        // Test pour un pas de 10, arrondir à 0 chiffre après la virgule
        val result2 = rounderUtil.roundToPrecision(123.456789, 0)
        assertEquals(123.0, result2, 0.0001)

        // Test pour un pas de 1.15, arrondir à 2 chiffres après la virgule
        val result3 = rounderUtil.roundToPrecision(123.456789, 2)
        assertEquals(123.46, result3, 0.0001)
    }
}
