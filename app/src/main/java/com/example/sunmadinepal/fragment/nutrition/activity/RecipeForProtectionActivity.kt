package com.example.sunmadinepal.fragment.nutrition.activity

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.sunmadinepal.ViewModel.RecipesViewModel
import com.example.sunmadinepal.databinding.ActivityRecipeForProtectionBinding
import com.example.sunmadinepal.fragment.nutrition.adapter.NutritionAdapter
import com.example.sunmadinepal.model.RecipesData
import com.example.sunmadinepal.utils.hideProgressDialog
import com.example.sunmadinepal.utils.showProgressDialog
import java.util.*

class RecipeForProtectionActivity : AppCompatActivity() {

    private lateinit var recipeForProtectionBinding: ActivityRecipeForProtectionBinding
    val string = Locale.getDefault().language
    private lateinit var recipesViewModel : RecipesViewModel
    private var _events = ArrayList<RecipesData>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        recipeForProtectionBinding = ActivityRecipeForProtectionBinding.inflate(layoutInflater)
        setContentView(recipeForProtectionBinding.root)

        recipesViewModel = ViewModelProvider(this).get(RecipesViewModel::class.java)
        getData()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun getData() {
        if (string.equals("en")){
            recipesViewModel.fetchEventRecipeForMothers(
                "JauloTitleEn","JauloDescriptionEn",
                "LittoTitleEn", "LittoDescriptionEn",
                "NutritiousFlourTitleEn","NutritiousFlourDescriptionEn",
                "PumpkinPuddingTitleEn", "PumpkinPuddingDescriptionEn")
        }else if (string.equals("ne")){
            recipesViewModel.fetchEventRecipeForMothers(
                "JauloTitleNe","JauloDescriptionNe",
                "LittoTitleNe","LittoDescriptionNe",
                "NutritiousFlourTitleNe","NutritiousFlourDescriptionNe",
                "PumpkinPuddingTitleNe", "PumpkinPuddingDescriptionNe")
        }

        showProgressDialog()

        recipeForProtectionBinding.recipeProtectionRv.adapter = NutritionAdapter().apply {
            submitList(_events)
        }

        recipesViewModel.events.observe(this, {
                event ->
            _events.removeAll(_events)
            _events.addAll(event)
            hideProgressDialog()
            recipeForProtectionBinding.recipeProtectionRv.adapter!!.notifyDataSetChanged()
        })
    }
}