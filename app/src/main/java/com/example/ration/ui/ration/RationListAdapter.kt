package com.example.ration.ui.ration

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.ration.R
import com.example.ration.data.model.Ration

class RationListAdapter(private var rations: List<Ration>) : RecyclerView.Adapter<RationListAdapter.RationViewHolder>() {

    class RationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.rationNameTextView)
        private val nameCardView: CardView = itemView.findViewById(R.id.rationNameCardView)

        fun bind(ration: Ration) {
            nameTextView.text = ration.rationName
            nameCardView.setOnClickListener {
                // Ici, vous pouvez démarrer RationDetailActivity pour afficher les détails de l'ration
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RationViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.ration_list_item, parent, false)
        return RationViewHolder(view)
    }

    override fun onBindViewHolder(holder: RationViewHolder, position: Int) {
        holder.bind(rations[position])
    }

    override fun getItemCount() = rations.size

    fun updateRations(rations: List<Ration>) {
        this.rations = rations // Assume `rations` is the list in your adapter
        notifyDataSetChanged()
    }
}
