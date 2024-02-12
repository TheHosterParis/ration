package com.hoster.ration.ui.animal

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hoster.ration.R
import com.hoster.ration.data.model.Animal

class AnimalListAdapter(private var animals: List<Animal>) : RecyclerView.Adapter<AnimalListAdapter.AnimalViewHolder>() {

    class AnimalViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val animalNameTextView: TextView = itemView.findViewById(R.id.animalNameTextView)
        val animalIconImageView: ImageView = itemView.findViewById(R.id.animalIconImageView)

        fun bind(animal: Animal) {
            animalNameTextView.text = animal.name
            //animalIconImageView.setImageResource() = animal.imageUrl
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimalViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.animal_list_item, parent, false)
        return AnimalViewHolder(view)
    }

    override fun onBindViewHolder(holder: AnimalViewHolder, position: Int) {
        val animal = animals[position]
        holder.animalNameTextView.text = animal.name
        val imageResId = holder.itemView.context.resources.getIdentifier(animal.imageUrl, "drawable", holder.itemView.context.packageName)
        if (imageResId != 0) {
            holder.animalIconImageView.setImageResource(imageResId)
        } else {
            // Set default icon if no valid image path is found
            holder.animalIconImageView.setImageResource(R.drawable.animal) // Assume you have a default_icon.png in drawable
        }
    }

    override fun getItemCount(): Int = animals.size

    fun updateAnimals(animals: List<Animal>) {
        this.animals = animals // Assume `animals` is the list in your adapter
        notifyDataSetChanged()
    }
}
