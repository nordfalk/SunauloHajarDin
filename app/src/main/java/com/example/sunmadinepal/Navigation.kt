package com.example.sunmadinepal

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.sunmadinepal.databinding.ActivityNavigationBinding
import com.example.sunmadinepal.utils.changeStatusBarColor
import com.example.sunmadinepal.utils.changeStatusBarIconTextColor
import com.google.android.material.bottomnavigation.BottomNavigationView


class Navigation : AppCompatActivity() {

    private lateinit var binding: ActivityNavigationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNavigationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_activity_navigation)

        navView.setupWithNavController(navController)

        changeStatusBarIconTextColor(true)
        changeStatusBarColor()

        val actionBar = supportActionBar
        actionBar?.setHomeButtonEnabled(true)

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}