package com.hoster.ration.data.model

data class RationComplete(
    val idRation: Long,
    val rationName: String,
    val numberOfAnimals: Int,
    val date: Long,
    val animalId: Long,
    val preparationId: Long,
    val animalName: String,
    val idAliment: Long,
    val alimentName: String,
    val unit: String,
    val pas: Double
)
