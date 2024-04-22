package com.hoster.ration.ui.util

import java.math.BigDecimal
import java.math.RoundingMode

class FormuleUtil {

    fun ComputeQuantity(numberOfAnimals: Int, step: Double): Double {
        return numberOfAnimals * step
    }
}