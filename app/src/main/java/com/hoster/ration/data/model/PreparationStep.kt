package com.hoster.ration.data.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.PrimaryKey

@Entity(
    tableName = "preparation_steps",
    foreignKeys = [ForeignKey(
        entity = Preparation::class,
        parentColumns = ["idPreparation"],
        childColumns = ["preparationId"],
        onDelete = CASCADE
    )]
)
data class PreparationStep(
    @PrimaryKey(autoGenerate = true)
    val idStep: Long = 0,
    val name: String,
    val description: String,
    val preparationId: Long,
    val order: Int
)
