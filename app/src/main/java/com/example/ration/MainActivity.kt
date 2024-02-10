package com.example.ration

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.ration.ui.animal.AnimalListActivity
import com.example.ration.ui.component.ComponentListActivity
import com.example.ration.ui.ration.RationHistoryActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Configure le bouton pour la liste des animaux
        findViewById<Button>(R.id.btnAnimalList).setOnClickListener {
            val intent = Intent(this, AnimalListActivity::class.java)
            startActivity(intent)
        }

        // Configure le bouton pour la liste des composants
        findViewById<Button>(R.id.btnComponentList).setOnClickListener {
            val intent = Intent(this, ComponentListActivity::class.java)
            startActivity(intent)
        }

        // Configure le bouton pour l'historique des rations
        findViewById<Button>(R.id.btnRationHistory).setOnClickListener {
            val intent = Intent(this, RationHistoryActivity::class.java)
            startActivity(intent)
        }
    }
}
