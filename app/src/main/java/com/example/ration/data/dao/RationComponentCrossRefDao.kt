package com.example.ration.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.ration.data.model.Animal
import com.example.ration.data.model.Component
import com.example.ration.data.model.RationComponentCrossRef

@Dao
interface RationComponentCrossRefDao {

    @Insert
    suspend fun insert(crossRef: RationComponentCrossRef)

    @Insert
    suspend fun insertAll(component: List<RationComponentCrossRef>): List<Long>

    @Update
    suspend fun update(component: RationComponentCrossRef)

    @Delete
    suspend fun delete(component: RationComponentCrossRef)

    @Query("SELECT * FROM ration_component_cross_ref")
    suspend fun getAllComponents(): List<RationComponentCrossRef>

    // Ajoutez d'autres méthodes de requête selon les besoins
}
