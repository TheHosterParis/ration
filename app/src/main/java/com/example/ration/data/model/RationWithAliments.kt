package com.example.ration.data.model

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class RationWithAliments(
    @Embedded val ration: Ration,
    @Relation(
        parentColumn = "idRation",
        entityColumn = "idAliment",
        associateBy = Junction(RationAlimentCrossRef::class)
    )
    var aliments: List<Aliment>
)
