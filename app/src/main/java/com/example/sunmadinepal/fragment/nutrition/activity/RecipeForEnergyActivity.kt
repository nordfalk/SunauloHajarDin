package com.example.sunmadinepal.fragment.nutrition.activity

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.sunmadinepal.ViewModel.RecipesViewModel
import com.example.sunmadinepal.databinding.ActivityReceipeForEnergyBinding
import com.example.sunmadinepal.fragment.nutrition.adapter.NutritionAdapter
import com.example.sunmadinepal.model.RecipesData
import com.example.sunmadinepal.utils.changeStatusBarColor
import com.example.sunmadinepal.utils.changeStatusBarIconTextColor
import com.example.sunmadinepal.utils.hideProgressDialog
import com.example.sunmadinepal.utils.showProgressDialog
import java.util.*

class RecipeForEnergyActivity : AppCompatActivity() {
    private lateinit var activityReceipeForEnergyBinding: ActivityReceipeForEnergyBinding
    val string = Locale.getDefault().language
    private lateinit var recipesViewModel : RecipesViewModel
    private var _events = ArrayList<RecipesData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityReceipeForEnergyBinding = ActivityReceipeForEnergyBinding.inflate(layoutInflater)
        setContentView(activityReceipeForEnergyBinding.root)

        changeStatusBarIconTextColor(true)
        changeStatusBarColor()

        val receiveIntent = intent.getStringExtra("tool_bar_title")
        activityReceipeForEnergyBinding.recipeEnergyToolBar.toolbarActionTitle.text = receiveIntent

        activityReceipeForEnergyBinding.recipeEnergyToolBar.toolbarActionBackImageView.setOnClickListener {
            finish()
        }
        recipesViewModel = ViewModelProvider(this).get(RecipesViewModel::class.java)
        getData()


    }

    @SuppressLint("NotifyDataSetChanged")
    private fun getData() {
        if (string.equals("en")){
            recipesViewModel.fetchEventRecipesForChildren(
                "JauloTitleEn","jauloIngrdientsEN", "jauloItemDirectionEN",
                "LittoTitleEn", "LittoDescriptionEn","litoItemDirectionEN",
                "NutritiousFlourTitleEn","NutritiousFlourDescriptionEn",
                    "PumpkinPuddingTitleEn", "PumpkinPuddingDescriptionEn",
            "PumpkinPuddingDirectionEn")
        }else if (string.equals("ne")){
            recipesViewModel.fetchEventRecipesForChildren(
//
                "JauloTitleNe","jauloIngredientsNP","jauloItemDirectionNP",
                "LittoTitleNe","LittoDescriptionNe","litoItemDirectionNP",
                "NutritiousFlourTitleNe","NutritiousFlourDescriptionNe",
                "PumpkinPuddingTitleNe", "PumpkinPuddingDescriptionNe",
            "PumpkinPuddingDirectionNp")
        }

        showProgressDialog()

        activityReceipeForEnergyBinding.recipeEnergyRv.adapter = NutritionAdapter().apply {
            submitList(_events)
        }

        recipesViewModel.events.observe(this, {
                event ->
            _events.removeAll(_events)
            _events.addAll(event)
            hideProgressDialog()
            activityReceipeForEnergyBinding.recipeEnergyRv.adapter!!.notifyDataSetChanged()
        })
    }


}