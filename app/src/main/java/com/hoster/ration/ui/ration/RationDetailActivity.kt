package com.hoster.ration.ui.ration

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hoster.ration.R
import com.hoster.ration.data.model.RationGrouped
import com.hoster.ration.ui.aliment.AlimentDeRationAdapter

class RationDetailActivity : AppCompatActivity() {

    private lateinit var tvRationName: TextView
    private lateinit var tvNumberOfAnimals: TextView
    private lateinit var btnDecrease: Button
    private lateinit var btnIncrease: Button
    private lateinit var tvTotalWeight: TextView
    // Ajoutez un indicateur pour savoir si vous êtes en mode édition
    private var isInEditMode = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ration_detail)

        // Initialisation des vues
        tvRationName = findViewById(R.id.tvRationName)
        tvNumberOfAnimals = findViewById(R.id.tvNumberOfAnimals)
        btnDecrease = findViewById(R.id.btnDecrease)
        btnIncrease = findViewById(R.id.btnIncrease)
        tvTotalWeight = findViewById(R.id.tvTotalWeight)

        // Récupérez l' objet RationGrouped passé via l'Intent
        val rationGrouped = intent.getSerializableExtra("RATION_GROUPED") as RationGrouped

        val alimentsRecyclerView: RecyclerView = findViewById(R.id.rvAliments)
        alimentsRecyclerView.layoutManager = LinearLayoutManager(this)

        // Instance de l'Adapter
        val adapter = AlimentDeRationAdapter(rationGrouped.aliments)
        alimentsRecyclerView.adapter = adapter

        // Récupération et affichage des données passées via l' intent
        tvRationName.text = rationGrouped.rationName
        var numberOfAnimals = rationGrouped.numberOfAnimals
        tvNumberOfAnimals.text = numberOfAnimals.toString()

        // Initialisation de l' affichage avec les données initiales
        updateUI(adapter, numberOfAnimals)

        // Gestion des clics pour modifier le nombre d' animaux
        btnDecrease.setOnClickListener {
            if (numberOfAnimals > 1) {
                numberOfAnimals--
                updateUI(adapter, numberOfAnimals)
            }
        }

        btnIncrease.setOnClickListener {
            numberOfAnimals++
            updateUI(adapter, numberOfAnimals)
        }

        val btnEditQuantities: Button = findViewById(R.id.btnEditQuantities)
        btnEditQuantities.setOnClickListener {
            isInEditMode = !isInEditMode
            adapter.setEditMode(isInEditMode)
        }
    }
    private fun updateUI(adapter: AlimentDeRationAdapter, numberOfAnimals: Int) {
        tvNumberOfAnimals.text = numberOfAnimals.toString()
        adapter.adjustQuantitiesAndRound(numberOfAnimals)
        updateTotalWeight(adapter)
    }

    private fun updateTotalWeight(adapter: AlimentDeRationAdapter) {
        val totalWeight = adapter.getTotalWeight()
        tvTotalWeight.text = getString(R.string.total_kg, totalWeight)
    }
}
