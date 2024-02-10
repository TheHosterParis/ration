package com.example.ration.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.ration.data.model.Animal

@Dao
interface AnimalDao {

    @Insert
    suspend fun insert(animal: Animal): Long

    @Insert
    suspend fun insertAll(animal: List<Animal>): List<Long>
    @Update
    suspend fun update(animal: Animal)

    @Delete
    suspend fun delete(animal: Animal)

    @Query("SELECT * FROM animals")
    suspend fun getAllAnimals(): List<Animal>

    @Query("SELECT * FROM animals WHERE id = :id")
    suspend fun getAnimalById(id: Long): Animal?

    @Query("SELECT * FROM animals WHERE name = :name")
    suspend fun getAnimalByName(name: String): Animal?
    // Ajoutez d'autres méthodes de requête selon les besoins
}
