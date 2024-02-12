package com.hoster.ration.data.model

import androidx.room.ColumnInfo
import androidx.room.Embedded

data class RationDetail(
    @Embedded val ration: Ration,
    @Embedded val aliment: Aliment,
    @ColumnInfo(name = "quantity") val quantity: Double
)
