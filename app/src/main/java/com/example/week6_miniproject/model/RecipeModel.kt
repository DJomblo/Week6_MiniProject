package com.example.week6_miniproject.model

// Data class for representing a food recipe item
data class RecipeModel(
    val cuisine: CuisineType,          // Type of the cuisine
    val name: String,                  // Recipe title
    val description: String,           // description of the recipe
    val imageUrl: String,              // URL for recipe image
    val ingredients: List<String>,     // Ingredients list
    val recipeDetails: String,          // Details/instructions for the recipe
    var isFavorite: Boolean = false     //  favorite state
)


// cuisine type
enum class CuisineType {
    American, Italian, Japanese, Mexican, Chinese, Indian, Indonesian
}
