package com.example.sunmadinepal.fragment.recipes

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.sunmadinepal.databinding.ActivityInsideRecipesBinding
import com.example.sunmadinepal.model.loadImage
import com.example.sunmadinepal.utils.changeStatusBarColor
import com.example.sunmadinepal.utils.changeStatusBarIconTextColor
import java.util.*


class InsideRecipes : AppCompatActivity() {
    private lateinit var insideRecipesBinding: ActivityInsideRecipesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        changeStatusBarIconTextColor(true)
        changeStatusBarColor()

        insideRecipesBinding = ActivityInsideRecipesBinding.inflate(layoutInflater)
        setContentView(insideRecipesBinding.root)

        insideRecipesBinding.insideRecipeToolLayout.toolbarActionBackImageView.setOnClickListener {
            finish()
        }
        val string = Locale.getDefault().language
        val locale = Locale(string)
        Locale.setDefault(locale)


        val bundle: Bundle? = intent.extras
        if (bundle != null) {

            val titlel = bundle.getString("Title")
            insideRecipesBinding.insideRecipeToolLayout.toolbarActionTitle.text = titlel
            insideRecipesBinding.description.text = bundle.getString("Description")
            loadImage(insideRecipesBinding.ivImage, bundle.getString("Image1").toString())
        }
    }
}