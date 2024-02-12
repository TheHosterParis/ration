package com.hoster.ration.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.hoster.ration.data.dao.AnimalDao
import com.hoster.ration.data.dao.AlimentDao
import com.hoster.ration.data.dao.RationDao
import com.hoster.ration.data.model.Animal
import com.hoster.ration.data.model.Aliment
import com.hoster.ration.data.model.Ration
import com.hoster.ration.data.util.Converters
import android.content.Context
import androidx.room.Room
import androidx.sqlite.db.SupportSQLiteDatabase
import com.hoster.ration.data.dao.RationAlimentCrossRefDao
import com.hoster.ration.data.model.RationAlimentCrossRef
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

// Assurez-vous d'importer vos classes d'entités et DAO ici

@Database(entities = [Animal::class, Aliment::class, Ration::class, RationAlimentCrossRef::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun animalDao(): AnimalDao
    abstract fun alimentDao(): AlimentDao
    abstract fun rationDao(): RationDao
    abstract fun rationAlimentCrossRefDao(): RationAlimentCrossRefDao

    // Ajoutez ici toute autre configuration spécifique à la base de données si nécessaire
    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "ration_database"
                )
                    .addCallback(roomDatabaseCallback)
                    .build()
                INSTANCE = instance
                instance
            }
        }

        private val roomDatabaseCallback = object : Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                CoroutineScope(Dispatchers.IO).launch {
                    INSTANCE?.let { database ->
                        database.animalDao().insertAll(PREPOPULATE_ANIMALS)
                        database.rationDao().insertAll(PREPOPULATE_RATIONS)
                        database.alimentDao().insertAll(PREPOPULATE_ALIMENTS)
                        PREPOPULATE_RATION_ALIMENT_CROSS_REF.forEach {
                            database.rationAlimentCrossRefDao().insert(it)
                        }
                    }
                }
            }
        }

        // Données de pré-remplissage
        private val PREPOPULATE_ANIMALS = listOf(
            Animal(name = "vacheLait", animalId = 1, imageUrl = "cowmilk", description = "Description de la vache laitiere"),
            Animal(name = "vacheLaitTarie", animalId = 2, imageUrl = "cowmilk", description = "Description de la vache laitiere"),
            Animal(name = "vacheLaitGenisse", animalId = 3, imageUrl = "cowmilk", description = "Description de la vache laitiere"),
            Animal(name = "vacheLaitPrepaVelage", animalId = 4, imageUrl = "cowmilk", description = "Description de la vache laitiere"),
            // Ajoutez plus si nécessaire
        )

        val PREPOPULATE_RATIONS = listOf(
            Ration(rationName = "Ration vaches en lactation", date = System.currentTimeMillis(), animalId = 1, numberOfAnimals = 140, information = "lactation 1"),
            Ration(rationName = "Ration vaches en lactation du 3 juin", date = 1685743200, animalId = 1, numberOfAnimals = 140, information = "lactation 2"),
            Ration(rationName = "Ration vaches taries", date = System.currentTimeMillis(), animalId = 2, numberOfAnimals = 4, information = "vache tarie "),
            Ration(rationName = "Ration vaches prépa vélage", date = System.currentTimeMillis(), animalId = 4, numberOfAnimals = 20, information = "Ration prepa velage "),
            Ration(rationName = "Ration génisse", date = System.currentTimeMillis(), animalId = 3, numberOfAnimals = 60, information = "Ration genisse"),
            // Ajoutez plus selon les besoins
        )

        val PREPOPULATE_ALIMENTS = listOf(
            Aliment(alimentName = "blé", description = "du blé tendre", averageQuantity = 3.2, unit = "KG", step = 0.1),
            Aliment(alimentName = "Soja", description = "Soja", averageQuantity = 1.8, unit = "KG", step = 0.1),
            Aliment(alimentName = "colza", description = "colza", averageQuantity = 3.6, unit = "KG", step = 0.1),
            Aliment(alimentName = "herbe", description = "herbe", averageQuantity = 10.0, unit = "KG", step = 0.1),
            Aliment(alimentName = "maïs", description = "maïs", averageQuantity = 32.5, unit = "KG", step = 1.0),
            Aliment(alimentName = "mineraux", description = "mineraux", averageQuantity = 0.3, unit = "KG", step = 0.1), // Pas supposé 1.0 par défaut si non spécifié
            Aliment(alimentName = "calcium", description = "calcium", averageQuantity = 1.0, unit = "KG", step = 1.0),
            Aliment(alimentName = "sel", description = "sel", averageQuantity = 0.06, unit = "KG", step = 0.1),
            Aliment(alimentName = "uree", description = "uree", averageQuantity = 4.0, unit = "KG", step = 1.0),
            Aliment(alimentName = "lin", description = "lin", averageQuantity = 1.2, unit = "KG", step = 0.1),
            Aliment(alimentName = "carbonateCalcium", description = "carbonateCalcium", averageQuantity = 0.09, unit = "KG", step = 0.1)
        )

        val PREPOPULATE_RATION_ALIMENT_CROSS_REF = listOf(
            RationAlimentCrossRef(idRation = 1, idAliment = 1, quantity = 448.0, unit = "KG", pas = 0.1, 3.2),
            RationAlimentCrossRef(idRation = 1, idAliment = 2, quantity = 252.0, unit = "KG", pas = 0.1, 1.8),
            RationAlimentCrossRef(idRation = 1, idAliment = 3, quantity = 504.0, unit = "KG", pas = 0.1, 3.6),
            RationAlimentCrossRef(idRation = 1, idAliment = 4, quantity = 1400.0, unit = "KG", pas = 0.1, 10.0),
            RationAlimentCrossRef(idRation = 1, idAliment = 5, quantity = 4550.0, unit = "KG", pas = 1.0, 32.5),
            RationAlimentCrossRef(idRation = 2, idAliment = 1, quantity = 420.0, unit = "KG", pas = 0.1, 3.0),
            RationAlimentCrossRef(idRation = 2, idAliment = 10, quantity = 165.0, unit = "KG", pas = 0.1, 1.2),
            RationAlimentCrossRef(idRation = 2, idAliment = 6, quantity = 42.0, unit = "KG", pas = 0.1, 0.3),
            RationAlimentCrossRef(idRation = 2, idAliment = 11, quantity = 12.0, unit = "KG", pas = 0.1, 0.09),
            RationAlimentCrossRef(idRation = 2, idAliment = 8, quantity = 8.0, unit = "KG", pas = 0.1, 0.06),
            RationAlimentCrossRef(idRation = 2, idAliment = 9, quantity = 14.0, unit = "KG", pas = 0.1, 0.1),
            RationAlimentCrossRef(idRation = 2, idAliment = 3, quantity = 200.0, unit = "KG", pas = 1.0, 1.4),
            RationAlimentCrossRef(idRation = 2, idAliment = 2, quantity = 500.0, unit = "KG", pas = 1.0, 3.6),
            RationAlimentCrossRef(idRation = 2, idAliment = 4, quantity = 400.0, unit = "KG", pas = 1.0, 2.9),
            // Assurez-vous que les ID correspondent à ceux des rations et des aliments pré-remplis
            // Ajoutez plus selon les besoins
        )
    }
}
