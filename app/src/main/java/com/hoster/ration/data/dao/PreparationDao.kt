package com.hoster.ration.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import androidx.room.Delete
import com.hoster.ration.data.model.Preparation
import com.hoster.ration.data.model.PreparationStep

@Dao
interface PreparationDao {

    @Insert
    suspend fun insert(preparation: Preparation): Long

    @Insert
    suspend fun insertAll(preparations: List<Preparation>): List<Long>

    @Update
    suspend fun update(preparation: Preparation)

    @Delete
    suspend fun delete(preparation: Preparation)

    @Query("SELECT * FROM preparations")
    suspend fun getAllPreparations(): List<Preparation>

    @Query("SELECT * FROM preparations WHERE idPreparation = :preparationId")
    suspend fun getPreparationById(preparationId: Long): Preparation?

    // Méthodes pour les étapes de préparation
    @Insert
    suspend fun insertStep(step: PreparationStep): Long

    @Insert
    suspend fun insertAllSteps(steps: List<PreparationStep>): List<Long>

    @Update
    suspend fun updateStep(step: PreparationStep)

    @Delete
    suspend fun deleteStep(step: PreparationStep)

    @Query("SELECT * FROM preparation_steps WHERE preparationId = :preparationId")
    suspend fun getStepsByPreparationId(preparationId: Long): List<PreparationStep>

    suspend fun getStepsByPreparationIdOrderedByOrder(preparationId: Long): List<PreparationStep> {
        return getStepsByPreparationId(preparationId).sortedBy { it.order }
    }
}
