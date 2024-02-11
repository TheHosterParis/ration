package com.example.ration.ui.ration

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.ration.R
import com.example.ration.data.model.Ration
import com.example.ration.data.model.RationComplete
import com.example.ration.data.model.RationDetail
import com.example.ration.data.model.RationGrouped

class RationListAdapter(private var rationGroups: List<RationGrouped>) : RecyclerView.Adapter<RationListAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvRationName: TextView = view.findViewById(R.id.tvRationName)
        val tvAnimalName: TextView = view.findViewById(R.id.tvAnimalName)
        val tvAlimentDetails: TextView = view.findViewById(R.id.tvAlimentDetails)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.ration_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val rationGroup = rationGroups[position]
        holder.tvRationName.text = rationGroup.rationName
        holder.tvAnimalName.text = rationGroup.animalName
        // Pour simplifier, affichez les composants sous forme de texte. Pour une présentation plus élaborée, envisagez un RecyclerView imbriqué.
        val componentsText = rationGroup.components.joinToString(separator = "\n") { "${it.alimentName}: ${it.quantity}${it.unit}, Pas: ${it.pas}" }
        holder.tvAlimentDetails.text = componentsText
    }

    override fun getItemCount() = rationGroups.size

    fun updateData(newRationCompletes: List<RationGrouped>) {
        this.rationGroups = newRationCompletes
        notifyDataSetChanged()
    }
}
