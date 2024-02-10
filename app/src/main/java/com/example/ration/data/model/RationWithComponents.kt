package com.example.ration.data.model

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class RationWithComponents(
    @Embedded val ration: Ration,
    @Relation(
        parentColumn = "idRation",
        entityColumn = "idComponent",
        associateBy = Junction(RationComponentCrossRef::class)
    )
    var components: List<Component>
)
