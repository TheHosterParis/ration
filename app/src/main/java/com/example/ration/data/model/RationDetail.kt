package com.example.ration.data.model

import androidx.room.ColumnInfo
import androidx.room.Embedded

data class RationDetail(
    @Embedded val ration: Ration,
    @Embedded val component: Component,
    @ColumnInfo(name = "quantity") val quantity: Double
)
