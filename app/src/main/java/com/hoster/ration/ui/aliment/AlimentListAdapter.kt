package com.hoster.ration.ui.aliment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hoster.ration.R
import com.hoster.ration.data.model.Aliment

class AlimentListAdapter(private var aliments: List<Aliment>) : RecyclerView.Adapter<AlimentListAdapter.AlimentViewHolder>() {

    class AlimentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.alimentNameTextView)

        fun bind(aliment: Aliment) {
            nameTextView.text = aliment.alimentName
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlimentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.aliment_list_item, parent, false)
        return AlimentViewHolder(view)
    }

    override fun onBindViewHolder(holder: AlimentViewHolder, position: Int) {
        holder.bind(aliments[position])
    }

    override fun getItemCount() = aliments.size

    fun updateAliments(aliments: List<Aliment>) {
        this.aliments = aliments // Assume `aliments` is the list in your adapter
        notifyDataSetChanged()
    }
}
