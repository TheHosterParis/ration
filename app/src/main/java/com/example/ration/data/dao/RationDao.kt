package com.example.ration.data.dao

import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Embedded
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.ration.data.model.Component
import com.example.ration.data.model.Ration
import com.example.ration.data.model.RationComponentCrossRef
import com.example.ration.data.model.RationDetail
import com.example.ration.data.model.RationWithComponents

@Dao
interface RationDao {

    @Insert
    suspend fun insert(ration: Ration): Long

    @Insert
    suspend fun insertAll(ration: List<Ration>): List<Long>

    @Insert
    suspend fun insertRationAlimentCrossRef(crossRef: RationComponentCrossRef)

    @Update
    suspend fun update(ration: Ration)

    @Delete
    suspend fun delete(ration: Ration)

    @Query("SELECT * FROM rations")
    suspend fun getAllRations(): List<Ration>

    @Query("SELECT * FROM rations WHERE idRation = :rationId")
    suspend fun getRationById(rationId: Long): Ration?

    @Transaction
    @Query("SELECT * FROM rations WHERE idRation = :rationId")
    suspend fun getRationWithAliments(rationId: Long): List<RationWithComponents>

    @Transaction
    @Query("SELECT rations.*, components.*, ration_component_cross_ref.quantity FROM rations JOIN ration_component_cross_ref ON rations.idRation = ration_component_cross_ref.idRation JOIN components ON components.idComponent = ration_component_cross_ref.idComponent WHERE rations.idRation = :rationId")
    fun getRationWithComponents(rationId: Long): List<RationDetail>
    // Ajoutez d'autres méthodes de requête selon les besoins
}
