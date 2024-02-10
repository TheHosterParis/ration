package com.example.ration.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ForeignKey
import androidx.room.Index
import java.util.Date

@Entity(
    tableName = "rations",
    indices = [Index(value = ["animalId", "rationName", "idRation"])])
data class Ration(
    @PrimaryKey(autoGenerate = true) var idRation: Long = 0,
    var rationName: String,
    var date: Long,
    var animalId: Long,
    var numberOfAnimals: Int,
)
