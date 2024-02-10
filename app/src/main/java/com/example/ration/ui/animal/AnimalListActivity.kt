package com.example.ration.ui.animal

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ration.R
import com.example.ration.data.dao.AnimalDao
import com.example.ration.data.database.AppDatabase
import com.example.ration.data.model.Animal
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AnimalListActivity : AppCompatActivity() {
    private lateinit var animalsRecyclerView: RecyclerView
    private lateinit var adapter: AnimalListAdapter
    private val animalDao: AnimalDao by lazy {
        AppDatabase.getDatabase(this).animalDao()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animal_list)

        // Correctly initialize the class property instead of creating a local variable
        animalsRecyclerView = findViewById(R.id.animalsRecyclerView)
        animalsRecyclerView.layoutManager = LinearLayoutManager(this)

        // Initialize the adapter with an empty list
        adapter = AnimalListAdapter(emptyList())
        animalsRecyclerView.adapter = adapter

        loadAnimals()

        findViewById<FloatingActionButton>(R.id.addAnimalFAB).setOnClickListener {
            // Ici, vous pouvez démarrer AnimalDetailActivity pour ajouter un nouvel animal
        }
    }

    private fun loadAnimals() {
        CoroutineScope(Dispatchers.Main).launch {
            val animals = withContext(Dispatchers.IO) {
                animalDao.getAllAnimals()
            }

            // Log the retrieved animals
            Log.d("AnimalListActivity", "Loaded animals: ${animals.size}")
            animals.forEach { animal ->
                Log.d("AnimalListActivity", "Animal: ${animal.name}") // Assurez-vous que la classe Animal a un attribut name ou modifiez cette ligne pour afficher l'information pertinente
            }

            adapter.updateAnimals(animals)
        }
    }
}
