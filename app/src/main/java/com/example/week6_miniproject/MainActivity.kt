package com.example.week6_miniproject

import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.week6_miniproject.model.CuisineType
import com.example.week6_miniproject.model.RecipeModel
import com.google.android.material.bottomnavigation.BottomNavigationView

// Main Activity used with RecyclerView to display recipe list
class MainActivity : AppCompatActivity() {

    // Lazy init RecyclerView reference
    private val recyclerView: RecyclerView by lazy { findViewById(R.id.recycler_view) }

    // Adapter that handles recipe data and image loading
    private val recipeAdapter by lazy {
        RecipeAdapter(
            layoutInflater,
            GlideImageLoader(this),
            object : RecipeAdapter.OnClickListener {
                override fun onItemClick(recipe: RecipeModel) {
                    showRecipeDialog(recipe)
                }

                // Handle favorite button click
                override fun onFavoriteClick(recipe: RecipeModel) {
                    toggleFavorite(recipe)
                }
            }
        )
    }



    // Keep all recipes in memory
    private lateinit var allRecipes: List<RecipeModel>

    // Track favorite recipes separately
    private val favoriteRecipes = mutableListOf<RecipeModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Setup RecyclerView
        recyclerView.adapter = recipeAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Recipe Data (help ketikin pls)
        allRecipes = listOf(
            RecipeModel(
                CuisineType.American,
                "Classic Cheeseburger",
                "Juicy beef patty with melted cheese, lettuce, and tomato inside a toasted bun.",
                "https://images.unsplash.com/photo-1550547660-d9450f859349?w=1200&q=80",
                listOf(
                    "500g ground beef",
                    "Salt & black pepper",
                    "Cheese slices",
                    "4 burger buns",
                    "Lettuce leaves",
                    "Tomato slices",
                    "Pickles",
                    "Ketchup & mustard"
                ),
                """
                    1. Shape ground beef into patties and season with salt & pepper.
                    2. Grill or pan-fry the patties until cooked to desired doneness.
                    3. Place cheese slice on top and let it melt.
                    4. Assemble with lettuce, tomato, pickles, and sauces inside toasted buns.
                    5. Serve hot with fries.
                """.trimIndent()
            ),

            RecipeModel(
                CuisineType.Italian,
                "Spaghetti Carbonara",
                "Classic Roman pasta dish with pancetta, parmesan, and creamy egg-based sauce.",
                "https://images.services.kitchenstories.io/z_bWPIhhM6qs38B0E46CRaYs81Q=/3840x0/filters:quality(85)/images.kitchenstories.io/wagtailOriginalImages/R2568-photo-final-_0.jpg",
                listOf(
                    "400g spaghetti",
                    "150g pancetta or guanciale",
                    "3 large eggs",
                    "100g grated parmesan",
                    "Salt & freshly ground black pepper",
                    "Olive oil"
                ),
                """
                    1. Boil spaghetti in salted water until al dente.
                    2. Fry pancetta until crispy in a little olive oil.
                    3. Mix eggs and parmesan in a bowl.
                    4. Drain pasta (reserve some water), combine with pancetta.
                    5. Remove from heat, stir in egg-cheese mixture quickly to create a creamy sauce.
                    6. Add pasta water if needed to reach desired creaminess.
                    7. Top with black pepper and extra parmesan.
                """.trimIndent()
            ),

            RecipeModel(
                CuisineType.Japanese,
                "Sushi Rolls",
                "Delicate rice rolls with fresh fish, vegetables, and seasoned rice.",
                "https://images.unsplash.com/photo-1553621042-f6e147245754?w=1200&q=80",
                listOf(
                    "2 cups sushi rice (cooked)",
                    "4 nori sheets",
                    "Sushi-grade salmon or tuna",
                    "1 cucumber, julienned",
                    "1 avocado, sliced",
                    "Rice vinegar, sugar & salt (for seasoning rice)",
                    "Soy sauce, wasabi, pickled ginger (to serve)"
                ),
                """
                    1. Cook sushi rice and season with rice vinegar, sugar, and salt.
                    2. Lay nori sheet on a bamboo mat.
                    3. Spread rice thinly over nori, leaving space at the top edge.
                    4. Add fillings: fish, cucumber, avocado.
                    5. Roll tightly using the bamboo mat and slice into pieces.
                    6. Serve with soy sauce, wasabi, and pickled ginger.
                """.trimIndent()
            ),

            RecipeModel(
                CuisineType.Mexican,
                "Chicken Tacos",
                "Soft tortillas filled with spiced chicken, salsa, guacamole, and cheese.",
                "https://images7.memedroid.com/images/UPLOADED963/61688f52dcb07.jpeg",
                listOf(
                    "500g chicken breast, sliced",
                    "Taco seasoning (or mix chili, cumin, paprika)",
                    "8 small tortillas",
                    "Lettuce, shredded",
                    "Salsa",
                    "Guacamole",
                    "Shredded cheese",
                    "Lime wedges"
                ),
                """
                    1. Cook chicken strips with taco seasoning until golden brown.
                    2. Warm tortillas in a pan.
                    3. Fill tortillas with chicken, salsa, guacamole, shredded lettuce, and cheese.
                    4. Squeeze lime on top for extra flavor.
                    5. Serve immediately with hot sauce.
                """.trimIndent()
            ),

            RecipeModel(
                CuisineType.Indian,
                "Butter Chicken",
                "Creamy tomato-based curry with tender chicken pieces and spices.",
                "https://images.immediate.co.uk/production/volatile/sites/30/2021/02/butter-chicken-ac2ff98.jpg?quality=90&resize=600,400",
                listOf(
                    "500g chicken, cubed",
                    "150g yogurt",
                    "2 tsp garlic paste",
                    "1 inch ginger, grated",
                    "400g canned tomatoes/puree",
                    "2 tbsp butter",
                    "100ml cream",
                    "Garam masala, chili powder, salt"
                ),
                """
                    1. Marinate chicken in yogurt, garlic, ginger, and spices for at least 30 minutes (overnight better).
                    2. Brown the chicken in a pan and set aside.
                    3. Prepare sauce with butter, onions, tomatoes, cream, and spices.
                    4. Simmer chicken in sauce until tender and flavorful.
                    5. Serve hot with naan or basmati rice.
                """.trimIndent()
            ),

            RecipeModel(
                CuisineType.Chinese,
                "Sweet and Sour Chicken",
                "Crispy chicken tossed in a tangy-sweet sauce with pineapple and peppers.",
                "https://www.modernhoney.com/wp-content/uploads/2023/01/Sweet-and-Sour-Chicken-3-crop-scaled.jpg",
                listOf(
                    "500g boneless chicken, cubed",
                    "Cornstarch for coating",
                    "Bell peppers",
                    "Pineapple chunks",
                    "Ketchup, rice vinegar, soy sauce, sugar",
                    "Oil for frying"
                ),
                """
                    1. Coat chicken pieces in cornstarch and fry until golden and crispy.
                    2. Stir-fry bell peppers, onions, and pineapple chunks.
                    3. Prepare sauce with ketchup, vinegar, soy sauce, and sugar, then add to pan.
                    4. Toss fried chicken in the sauce until coated.
                    5. Serve immediately with steamed rice.
                """.trimIndent()
            ),

            // Indonesian recipe - Beef Rendang
            RecipeModel(
                CuisineType.Indonesian,
                "Beef Rendang",
                "Slow-cooked spicy beef stew in coconut milk.",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSV7L29OhV7tDV09rYPX6l-CuWmrsouEVkmdQ&s",
                listOf(
                    "1 kg beef (cut into cubes)",
                    "400 ml coconut milk",
                    "4 kaffir lime leaves",
                    "2 turmeric leaves (optional)",
                    "2 lemongrass stalks (bruised)",
                    "5 tbsp cooking oil",
                    "1 cinnamon stick",
                    "3 star anise",
                    "3 cardamom pods",
                    "2 cloves",
                    "Salt & sugar to taste",
                    "Spice paste (blend): 10 shallots, 6 garlic cloves, 5 red chilies, 3 cm ginger, 3 cm galangal, 3 cm turmeric"
                ),
                """
                    1. Blend spice paste until smooth.
                    2. Heat oil in a large pan or wok, sauté the spice paste until fragrant.
                    3. Add whole spices (cinnamon, star anise, cardamom, cloves) and stir briefly.
                    4. Add beef cubes and brown all sides.
                    5. Pour in coconut milk, add kaffir lime leaves, turmeric leaves, and bruised lemongrass.
                    6. Simmer on low heat for 2–3 hours until beef is tender and sauce thickens (stir occasionally).
                    7. Adjust salt and sugar, reduce until oil separates and sauce is rich and dark.
                    8. Serve with steamed rice.
                """.trimIndent()
            ),
            RecipeModel(
                CuisineType.American,
                "Hot Dog",
                "PLEASE DO NOT EAT YOUR ACTUAL DOG (IM LOOKING AT YOU MAX.)",
                "https://ih1.redbubble.net/image.1223050410.0242/raf,360x360,075,t,fafafa:ca443f4786.jpg",
                listOf(
                    "4 hot dog buns",
                    "4 beef or chicken sausages",
                    "2 tbsp butter (for toasting buns)",
                    "2 tbsp yellow mustard",
                    "2 tbsp ketchup",
                    "2 tbsp mayonnaise (optional)",
                    "½ cup sauerkraut or pickles (optional)",
                    "½ cup grated cheddar cheese (optional)",
                    "½ cup diced onions (optional)"
                ),
                """
                    1. Preheat a grill pan or skillet over medium heat.
                    2. Grill or pan-fry sausages for 5–7 minutes, turning occasionally until browned.
                    3. Slice buns lengthwise and lightly toast with butter on the grill/pan.
                    4. Place cooked sausage into each bun.
                    5. Add condiments (mustard, ketchup, mayonnaise).
                    6. Top with sauerkraut, pickles, cheese, or onions as desired.
                    7. Serve immediately while hot.
                """.trimIndent()
            ),
            RecipeModel(
                CuisineType.Italian,
                "Pineapple Pizza",
                "Pizza with pineapple. why you doing this?",
                "https://media.radiocms.net/uploads/2022/10/05081627/gordon-ramsay-pinapple-pizza.png",
                listOf(
                    "1 pizza dough (store-bought or homemade)",
                    "200 ml pizza sauce (tomato-based)",
                    "200 g shredded mozzarella cheese",
                    "100 g cooked ham or bacon, sliced",
                    "150 g pineapple chunks (fresh or canned, drained)",
                    "1 tbsp olive oil",
                    "1 tsp dried oregano or Italian seasoning"
                ),
                """
                    "1 pizza dough (store-bought or homemade)",
                    "200 ml pizza sauce (tomato-based)",
                    "200 g shredded mozzarella cheese",
                    "100 g cooked ham or bacon, sliced",
                    "150 g pineapple chunks (fresh or canned, drained)",
                    "1 tbsp olive oil",
                    "1 tsp dried oregano or Italian seasoning"
                """.trimIndent()
            ),
            RecipeModel(
                CuisineType.American,
                "Corn Dog",
                "AGAIN, DO NOT EAT YOUR ACTUAL DOG (IM STILL LOOKING AT YOU MAX.)",
                "https://cdn.discordapp.com/attachments/1412700074953150515/1421144100672897116/9k.png?ex=68d7f715&is=68d6a595&hm=206b6e911f83c610f275a48c964472a68db36c36703f37b3cd5d12662294686e&",
                listOf(
                    "4 beef or chicken sausages",
                    "200ml of cooking oil",
                    "2 tbsp yellow mustard",
                    "2 tbsp ketchup",
                    "2 tbsp mayonnaise (optional)",
                    "½ cup sauerkraut or pickles (optional)",
                    "½ cup grated cheddar cheese (optional)",
                    "½ cup diced onions (optional)"
                ),
                """
                    1. Preheat a pan or skillet over medium heat.
                    2. Grill or pan-fry sausages for 5–7 minutes, turning occasionally until browned.
                    3. Slice buns lengthwise and lightly toast with butter on the grill/pan.
                    4. Place cooked sausage into each bun.
                    5. Add condiments (mustard, ketchup, mayonnaise).
                    6. Top with sauerkraut, pickles, cheese, or onions as desired.
                    7. Serve immediately while hot.
                """.trimIndent()
            ),

        )

        // Show recipe
        recipeAdapter.setData(allRecipes)

        // Bottom navbarnya
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    recipeAdapter.setData(allRecipes)
                    true
                }
                R.id.nav_favorites -> {
                    recipeAdapter.setData(favoriteRecipes)
                    true
                }
                else -> false
            }
        }
    }

    // Show AlertDialog for the recipe's ingredients steps
    private fun showRecipeDialog(recipe: RecipeModel) {
        val ingredientsText = recipe.ingredients.joinToString("\n") { "- $it" }
        val message = "Ingredients:\n$ingredientsText\n\nSteps:\n${recipe.recipeDetails}"

        AlertDialog.Builder(this)
            .setTitle(recipe.name)
            .setMessage(message)
            .setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
            .show()
    }

    // toggle favorite state and refresh the UI
    private fun toggleFavorite(recipe: RecipeModel) {
        if (favoriteRecipes.contains(recipe)) {
            favoriteRecipes.remove(recipe)
            recipe.isFavorite = false
        } else {
            favoriteRecipes.add(recipe)
            recipe.isFavorite = true
        }
        recipeAdapter.notifyDataSetChanged()
    }
}
