package com.example.sunmadinepal.fragment.health.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.sunmadinepal.databinding.ActivityHealthDetailBinding
import com.example.sunmadinepal.model.loadImage
import com.example.sunmadinepal.utils.changeStatusBarColor
import com.example.sunmadinepal.utils.changeStatusBarIconTextColor
import java.util.*

class HealthDetailActivity : AppCompatActivity() {

    lateinit var activityHealthDetailBinding: ActivityHealthDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityHealthDetailBinding = ActivityHealthDetailBinding.inflate(layoutInflater)
        setContentView(activityHealthDetailBinding.root)

        changeStatusBarIconTextColor(true)
        changeStatusBarColor()


        activityHealthDetailBinding.insideRecipeToolLayout.toolbarActionBackImageView.setOnClickListener {
            finish()
        }
        val string = Locale.getDefault().language
        val locale = Locale(string)
        Locale.setDefault(locale)


        val bundle: Bundle? = intent.extras
        if (bundle != null) {

            val titlel = bundle.getString("Title")
            activityHealthDetailBinding.insideRecipeToolLayout.toolbarActionTitle.text = titlel
            activityHealthDetailBinding.descriptionTv.text = bundle.getString("Description")

            loadImage(activityHealthDetailBinding.healthIv, bundle.getString("Image1").toString())
        }
    }
}