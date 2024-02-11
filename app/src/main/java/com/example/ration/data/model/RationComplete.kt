package com.example.ration.data.model

data class RationComplete(
    val idRation: Long,
    val rationName: String,
    val date: Long,
    val animalId: Long,
    val animalName: String,
    val idAliment: Long,
    val alimentName: String,
    val quantity: Double,
    val unit: String,
    val pas: Double
)
