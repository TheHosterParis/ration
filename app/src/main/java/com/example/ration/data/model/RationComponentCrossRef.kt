package com.example.ration.data.model

import androidx.room.Entity
import androidx.room.Index

@Entity(tableName = "ration_component_cross_ref",
    primaryKeys = ["idRation", "idComponent"],
    indices = [Index(value = ["idRation", "idComponent"])])
data class RationComponentCrossRef(
    var idRation: Long,
    var idComponent: Long,
    var quantity: Double
)
