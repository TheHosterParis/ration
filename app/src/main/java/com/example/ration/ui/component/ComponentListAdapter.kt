package com.example.ration.ui.component

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ration.R
import com.example.ration.data.model.Component

class ComponentListAdapter(private var components: List<Component>) : RecyclerView.Adapter<ComponentListAdapter.ComponentViewHolder>() {

    class ComponentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.componentNameTextView)

        fun bind(component: Component) {
            nameTextView.text = component.componentName
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComponentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.component_list_item, parent, false)
        return ComponentViewHolder(view)
    }

    override fun onBindViewHolder(holder: ComponentViewHolder, position: Int) {
        holder.bind(components[position])
    }

    override fun getItemCount() = components.size

    fun updateComponents(components: List<Component>) {
        this.components = components // Assume `components` is the list in your adapter
        notifyDataSetChanged()
    }
}
