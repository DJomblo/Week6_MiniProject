package com.example.week6_miniproject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.week6_miniproject.model.RecipeModel

// Adapter for RecyclerView with support for favorites
class RecipeAdapter(
    private val inflater: LayoutInflater,
    private val imageLoader: ImageLoader,
    private val listener: OnClickListener
) : RecyclerView.Adapter<RecipeAdapter.ViewHolder>() {

    private var recipeList: List<RecipeModel> = listOf()

    fun setData(newList: List<RecipeModel>) {
        recipeList = newList
        notifyDataSetChanged()
    }

    interface OnClickListener {
        fun onItemClick(recipe: RecipeModel)
        fun onFavoriteClick(recipe: RecipeModel) // <-- NEW
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val recipeImage: ImageView = view.findViewById(R.id.recipe_image)
        val recipeTitle: TextView = view.findViewById(R.id.recipe_title)
        val recipeDesc: TextView = view.findViewById(R.id.recipe_desc)
        val favoriteButton: ImageButton = view.findViewById(R.id.favorite_button)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = inflater.inflate(R.layout.item_recipe, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = recipeList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val recipe = recipeList[position]

        holder.recipeTitle.text = recipe.name
        holder.recipeDesc.text = recipe.description

        // Load image with GlideImageLoader
        imageLoader.loadImage(recipe.imageUrl, holder.recipeImage)

        // Favorite button icon
        holder.favoriteButton.setImageResource(
            if (recipe.isFavorite) R.drawable.ic_favorite else R.drawable.ic_favorite_border
        )

        // Click listeners
        holder.itemView.setOnClickListener { listener.onItemClick(recipe) }
        holder.favoriteButton.setOnClickListener { listener.onFavoriteClick(recipe) }
    }
}
