package com.hoster.ration.ui.ration

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hoster.ration.R
import com.hoster.ration.data.model.RationGrouped
import com.hoster.ration.ui.util.FormuleUtil
import com.hoster.ration.ui.util.RounderUtil

class RationListAdapter(private var rationGroups: List<RationGrouped>, private val onRationClickListener: OnRationClickListener) : RecyclerView.Adapter<RationListAdapter.ViewHolder>() {
    private val formuleUtil = FormuleUtil()
    interface OnRationClickListener {
        fun onRationClicked(rationGrouped: RationGrouped)
    }
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvRationName: TextView = view.findViewById(R.id.rationNameTextView)
        val tvnumberOfAnimals: TextView = view.findViewById(R.id.numberOfAnimalsTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.ration_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val rationGroup = rationGroups[position]
        holder.tvRationName.text = rationGroup.rationName
        holder.tvnumberOfAnimals.text = rationGroup.numberOfAnimals.toString()
        val quantity = formuleUtil.ComputeQuantity(rationGroup.numberOfAnimals, rationGroup.aliments[0].pas)
        // Pour simplifier, affichez les composants sous forme de texte. Pour une présentation plus élaborée, envisagez un RecyclerView imbriqué.
        val alimentsText = rationGroup.aliments.joinToString(separator = "\n") { "${it.alimentName}: ${quantity}${it.unit}, Pas: ${it.pas}" }
        Log.d("RationListAdapter",alimentsText)
        //holder.tvAlimentDetails.text = alimentsText

        holder.itemView.setOnClickListener {
            onRationClickListener.onRationClicked(rationGroup)
            Log.d("RationListAdapter",alimentsText)
        }
    }

    override fun getItemCount() = rationGroups.size

    fun updateData(newRationCompletes: List<RationGrouped>) {
        this.rationGroups = newRationCompletes
        notifyDataSetChanged()
    }
}
