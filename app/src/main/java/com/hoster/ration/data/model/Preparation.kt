package com.hoster.ration.data.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "preparations",
    foreignKeys = [ForeignKey(
        entity = Ration::class,
        parentColumns = ["idRation"],
        childColumns = ["rationId"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class Preparation(
    @PrimaryKey(autoGenerate = true) val idPreparation: Long = 0,
    val name: String,
    val rationId: Long
)
