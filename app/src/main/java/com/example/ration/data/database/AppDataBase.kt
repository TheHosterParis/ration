package com.example.ration.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.ration.data.dao.AnimalDao
import com.example.ration.data.dao.ComponentDao
import com.example.ration.data.dao.RationDao
import com.example.ration.data.model.Animal
import com.example.ration.data.model.Component
import com.example.ration.data.model.Ration
import com.example.ration.data.util.Converters
import android.content.Context
import androidx.room.Room
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.ration.data.dao.RationComponentCrossRefDao
import com.example.ration.data.model.RationComponentCrossRef
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

// Assurez-vous d'importer vos classes d'entités et DAO ici

@Database(entities = [Animal::class, Component::class, Ration::class, RationComponentCrossRef::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun animalDao(): AnimalDao
    abstract fun componentDao(): ComponentDao
    abstract fun rationDao(): RationDao
    abstract fun rationComponentCrossRefDao(): RationComponentCrossRefDao

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
                        database.componentDao().insertAll(PREPOPULATE_COMPONENTS)
                        PREPOPULATE_RATION_COMPONENT_CROSS_REF.forEach {
                            database.rationComponentCrossRefDao().insert(it)
                        }
                    }
                }
            }
        }

        // Données de pré-remplissage
        private val PREPOPULATE_ANIMALS = listOf(
            Animal(name = "vacheLait", category = "bovin", imageUrl = "cowmilk", description = "Description de la vache laitiere"),
            Animal(name = "vacheViande", category = "bovin", imageUrl = "cow", description = "Description de la vache a viande"),
            Animal(name = "chevre", category = "ovin", imageUrl = "goat", description = "Description de la chevre"),
            Animal(name = "cochon", category = "Porcin", imageUrl = "pig", description = "Description du cochon")
            // Ajoutez plus si nécessaire
        )

        val PREPOPULATE_RATIONS = listOf(
            Ration(rationName = "Ration vache laitière Matin", date = System.currentTimeMillis(), animalId = 1, numberOfAnimals = 30),
            Ration(rationName = "Ration vache laitière Soir", date = System.currentTimeMillis(), animalId = 1, numberOfAnimals = 30),
            Ration(rationName = "Ration vache laitière Matin", date = System.currentTimeMillis(), animalId = 2, numberOfAnimals = 20),
            Ration(rationName = "Ration vache laitière Soir", date = System.currentTimeMillis(), animalId = 2, numberOfAnimals = 20),
            // Ajoutez plus selon les besoins
        )

        val PREPOPULATE_COMPONENTS = listOf(
            Component(componentName = "blé", description = "du blé tendre", averageQuantity = 3.2, unit = "KG", step = 0.1),
            Component(componentName = "Soja", description = "Soja", averageQuantity = 1.8, unit = "KG", step = 0.1),
            Component(componentName = "colza", description = "colza", averageQuantity = 3.6, unit = "KG", step = 0.1),
            Component(componentName = "herbe", description = "herbe", averageQuantity = 10.0, unit = "KG", step = 0.1),
            Component(componentName = "maïs", description = "maïs", averageQuantity = 32.5, unit = "KG", step = 1.0),
            Component(componentName = "mineraux", description = "mineraux", averageQuantity = 4.0, unit = "KG", step = 1.0), // Pas supposé 1.0 par défaut si non spécifié
            Component(componentName = "calcium", description = "calcium", averageQuantity = 1.0, unit = "KG", step = 1.0),
            Component(componentName = "sel", description = "sel", averageQuantity = 0.33, unit = "sac", step = 1.0),
            Component(componentName = "uree", description = "uree", averageQuantity = 4.0, unit = "sac", step = 1.0)
        )

        val PREPOPULATE_RATION_COMPONENT_CROSS_REF = listOf(
            RationComponentCrossRef(idRation = 1, idComponent = 1, quantity = 5.0),
            RationComponentCrossRef(idRation = 1, idComponent = 2, quantity = 3.0),
            RationComponentCrossRef(idRation = 2, idComponent = 3, quantity = 4.5),
            // Assurez-vous que les ID correspondent à ceux des rations et des aliments pré-remplis
            // Ajoutez plus selon les besoins
        )
    }
}
