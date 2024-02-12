package com.hoster.ration.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.hoster.ration.data.model.Ration
import com.hoster.ration.data.model.RationAlimentCrossRef
import com.hoster.ration.data.model.RationComplete
import com.hoster.ration.data.model.RationDetail
import com.hoster.ration.data.model.RationWithAliments

@Dao
interface RationDao {

    @Insert
    suspend fun insert(ration: Ration): Long

    @Insert
    suspend fun insertAll(ration: List<Ration>): List<Long>

    @Insert
    suspend fun insertRationAlimentCrossRef(crossRef: RationAlimentCrossRef)

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
    suspend fun getRationWithAliments(rationId: Long): List<RationWithAliments>

    @Transaction
    @Query("SELECT rations.*, aliments.*, ration_aliment_cross_ref.quantity, ration_aliment_cross_ref.quantityParRation FROM rations JOIN ration_aliment_cross_ref ON rations.idRation = ration_aliment_cross_ref.idRation JOIN aliments ON aliments.idAliment = ration_aliment_cross_ref.idAliment WHERE rations.idRation = :rationId")
    fun getRationDetailWithAliments(rationId: Long): List<RationDetail>
    // Ajoutez d'autres méthodes de requête selon les besoins

    @Transaction
    @Query("""
        SELECT 
            r.idRation,
            r.rationName,
            r.numberOfAnimals,
            r.date,
            a.id AS animalId,
            a.name AS animalName,
            al.idAliment,
            al.alimentName,
            rac.quantity,
            rac.unit,
            rac.pas,
            rac.quantityParRation
        FROM 
            rations r
        INNER JOIN 
            animals a ON r.animalId = a.id
        INNER JOIN 
            ration_aliment_cross_ref rac ON r.idRation = rac.idRation
        INNER JOIN 
            aliments al ON rac.idAliment = al.idAliment
    """)
    suspend fun getAllRationDetails(): List<RationComplete>
}
