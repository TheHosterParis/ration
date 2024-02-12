package com.hoster.ration

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.hoster.ration.ui.aliment.AlimentListActivity
import com.hoster.ration.ui.animal.AnimalListActivity
import com.hoster.ration.ui.ration.RationHistoryActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Configure le bouton pour la liste des animaux
        findViewById<Button>(R.id.btnAnimalList).setOnClickListener {
            val intent = Intent(this, AnimalListActivity::class.java)
            startActivity(intent)
        }

        // Configure le bouton pour la liste des Aliments
        findViewById<Button>(R.id.btnAlimentList).setOnClickListener {
            val intent = Intent(this, AlimentListActivity::class.java)
            startActivity(intent)
        }

        // Configure le bouton pour l'historique des rations
        findViewById<Button>(R.id.btnRationHistory).setOnClickListener {
            val intent = Intent(this, RationHistoryActivity::class.java)
            startActivity(intent)
        }
    }
}
