package com.hoster.ration.data.model

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "rations",
    indices = [Index(value = ["animalId", "rationName", "idRation"])])
data class Ration(
    @PrimaryKey(autoGenerate = true) var idRation: Long = 0,
    var rationName: String,
    var date: Long,
    var animalId: Long,
    var numberOfAnimals: Int,
    var information: String?
)
