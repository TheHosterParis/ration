package com.example.ration.data.model

import java.io.Serializable

data class RationGrouped(
    val idRation: Long,
    val rationName: String,
    val numberOfAnimals: Int,
    val date: Long,
    val animalId: Long,
    val animalName: String,
    val components: List<AlimentQuantity>
) : Serializable

data class AlimentQuantity(
    val idAliment: Long,
    val alimentName: String,
    val quantity: Double,
    val unit: String,
    val pas: Double
) : Serializable
