package com.hoster.ration.data.model

import androidx.room.Entity
import androidx.room.Index

@Entity(tableName = "ration_aliment_cross_ref",
    primaryKeys = ["idRation", "idAliment"],
    indices = [Index(value = ["idRation", "idAliment"])])
data class RationAlimentCrossRef(
    var idRation: Long,
    var idAliment: Long,
    var quantity: Double,
    var unit: String,
    var pas: Double,
    var quantityParRation: Double
)
