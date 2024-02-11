package com.example.ration.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.ration.data.model.Aliment

@Dao
interface AlimentDao {

    @Insert
    suspend fun insert(aliment: Aliment)

    @Insert
    suspend fun insertAll(aliment: List<Aliment>): List<Long>

    @Update
    suspend fun update(aliment: Aliment)

    @Delete
    suspend fun delete(aliment: Aliment)

    @Query("SELECT * FROM aliments")
    suspend fun getAllAliments(): List<Aliment>

    @Query("SELECT * FROM aliments WHERE idAliment = :alimentId")
    suspend fun getAlimentById(alimentId: Long): Aliment?

    // Ajoutez d'autres méthodes de requête selon les besoins
}
