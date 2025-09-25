package com.example.week6_miniproject.model

// Data class for representing a food recipe item
data class RecipeModel(
    val cuisine: CuisineType,          // Type of cuisine
    val name: String,                  // Recipe title
    val description: String,           // Short description of the recipe
    val imageUrl: String,              // URL for recipe image
    val recipeDetails: String          // Details/instructions for the recipe
)

// Enumeration for cuisine types to categorize recipes
enum class CuisineType {
    American, Italian, Japanese, Mexican, Chinese, Indian
}
