package com.hoster.ration.data.model

import java.io.Serializable

data class RationGrouped(
    val idRation: Long,
    val rationName: String,
    var numberOfAnimals: Int,
    val date: Long,
    val animalId: Long,
    val preparationId: Long,
    val animalName: String,
    var aliments: List<AlimentQuantity>
) : Serializable

data class AlimentQuantity(
    val idAliment: Long,
    val alimentName: String,
    val unit: String,
    var pas: Double,
) : Serializable
