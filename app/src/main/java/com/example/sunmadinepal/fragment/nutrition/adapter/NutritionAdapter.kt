package com.example.sunmadinepal.fragment.nutrition.adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.sunmadinepal.databinding.NutritionItemLayoutBinding
import com.example.sunmadinepal.fragment.recipes.InsideRecipes
import com.example.sunmadinepal.model.RecipesData

class NutritionAdapter : ListAdapter<RecipesData, NutritionAdapter.ViewHolder>(
    diffUtil
) {

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<RecipesData>() {
            override fun areItemsTheSame(oldItem: RecipesData, newItem: RecipesData): Boolean =
                oldItem == newItem

            override fun areContentsTheSame(oldItem: RecipesData, newItem: RecipesData): Boolean =
                oldItem == newItem

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            NutritionItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.updateView(currentList[position])
    }

    inner class ViewHolder(private val view: NutritionItemLayoutBinding) :
        RecyclerView.ViewHolder(view.root) {
        @SuppressLint("SetTextI18n")
        fun updateView(recipesData: RecipesData) {

//            loadImage(view.nutritionImageView,recipesData.itemImage)
            Glide.with(view.root.context).load(recipesData.itemImage).into(view.nutritionImageView)
            view.nutritionTv.text = recipesData.itemName

            view.nutritionCardView.apply {
                setOnClickListener {
                    val intent = Intent(context, InsideRecipes::class.java)
                    intent.putExtra("Image1", recipesData.itemImage)
                    intent.putExtra("Title", recipesData.getItemName())
                    intent.putExtra("direction", recipesData.getItemDirection())
                    intent.putExtra(
                        "Description",
                        recipesData.getItemDescription().replace("_n", "\n")
                    )
                    context.startActivity(intent)
                }
            }
        }
    }
}