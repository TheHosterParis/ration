package com.example.ration.ui.aliment

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ration.R
import com.example.ration.data.dao.AlimentDao
import com.example.ration.data.database.AppDatabase
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AlimentListActivity : AppCompatActivity() {
    private lateinit var alimentsRecyclerView: RecyclerView
    private lateinit var adapter: AlimentListAdapter
    private val alimentDao: AlimentDao by lazy {
        AppDatabase.getDatabase(this).alimentDao()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_aliment_list)

        // Correctly initialize the class property instead of creating a local variable
        alimentsRecyclerView = findViewById(R.id.alimentsRecyclerView)
        alimentsRecyclerView.layoutManager = LinearLayoutManager(this)

        // Initialize the adapter with an empty list
        adapter = AlimentListAdapter(emptyList())
        alimentsRecyclerView.adapter = adapter

        loadAliments()

        findViewById<FloatingActionButton>(R.id.addAlimentFAB).setOnClickListener {
            // Ici, vous pouvez démarrer AlimentDetailActivity pour ajouter un nouvel aliment
        }
    }

    private fun loadAliments() {
        CoroutineScope(Dispatchers.Main).launch {
            val aliments = withContext(Dispatchers.IO) {
                alimentDao.getAllAliments()
            }

            // Log the retrieved aliments
            Log.d("AlimentListActivity", "Loaded aliments: ${aliments.size}")
            aliments.forEach { aliment ->
                Log.d("AlimentListActivity", "Aliment: ${aliment.alimentName}") // Assurez-vous que la classe Aliment a un attribut name ou modifiez cette ligne pour afficher l'information pertinente
            }

            adapter.updateAliments(aliments)
        }
    }
}
