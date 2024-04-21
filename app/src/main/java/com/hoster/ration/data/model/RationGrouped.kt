package com.hoster.ration.data.model

import java.io.Serializable

data class RationGrouped(
    val idRation: Long,
    val rationName: String,
    val numberOfAnimals: Int,
    val date: Long,
    val animalId: Long,
    val preparationId: Long,
    val animalName: String,
    val aliments: List<AlimentQuantity>
) : Serializable

data class AlimentQuantity(
    val idAliment: Long,
    val alimentName: String,
    var quantity: Double,
    val unit: String,
    val pas: Double,
    val quantityParRation: Double
) : Serializable
