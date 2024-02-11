package com.example.ration.ui.ration

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ration.R
import com.example.ration.data.dao.RationDao
import com.example.ration.data.database.AppDatabase
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RationHistoryActivity : AppCompatActivity() {
    private lateinit var rationsRecyclerView: RecyclerView
    private lateinit var adapter: RationListAdapter
    private val rationDao: RationDao by lazy {
        AppDatabase.getDatabase(this).rationDao()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ration_list)

        // Correctly initialize the class property instead of creating a local variable
        rationsRecyclerView = findViewById(R.id.rationsRecyclerView)
        rationsRecyclerView.layoutManager = LinearLayoutManager(this)

        // Initialize the adapter with an empty list
        adapter = RationListAdapter(emptyList())
        rationsRecyclerView.adapter = adapter

        loadRations()
        loadRationComplete()
        findViewById<FloatingActionButton>(R.id.addRationFAB).setOnClickListener {
            // Ici, vous pouvez démarrer RationDetailActivity pour ajouter un nouvel ration
        }
    }

    private fun loadRations() {
        CoroutineScope(Dispatchers.Main).launch {
            val rations = withContext(Dispatchers.IO) {
                rationDao.getAllRations()
            }

            // Log the retrieved rations
            Log.d("RationListActivity", "Loaded rations: ${rations.size}")
            rations.forEach { ration ->
                Log.d("RationListActivity", "Ration: $ration") // Assurez-vous que la classe Ration a un attribut name ou modifiez cette ligne pour afficher l'information pertinente
            }

            adapter.updateRations(rations)
        }
    }
    private fun loadRationComplete() {
        CoroutineScope(Dispatchers.Main).launch {
            val rations = withContext(Dispatchers.IO) {
                rationDao.getAllRationDetails()
            }

            // Log the retrieved rations
            Log.d("getAllRationDetails", "Loaded rations: ${rations.size}")
            rations.forEach { ration ->
                Log.d("getAllRationDetails", "Ration: $ration") // Assurez-vous que la classe Ration a un attribut name ou modifiez cette ligne pour afficher l'information pertinente
            }

            //adapter.updateRations(rations)
        }
    }
}