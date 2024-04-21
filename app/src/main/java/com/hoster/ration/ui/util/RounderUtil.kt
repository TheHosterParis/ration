package com.hoster.ration.ui.util

import java.math.BigDecimal
import java.math.RoundingMode

class RounderUtil {

    fun roundToPrecision(number: Double, precision: Int): Double {
        // Convertir le nombre en BigDecimal
        val bigDecimal = BigDecimal.valueOf(number)

        // Calculer le nombre de chiffres après la virgule en fonction du pas
        // Correction : Utiliser directement la précision fournie

        // Arrondir le nombre à la précision souhaitée
        val rounded = bigDecimal.setScale(precision, RoundingMode.HALF_UP)

        // Convertir le BigDecimal arrondi en Double
        return rounded.toDouble()
    }
}