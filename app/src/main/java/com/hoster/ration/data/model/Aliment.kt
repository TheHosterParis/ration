package com.hoster.ration.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Index

@Entity(tableName = "aliments",
    indices = [Index(value = ["idAliment", "alimentName"])])
data class Aliment(
    @PrimaryKey(autoGenerate = true) var idAliment: Long = 0,
    var alimentName: String,
    var description: String?,
    var averageQuantity: Double,
    var unit: String,
    var step: Double
)
