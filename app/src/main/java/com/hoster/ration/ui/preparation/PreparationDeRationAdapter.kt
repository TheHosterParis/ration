package com.hoster.ration.ui.preparation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hoster.ration.R
import com.hoster.ration.data.model.PreparationStep

class PreparationDeRationAdapter(private var preparationStepList: List<PreparationStep>) :
    RecyclerView.Adapter<PreparationDeRationAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameTextView: TextView = view.findViewById(R.id.tvPreparationStepName)
        val descriptionTextView: TextView = view.findViewById(R.id.tvPreparationStepDescription)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.ration_preparation_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val preparationStep = preparationStepList[position]
        holder.nameTextView.text = preparationStep.name
        holder.descriptionTextView.text = preparationStep.description
    }

    override fun getItemCount() = preparationStepList.size

}
