package com.hoster.ration.data.model

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "animals",
    indices = [Index(value = ["id", "name"])])
data class Animal(
    @PrimaryKey(autoGenerate = true) var id: Long = 0,
    var name: String,
    var animalId: Long,
    var imageUrl: String,
    var description: String
)
