package com.example.ration.ui.component

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ration.R
import com.example.ration.data.dao.ComponentDao
import com.example.ration.data.database.AppDatabase
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ComponentListActivity : AppCompatActivity() {
    private lateinit var componentsRecyclerView: RecyclerView
    private lateinit var adapter: ComponentListAdapter
    private val componentDao: ComponentDao by lazy {
        AppDatabase.getDatabase(this).componentDao()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_component_list)

        // Correctly initialize the class property instead of creating a local variable
        componentsRecyclerView = findViewById(R.id.componentsRecyclerView)
        componentsRecyclerView.layoutManager = LinearLayoutManager(this)

        // Initialize the adapter with an empty list
        adapter = ComponentListAdapter(emptyList())
        componentsRecyclerView.adapter = adapter

        loadComponents()

        findViewById<FloatingActionButton>(R.id.addComponentFAB).setOnClickListener {
            // Ici, vous pouvez démarrer ComponentDetailActivity pour ajouter un nouvel component
        }
    }

    private fun loadComponents() {
        CoroutineScope(Dispatchers.Main).launch {
            val components = withContext(Dispatchers.IO) {
                componentDao.getAllComponents()
            }

            // Log the retrieved components
            Log.d("ComponentListActivity", "Loaded components: ${components.size}")
            components.forEach { component ->
                Log.d("ComponentListActivity", "Component: ${component.componentName}") // Assurez-vous que la classe Component a un attribut name ou modifiez cette ligne pour afficher l'information pertinente
            }

            adapter.updateComponents(components)
        }
    }
}
