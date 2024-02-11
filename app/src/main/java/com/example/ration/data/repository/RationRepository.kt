package com.example.ration.data.repository

import com.example.ration.data.dao.RationDao
import com.example.ration.data.model.AlimentQuantity
import com.example.ration.data.model.RationComplete
import com.example.ration.data.model.RationGrouped

class RationRepository(private val rationDao: RationDao) {

    suspend fun getGroupedRations(): List<RationGrouped> {
        val rations = rationDao.getAllRationDetails()
        return groupRations(rations)
    }

    fun groupRations(rations: List<RationComplete>): List<RationGrouped> {
        return rations.groupBy { it.idRation }
            .map { entry ->
                val first = entry.value.first()
                RationGrouped(
                    idRation = first.idRation,
                    rationName = first.rationName,
                    date = first.date,
                    animalId = first.animalId,
                    animalName = first.animalName,
                    components = entry.value.map { aliment ->
                        AlimentQuantity(
                            idAliment = aliment.idAliment,
                            alimentName = aliment.alimentName,
                            quantity = aliment.quantity,
                            unit = aliment.unit,
                            pas = aliment.pas
                        )
                    }
                )
            }
    }
}
