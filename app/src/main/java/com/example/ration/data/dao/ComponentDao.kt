package com.example.ration.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.ration.data.model.Animal
import com.example.ration.data.model.Component

@Dao
interface ComponentDao {

    @Insert
    suspend fun insert(component: Component)

    @Insert
    suspend fun insertAll(component: List<Component>): List<Long>

    @Update
    suspend fun update(component: Component)

    @Delete
    suspend fun delete(component: Component)

    @Query("SELECT * FROM components")
    suspend fun getAllComponents(): List<Component>

    @Query("SELECT * FROM components WHERE idComponent = :componentId")
    suspend fun getComponentById(componentId: Long): Component?

    // Ajoutez d'autres méthodes de requête selon les besoins
}
