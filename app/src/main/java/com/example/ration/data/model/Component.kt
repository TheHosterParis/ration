package com.example.ration.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ForeignKey
import androidx.room.Index

@Entity(tableName = "components",
    indices = [Index(value = ["idComponent", "componentName"])])
data class Component(
    @PrimaryKey(autoGenerate = true) var idComponent: Long = 0,
    var componentName: String,
    var description: String?,
    var averageQuantity: Double,
    var unit: String,
    var step: Double
)
