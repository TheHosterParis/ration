package com.hoster.ration.data.model

data class RationComplete(
    val idRation: Long,
    val rationName: String,
    val numberOfAnimals: Int,
    val date: Long,
    val animalId: Long,
    val animalName: String,
    val idAliment: Long,
    val alimentName: String,
    val quantity: Double,
    val unit: String,
    val pas: Double,
    val quantityParRation: Double
)
