package com.example.sunmadinepal.fragment.home

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.sunmadinepal.databinding.ActivityDailyHealthTipsDetailBinding
import com.example.sunmadinepal.model.RecipesData
import com.example.sunmadinepal.utils.changeStatusBarColor
import com.example.sunmadinepal.utils.changeStatusBarIconTextColor

class DailyHealthTipsDetailActivity : AppCompatActivity() {

    lateinit var activityDailyHealthTipsDetailBinding: ActivityDailyHealthTipsDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityDailyHealthTipsDetailBinding =
            ActivityDailyHealthTipsDetailBinding.inflate(layoutInflater)
        setContentView(activityDailyHealthTipsDetailBinding.root)

        changeStatusBarIconTextColor(true)
        changeStatusBarColor()

        val listData = intent.getSerializableExtra("list_data") as RecipesData
        activityDailyHealthTipsDetailBinding.healthTipsToolLayout.toolbarActionTitle.text = listData.itemName

        activityDailyHealthTipsDetailBinding.healthTipsToolLayout.toolbarActionBackImageView.setOnClickListener {
            finish()
        }

        activityDailyHealthTipsDetailBinding.descriptionHealthTipsTv.text = listData.itemDescription.replace("_n", "\n")
        Glide.with(this).load(listData.itemImage).into(activityDailyHealthTipsDetailBinding.healthToolTipsIv)

        Log.d("dailyhealimge",listData.itemImage)
    }
}