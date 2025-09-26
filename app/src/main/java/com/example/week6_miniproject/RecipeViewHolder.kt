package com.example.week6_miniproject

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.week6_miniproject.R
import com.example.week6_miniproject.model.CuisineType
import com.example.week6_miniproject.model.RecipeModel

// ViewHolder for representing a recipe item
class RecipeViewHolder(
    private val containerView: View,
    private val imageLoader: ImageLoader,
    private val onClickListener: RecipeAdapter.OnClickListener
) : RecyclerView.ViewHolder(containerView) {

    private val recipeImage: ImageView = containerView.findViewById(R.id.recipe_image)
    private val recipeName: TextView = containerView.findViewById(R.id.recipe_name)
    private val recipeCuisine: TextView = containerView.findViewById(R.id.recipe_cuisine)
    private val recipeDescription: TextView = containerView.findViewById(R.id.recipe_description)

    // Bind recipe data to view widgets
    fun bindData(recipe: RecipeModel) {
        imageLoader.loadImage(recipe.imageUrl, recipeImage)
        recipeName.text = recipe.name
        recipeCuisine.text = when(recipe.cuisine) {
            CuisineType.American -> "American"
            CuisineType.Italian -> "Italian"
            CuisineType.Japanese -> "Japanese"
            CuisineType.Mexican -> "Mexican"
            CuisineType.Chinese -> "Chinese"
            CuisineType.Indian -> "Indian"
            CuisineType.Indonesian -> "Indonesian"
        }
        recipeDescription.text = recipe.description

        // Set up click listener to handle item clicks
        containerView.setOnClickListener {
            onClickListener.onItemClick(recipe)
        }
    }
}
