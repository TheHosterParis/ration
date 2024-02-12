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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ration_detail)

        // Initialisation des vues
        tvRationName = findViewById(R.id.tvRationName)
        tvNumberOfAnimals = findViewById(R.id.tvNumberOfAnimals)
        btnDecrease = findViewById(R.id.btnDecrease)
        btnIncrease = findViewById(R.id.btnIncrease)

        // Récupérez l'objet RationGrouped passé via l'Intent
        val rationGrouped = intent.getSerializableExtra("RATION_GROUPED") as RationGrouped

        val componentsRecyclerView: RecyclerView = findViewById(R.id.rvAliments)
        componentsRecyclerView.layoutManager = LinearLayoutManager(this)
        // Supposons que rationGrouped.components contient déjà la liste des composants
        val adapter = AlimentDeRationAdapter(rationGrouped.components)
        componentsRecyclerView.adapter = adapter
        // Récupération et affichage des données passées via l'intent
        tvRationName.text = rationGrouped.rationName
        var numberOfAnimals = rationGrouped.numberOfAnimals
        tvNumberOfAnimals.text = numberOfAnimals.toString()

        // Gestion des clics pour modifier le nombre d'animaux
        btnDecrease.setOnClickListener {
            if (numberOfAnimals > 0) {
                numberOfAnimals--
                tvNumberOfAnimals.text = numberOfAnimals.toString()
                // Mettez à jour la liste des aliments ici en fonction du nouveau nombre d'animaux
            }
        }

        btnIncrease.setOnClickListener {
            numberOfAnimals++
            tvNumberOfAnimals.text = numberOfAnimals.toString()
            // Mettez à jour la liste des aliments ici en fonction du nouveau nombre d'animaux
        }
    }
}
