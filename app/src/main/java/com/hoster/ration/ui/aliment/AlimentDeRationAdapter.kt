package com.hoster.ration.ui.aliment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hoster.ration.R
import com.hoster.ration.data.model.AlimentQuantity

class AlimentDeRationAdapter(private var alimentList: List<AlimentQuantity>) :
    RecyclerView.Adapter<AlimentDeRationAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val alimentNameTextView: TextView = view.findViewById(R.id.tvAlimentName)
        val quantityTextView: TextView = view.findViewById(R.id.tvAlimentQuantity)
        val unitTextView: TextView = view.findViewById(R.id.tvAlimentUnit)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.ration_aliment_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val aliment = alimentList[position]
        holder.alimentNameTextView.text = aliment.alimentName
        holder.quantityTextView.text = aliment.quantity.toString()
        holder.unitTextView.text = aliment.unit
    }

    override fun getItemCount() = alimentList.size

    fun updateComponents(newComponents: List<AlimentQuantity>) {
        this.alimentList = newComponents
        notifyDataSetChanged()
    }
}
