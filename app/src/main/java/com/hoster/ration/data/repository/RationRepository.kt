package com.hoster.ration.data.repository

import com.hoster.ration.data.dao.RationDao
import com.hoster.ration.data.model.AlimentQuantity
import com.hoster.ration.data.model.RationComplete
import com.hoster.ration.data.model.RationGrouped

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
                    numberOfAnimals = first.numberOfAnimals,
                    date = first.date,
                    animalId = first.animalId,
                    animalName = first.animalName,
                    aliments = entry.value.map { aliment ->
                        AlimentQuantity(
                            idAliment = aliment.idAliment,
                            alimentName = aliment.alimentName,
                            quantity = aliment.quantity,
                            unit = aliment.unit,
                            pas = aliment.pas,
                            quantityParRation = aliment.quantityParRation
                        )
                    }
                )
            }
    }
}
