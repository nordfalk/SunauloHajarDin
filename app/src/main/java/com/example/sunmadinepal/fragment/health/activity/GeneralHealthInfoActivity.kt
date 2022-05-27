package com.example.sunmadinepal.fragment.health.activity

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.sunmadinepal.ViewModel.HealthViewModel
import com.example.sunmadinepal.database.adapter.CustomAdapter
import com.example.sunmadinepal.databinding.ActivityGeneralHealthInfoBinding
import com.example.sunmadinepal.model.RecipesData
import com.example.sunmadinepal.utils.changeStatusBarColor
import com.example.sunmadinepal.utils.changeStatusBarIconTextColor
import com.example.sunmadinepal.utils.hideProgressDialog
import com.example.sunmadinepal.utils.showProgressDialog
import java.util.*
import kotlin.collections.ArrayList

class GeneralHealthInfoActivity : AppCompatActivity() {
    private lateinit var activityGeneralHealthInfoBinding: ActivityGeneralHealthInfoBinding

    val string = Locale.getDefault().language
    private var _events = ArrayList<RecipesData>()
    private lateinit var healthViewModel: HealthViewModel

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityGeneralHealthInfoBinding = ActivityGeneralHealthInfoBinding.inflate(layoutInflater)
        setContentView(activityGeneralHealthInfoBinding.root)

        changeStatusBarIconTextColor(true)
        changeStatusBarColor()
        activityGeneralHealthInfoBinding.include.toolbarActionBackImageView.setOnClickListener {
            finish()
        }
        healthViewModel = ViewModelProvider(this).get(HealthViewModel::class.java)
        val receiveIntent = intent?.getStringExtra("general_health")
        val receiveTitle = intent.getStringExtra("title_name")

        activityGeneralHealthInfoBinding.include.toolbarActionTitle.text = receiveTitle

        when(receiveIntent){

            "general"->{
                if (string.equals("en")) {
                    healthViewModel.fetchEvent_GeneralHealth(
                        "GrowthMonitoringTitleEn", "GrowthMonitoringDescriptionEn",
                        "HandwashingTitleEn", "HandwashingDescriptionEn",
                        "VitaminsTitleEn", "VitaminsDescriptionEn",
                        "IronTitleEn", "IronDescriptionEn",
                        "AnemiaTitleEn", "AnemiaDescriptionEn",
                        "IronDeficiencyTitleEn", "IronDeficiencyDescriptionEn",
                        "SickchildTitleEn", "SickchildDescriptionEn",
                        "TDTitleEn", "TDDescriptionEn",
                        "MalnutritionTitleEn", "MalnutritionDescriptionEn"
                    )

                } else if (string.equals("ne")) {
                    healthViewModel.fetchEvent_GeneralHealth(
                        "GrowthMonitoringTitleNe", "GrowthMonitoringDescriptionNe",
                        "HandwashingTitleNe", "HandwashingDescriptionNe",
                        "VitaminsTitleNe", "VitaminsDescriptionNe",
                        "IronTitleNe", "IronDescriptionNe",
                        "AnemiaTitleNe", "AnemiaDescriptionNe",
                        "IronDeficiencyTitleNe", "IronDeficiencyDescriptionNe",
                        "SickchildTitleNe", "SickchildDescriptionNe",
                        "TDTitleNe", "TDDescriptionNe",
                        "MalnutritionTitleNe", "MalnutritionDescriptionNe"
                    )
                }
            }

            "lady_zero_six"->{
                if (string.equals("en")){
                    healthViewModel.fetchEvent_0_6_Months(
                        "0to6MonthsBreastfeedingTitleEn","0to6MonthsBreastfeedingDescriptionEn",
                        "0to6MonthsExaminationTitleEn","0to6MonthsExaminationDescriptionEn",
                        "0to6MonthsVitaminsTitleEn","0to6MonthsVitaminsDescriptionEn")


                }else if (string.equals("ne")){
                    healthViewModel.fetchEvent_0_6_Months(
                        "0to6MonthsBreastfeedingTitleNe","0to6MonthsBreastfeedingDescriptionNe",
                        "0to6MonthsExaminationTitleNe","0to6MonthsExaminationDescriptionNe",
                        "0to6MonthsVitaminsTitleNe","0to6MonthsVitaminsDescriptionNe")
                }
            }

            "lady_six_nine"->{
                if (string.equals("en")){
                    healthViewModel.fetchEvent_06_09_Months(
                        "6to9MonthsExaminationTitleEn","6to9MonthsExaminationDescriptionEn",
                        "6to9MonthsFeedingTitleEn","6to9MonthsFeedingDescriptionEn",
                        "6to9MonthsWaterTitleEn","6to9MonthsWaterDescriptionEn")

                }else if (string.equals("ne")){
                    healthViewModel.fetchEvent_06_09_Months(
                        "6to9MonthsExaminationTitleNe","6to9MonthsExaminationDescriptionNe",
                        "6to9MonthsFeedingTitleNe","6to9MonthsFeedingDescriptionNe",
                        "6to9MonthsWaterTitleNe","6to9MonthsWaterDescriptionNe")
                }
            }

            "lady_nine_twelve"->{
                if (string.equals("en")) {
                    healthViewModel.fetchEvent_09_12_Months(
                        "9to12MonthsHealthyEatingTitleEn", "9to12MonthsHealthyEatingDescriptionEn",
                        "9to12MonthsFeedingTitleEn", "9to12MonthsFeedingDescriptionEn",
                        "9to12MonthsWaterTitleEn", "9to12MonthsWaterDescriptionEn"
                    )

                } else if (string.equals("ne")) {
                    healthViewModel.fetchEvent_09_12_Months(
                        "9to12MonthsHealthyEatingTitleNe", "9to12MonthsHealthyEatingDescriptionNe",
                        "9to12MonthsFeedingTitleNe", "9to12MonthsFeedingDescriptionNe",
                        "9to12MonthsWaterTitleNe", "9to12MonthsWaterDescriptionNe"
                    )
                }
            }

            "twelve_twenty_four"->{
                if (string.equals("en")) {
                    healthViewModel.fetchEvent_12_24_Months(
                        "12to24MonthsFeedingTitleEn", "12to24MonthsFeedingDescriptionEn"
                    )

                } else if (string.equals("ne")) {
                    healthViewModel.fetchEvent_12_24_Months(
                        "12to24MonthsFeedingTitleNe", "12to24MonthsFeedingDescriptionNe"
                    )
                }
            }

            "maternity"->{
                if (string.equals("en")){
                    healthViewModel.fetchEvent_Maternity(
                        "PregnancyExaminationTitleEn", "PregnancyExaminationDescriptionEn",
                        "PregnancyFoodTypesTitleEn", "PregnancyFoodTypesDescriptionEn",
                        "PregnancyIrontabletTitleEn", "PregnancyIrontabletDescriptionEn",
                        "PregnancyRestTitleEn","PregnancyRestDescriptionEn")

                }else if (string.equals("ne")){
                    healthViewModel.fetchEvent_Maternity(
                        "PregnancyExaminationTitleNe", "PregnancyExaminationDescriptionNe",
                        "PregnancyFoodTypesTitleNe", "PregnancyFoodTypesDescriptionNe",
                        "PregnancyIrontabletTitleNe", "PregnancyIrontabletDescriptionNe",
                        "PregnancyRestTitleNe","PregnancyRestDescriptionNe")
                }
            }
        }


        showProgressDialog()

        activityGeneralHealthInfoBinding.recyclerView.adapter = CustomAdapter(this, _events, null)

        healthViewModel.events.observe(this, { event ->
            _events.removeAll(_events)
            _events.addAll(event)
            hideProgressDialog()
            activityGeneralHealthInfoBinding.recyclerView.adapter!!.notifyDataSetChanged()
        })
    }
}
