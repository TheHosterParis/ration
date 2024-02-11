package com.example.ration.data.model

data class RationGrouped(
    val idRation: Long,
    val rationName: String,
    val date: Long,
    val animalId: Long,
    val animalName: String,
    val components: List<AlimentQuantity>
)

data class AlimentQuantity(
    val idAliment: Long,
    val alimentName: String,
    val quantity: Double,
    val unit: String,
    val pas: Double
)
