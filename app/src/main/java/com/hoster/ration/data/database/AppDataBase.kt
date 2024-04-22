package com.hoster.ration.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.hoster.ration.data.dao.AlimentDao
import com.hoster.ration.data.dao.AnimalDao
import com.hoster.ration.data.dao.PreparationDao
import com.hoster.ration.data.dao.RationAlimentCrossRefDao
import com.hoster.ration.data.dao.RationDao
import com.hoster.ration.data.model.Aliment
import com.hoster.ration.data.model.Animal
import com.hoster.ration.data.model.Preparation
import com.hoster.ration.data.model.PreparationStep
import com.hoster.ration.data.model.Ration
import com.hoster.ration.data.model.RationAlimentCrossRef
import com.hoster.ration.data.util.Converters
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

// Assurez-vous d'importer vos classes d'entités et DAO ici

@Database(entities = [Animal::class, Aliment::class, Ration::class, RationAlimentCrossRef::class, Preparation::class, PreparationStep::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun animalDao(): AnimalDao
    abstract fun alimentDao(): AlimentDao
    abstract fun rationDao(): RationDao
    abstract fun preparationDao(): PreparationDao
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
                    .fallbackToDestructiveMigration()
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
                        database.preparationDao().insertAll(PREPOPULATE_PREPARATION)
                        database.preparationDao().insertAllSteps(PREPOPULATE_PREPARATION_STEPS)
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
            Ration(rationName = "Ration vaches en lactation", date = System.currentTimeMillis(), animalId = 1, numberOfAnimals = 130, information = "lactation 1"),
            Ration(rationName = "Ration vaches en lactation du 3 juin", date = 1685743200, animalId = 1, numberOfAnimals = 140, information = "lactation 2"),
            Ration(rationName = "Ration vaches taries", date = System.currentTimeMillis(), animalId = 2, numberOfAnimals = 4, information = "vache tarie "),
            Ration(rationName = "Ration vaches prépa vélage", date = System.currentTimeMillis(), animalId = 4, numberOfAnimals = 20, information = "Ration prepa velage "),
            Ration(rationName = "Ration génisse", date = System.currentTimeMillis(), animalId = 3, numberOfAnimals = 60, information = "Ration genisse"),
            // Ajoutez plus selon les besoins
            Ration(rationName = "Ration vaches en lactation Démo", date = System.currentTimeMillis(), animalId = 1, numberOfAnimals = 130, information = "lactation demo"),

        )

        val PREPOPULATE_ALIMENTS = listOf(

            Aliment(alimentName = "Sodagrain", description = "", unit = "KG", step = 2.5, ordre = 1),
            Aliment(alimentName = "Régalin", description = "", unit = "KG", step = 1.5, ordre = 2),
            Aliment(alimentName = "Minéraux", description = "Minéraux", unit = "KG", step = 0.3, ordre = 3), // Pas supposé 1.0 par défaut si non spécifié
            Aliment(alimentName = "Carbonate", description = "Carbonate", unit = "KG", step = 0.14, ordre = 4), // Pas supposé 1.0 par défaut si non spécifié
            Aliment(alimentName = "Sel", description = "Sel", unit = "KG", step = 0.06, ordre = 5),
            Aliment(alimentName = "Urée", description = "Urée", unit = "KG", step = 0.08, ordre = 6),
            Aliment(alimentName = "Soja", description = "Soja", unit = "KG", step = 2.0, ordre = 7),
            Aliment(alimentName = "Colza", description = "Colza", unit = "KG", step = 3.0, ordre = 8),
            Aliment(alimentName = "enrubannage", description = "Enrubannage", unit = "KG", step = 2.66, ordre = 9),
            Aliment(alimentName = "ensilage_de_seigle", description = "Ensilage de Seigle", unit = "KG", step = 7.0, ordre = 10),
            Aliment(alimentName = "Méthio_protect", description = "Méthio protect", unit = "KG", step = 0.06, ordre = 11),
            Aliment(alimentName = "ensilage_de_mais", description = "Ensilage de Maïs", unit = "KG", step = 52.0, ordre = 12)
        )
        val PREPOPULATE_PREPARATION_STEPS = listOf(
            PreparationStep(description = "Rajouter Méthio Protect et Ensilage de Maïs", name = "Matin", preparationId = 1, order = 1),
            PreparationStep(description = "Si plus de 1 godet de refus donner aux génisses", name = "Refus", preparationId = 1, order = 2),
            PreparationStep(description = "Benner le blé dans la mélangeuse", name = "Mélangeuse", preparationId = 2, order = 1),
            PreparationStep(description = "Peser le reste des compléments et petits sacs", name = "Mélange compléments", preparationId = 2, order = 2)
        )

        val PREPOPULATE_PREPARATION = listOf(
            Preparation(name =  "Preparation", rationId = 1),
            Preparation(name = "Mélangeuse", rationId = 2)
        )

        val PREPOPULATE_RATION_ALIMENT_CROSS_REF = listOf(
            RationAlimentCrossRef(idRation = 1, idAliment = 1, unit = "KG", pas = 0.1, 3.2),
            RationAlimentCrossRef(idRation = 1, idAliment = 2, unit = "KG", pas = 0.1, 1.8),
            RationAlimentCrossRef(idRation = 1, idAliment = 3, unit = "KG", pas = 0.1, 3.6),
            RationAlimentCrossRef(idRation = 1, idAliment = 4, unit = "KG", pas = 0.1, 10.0),
            RationAlimentCrossRef(idRation = 1, idAliment = 5, unit = "KG", pas = 1.0, 32.5),
            RationAlimentCrossRef(idRation = 2, idAliment = 1, unit = "KG", pas = 0.1, 3.0),
            RationAlimentCrossRef(idRation = 2, idAliment = 10, unit = "KG", pas = 0.1, 1.2),
            RationAlimentCrossRef(idRation = 2, idAliment = 6, unit = "KG", pas = 0.1, 0.3),
            RationAlimentCrossRef(idRation = 2, idAliment = 11, unit = "KG", pas = 0.1, 0.09),
            RationAlimentCrossRef(idRation = 2, idAliment = 8, unit = "KG", pas = 0.1, 0.06),
            RationAlimentCrossRef(idRation = 2, idAliment = 9, unit = "KG", pas = 0.1, 0.1),
            RationAlimentCrossRef(idRation = 2, idAliment = 3, unit = "KG", pas = 1.0, 1.4),
            RationAlimentCrossRef(idRation = 2, idAliment = 2, unit = "KG", pas = 1.0, 3.6),
            RationAlimentCrossRef(idRation = 2, idAliment = 4, unit = "KG", pas = 1.0, 2.9),
            // Assurez-vous que les ID correspondent à ceux des rations et des aliments pré-remplis
            // Ajoutez plus selon les besoins
            RationAlimentCrossRef(idRation = 6, idAliment = 1, unit = "KG", pas = 1.0, 2.5),
            RationAlimentCrossRef(idRation = 6, idAliment = 2, unit = "KG", pas = 1.0, 1.5),
            RationAlimentCrossRef(idRation = 6, idAliment = 3, unit = "KG", pas = 1.0, 0.3),
            RationAlimentCrossRef(idRation = 6, idAliment = 4, unit = "KG", pas = 1.0, 0.14),
            RationAlimentCrossRef(idRation = 6, idAliment = 5, unit = "KG", pas = 1.0, 0.06),
            RationAlimentCrossRef(idRation = 6, idAliment = 6, unit = "KG", pas = 1.0, 0.08),
            RationAlimentCrossRef(idRation = 6, idAliment = 7, unit = "KG", pas = 1.0, 2.0),
            RationAlimentCrossRef(idRation = 6, idAliment = 8, unit = "KG", pas = 1.0, 3.0),
            RationAlimentCrossRef(idRation = 6, idAliment = 9, unit = "KG", pas = 1.0, 2.66),
            RationAlimentCrossRef(idRation = 6, idAliment = 10, unit = "KG", pas = 1.0, 7.0),
            RationAlimentCrossRef(idRation = 6, idAliment = 11, unit = "KG", pas = 1.0, 0.06),
            RationAlimentCrossRef(idRation = 6, idAliment = 12, unit = "KG", pas = 1.0, 52.0)
        )
    }
}
