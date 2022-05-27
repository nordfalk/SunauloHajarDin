package com.example.sunmadinepal.fragment.health

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.sunmadinepal.R
import com.example.sunmadinepal.ViewModel.HealthViewModel
import com.example.sunmadinepal.databinding.HealthBinding
import com.example.sunmadinepal.fragment.health.activity.GeneralHealthInfoActivity
import com.example.sunmadinepal.model.setLocale
import java.util.*


class HealthFragment : Fragment(){

    private lateinit var healthViewModel : HealthViewModel

    lateinit var healthFragmentBinding: HealthBinding
    val string = Locale.getDefault().language
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View{

        healthFragmentBinding = HealthBinding.inflate(layoutInflater)
        return healthFragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        healthViewModel = ViewModelProvider(this).get(HealthViewModel::class.java)

        setLocale(requireContext(),string)

        healthFragmentBinding.healthToolBar.toolbarActionTitle.text = getString(R.string.health)
        goToDestinations()
    }

    private fun goToDestinations(){

        healthFragmentBinding.apply {

            generalInformation.setOnClickListener{
                val intent = Intent(requireContext(),GeneralHealthInfoActivity::class.java).apply {
                    putExtra("general_health", "general")
                    putExtra("title_name","General Health Info")
                }
                startActivity(intent)

            }
            lady0to6.setOnClickListener {
                val intent = Intent(requireContext(),GeneralHealthInfoActivity::class.java).apply {
                    putExtra("general_health", "lady_zero_six")
                    putExtra("title_name","0-6 months")
                }
                startActivity(intent)

            }
            lady6to9.setOnClickListener {

                val intent = Intent(requireContext(),GeneralHealthInfoActivity::class.java).apply {
                    putExtra("general_health", "lady_six_nine")
                    putExtra("title_name","6-9 months")
                }
                startActivity(intent)

            }
            lady9to12.setOnClickListener {

                val intent = Intent(requireContext(),GeneralHealthInfoActivity::class.java).apply {
                    putExtra("general_health", "lady_nine_twelve")
                    putExtra("title_name","9-12 months")
                }
                startActivity(intent)

            }
            lady12to24.setOnClickListener {

                val intent = Intent(requireContext(),GeneralHealthInfoActivity::class.java).apply {
                    putExtra("general_health", "twelve_twenty_four")
                    putExtra("title_name", "12-24 months")
                }
                startActivity(intent)

            }
            maternity.setOnClickListener {
                val intent = Intent(requireContext(),GeneralHealthInfoActivity::class.java).apply {
                    putExtra("general_health", "maternity")
                    putExtra("title_name", "Pregnancy")

                }
                startActivity(intent)
            }
        }
    }
}
