package com.hoster.ration.ui.aliment

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hoster.ration.R
import com.hoster.ration.data.model.RationGrouped
import com.hoster.ration.ui.util.FormuleUtil
import com.hoster.ration.ui.util.RounderUtil
import kotlin.math.roundToInt

class AlimentDeRationAdapter(private var rationGrouped: RationGrouped) :
    RecyclerView.Adapter<AlimentDeRationAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val alimentNameTextView: TextView = view.findViewById(R.id.tvAlimentName)
        val quantityTextView: TextView = view.findViewById(R.id.tvAlimentQuantity)
        val unitTextView: TextView = view.findViewById(R.id.tvAlimentUnit)
        val quantityEditText: EditText = view.findViewById(R.id.editTextAlimentQuantity)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.ration_aliment_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val aliment = rationGrouped.aliments[position]
        var quantity = FormuleUtil().ComputeQuantity(rationGrouped.numberOfAnimals, aliment.pas)
        val quantityRounded = RounderUtil().roundToPrecision(quantity, 1)
        holder.alimentNameTextView.text = aliment.alimentName
        holder.quantityTextView.text = String.format("%.1f", quantityRounded)
        holder.unitTextView.text = aliment.unit

        // Affichez EditText ou TextView en fonction du mode édition
        if (isInEditMode) {
            holder.quantityEditText.visibility = View.VISIBLE
            holder.quantityTextView.visibility = View.GONE
            holder.quantityEditText.setText(aliment.pas.toString()) //TODO mettre un nouveau champs 'pas'

            holder.quantityEditText.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    // Convertir le texte en nombre et mettre à jour la quantité de l'aliment
                    s?.toString()?.toDoubleOrNull()?.let { newStep ->
                        aliment.pas = newStep
                        // Mettez à jour le total ou d'autres UI si nécessaire
                    }
                }

                override fun afterTextChanged(s: Editable?) {}
            })

        } else {
            holder.quantityEditText.visibility = View.GONE
            holder.quantityTextView.visibility = View.VISIBLE
            holder.quantityTextView.text = String.format("%.1f", quantityRounded)
        }
    }

    override fun getItemCount() = rationGrouped.aliments.size

    // Méthode pour ajuster les quantités et calculer le poids total des aliments
    fun adjustQuantities(numberOfAnimals: Int) {
        rationGrouped.numberOfAnimals = numberOfAnimals
        notifyDataSetChanged()
    }
    fun getTotalWeight(): Double {
        return rationGrouped.aliments.map { it.pas * rationGrouped.numberOfAnimals }.sum()
    }
    // Ajoutez une variable pour suivre le mode édition
    private var isInEditMode = false

    // Méthode pour définir le mode édition
    fun setEditMode(isInEditMode: Boolean) {
        this.isInEditMode = isInEditMode
        notifyDataSetChanged()
    }
}
