package com.example.week6_miniproject

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.week6_miniproject.R
import com.example.week6_miniproject.model.RecipeModel

// RecyclerView Adapter to bind recipe data to the UI
class RecipeAdapter(
    private val layoutInflater: LayoutInflater,
    private val imageLoader: ImageLoader,
    private val onClickListener: OnClickListener
) : RecyclerView.Adapter<RecipeViewHolder>() {

    private val recipes = mutableListOf<RecipeModel>()

    // Update the dataset and refresh UI
    fun setData(newRecipes: List<RecipeModel>) {
        recipes.clear()
        recipes.addAll(newRecipes)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val view = layoutInflater.inflate(R.layout.item_list_recipe, parent, false)
        return RecipeViewHolder(view, imageLoader, onClickListener)
    }

    override fun getItemCount(): Int = recipes.size

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.bindData(recipes[position])
    }

    interface OnClickListener {
        fun onItemClick(recipe: RecipeModel)
    }
}
