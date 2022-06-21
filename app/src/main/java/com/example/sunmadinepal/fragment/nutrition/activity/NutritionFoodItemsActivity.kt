package com.example.sunmadinepal.fragment.nutrition.activity

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.sunmadinepal.R
import com.example.sunmadinepal.ViewModel.RecipesViewModel
import com.example.sunmadinepal.databinding.ActivityNutritionFoodItemsBinding
import com.example.sunmadinepal.model.loadImage
import com.example.sunmadinepal.utils.changeStatusBarColor
import com.example.sunmadinepal.utils.changeStatusBarIconTextColor
import java.util.*

class NutritionFoodItemsActivity : AppCompatActivity() {

    private lateinit var recipesViewModel: RecipesViewModel
    val string = Locale.getDefault().language


    lateinit var activityNutritionFoodItemsBinding: ActivityNutritionFoodItemsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityNutritionFoodItemsBinding =
            ActivityNutritionFoodItemsBinding.inflate(layoutInflater)
        setContentView(activityNutritionFoodItemsBinding.root)

        val intentReceive = intent.getStringExtra("tool_bar_title")
        activityNutritionFoodItemsBinding.foodItemsToolBar.toolbarActionTitle.text = intentReceive

        activityNutritionFoodItemsBinding.foodItemsToolBar.toolbarActionBackImageView.setOnClickListener {
            finish()
        }
        recipesViewModel = ViewModelProvider(this).get(RecipesViewModel::class.java)


        changeStatusBarIconTextColor(true)
        changeStatusBarColor()
        activityNutritionFoodItemsBinding.progressBar.visibility = View.VISIBLE
        activityNutritionFoodItemsBinding.nutritionFoodConstrain.visibility = View.GONE

        when(intentReceive){

            getString(R.string.recipe_for_energy)->{

                if (string.equals("en")){
                    recipesViewModel.fetchNutritionFoodItems(
                        "foodItemEnergyEn","foodItemEnergyImageUrl"
                    )
                }else if (string.equals("ne")){
                    recipesViewModel.fetchNutritionFoodItems(
                        "foodItemEnergynp","foodItemEnergyImageUrl"
                    )
                }
            }

            getString(R.string.recipe_for_protection)->{

                if (string.equals("en")){
                    recipesViewModel.fetchNutritionFoodProtection(
                        "foodItemProtectionEN","foodItemProtectionImageUrl"
                    )
                }else if (string.equals("ne")){
                    recipesViewModel.fetchNutritionFoodProtection(
                        "foodItemProtectionNP","foodItemProtectionImageUrl"
                    )
                }
            }

            getString(R.string.recipe_for_strength)->{

                if (string.equals("en")){
                    recipesViewModel.fetchNutritionForStrength(
                        "foodItemStrengthEN","foodItemStrengthImageUrl"
                    )
                }else if (string.equals("ne")){
                    recipesViewModel.fetchNutritionForStrength(
                        "foodItemStrengthNP","foodItemStrengthImageUrl"
                    )
                }
            }
        }

        recipesViewModel.nutritionFoodItemsEvents.observe(this, {
            Log.d("onCreate", it.toString())
            it.forEach {foodItems->
                activityNutritionFoodItemsBinding.progressBar.visibility = View.GONE
                activityNutritionFoodItemsBinding.nutritionFoodConstrain.visibility = View.VISIBLE
                activityNutritionFoodItemsBinding.foodItemsTv.text = foodItems.foodItems
//                loadImage(activityNutritionFoodItemsBinding.foodItemsImage,foodItems.imageUrl)

                Glide.with(this).load(foodItems.imageUrl).into(activityNutritionFoodItemsBinding.foodItemsImage)
            }
        })
    }
}