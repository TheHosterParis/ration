package com.hoster.ration.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.hoster.ration.data.model.RationAlimentCrossRef

@Dao
interface RationAlimentCrossRefDao {

    @Insert
    suspend fun insert(crossRef: RationAlimentCrossRef)

    @Insert
    suspend fun insertAll(aliment: List<RationAlimentCrossRef>): List<Long>

    @Update
    suspend fun update(aliment: RationAlimentCrossRef)

    @Delete
    suspend fun delete(aliment: RationAlimentCrossRef)

    @Query("SELECT * FROM ration_aliment_cross_ref")
    suspend fun getAllAliments(): List<RationAlimentCrossRef>

    // Ajoutez d'autres méthodes de requête selon les besoins
}
