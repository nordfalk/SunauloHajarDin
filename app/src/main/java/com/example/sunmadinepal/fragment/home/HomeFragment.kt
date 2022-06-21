package com.example.sunmadinepal.fragment.home

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import androidx.appcompat.app.AlertDialog
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.PagerSnapHelper
import com.example.helper.UpdatedLocaleHelper
import com.example.sunmadinepal.R
import com.example.sunmadinepal.ViewModel.DoctorAppointmentViewModel
import com.example.sunmadinepal.ViewModel.HealthViewModel
import com.example.sunmadinepal.ViewModel.HomeViewModel
import com.example.sunmadinepal.ViewModel.RecipesViewModel
import com.example.sunmadinepal.databinding.FragmentHomeBinding
import com.example.sunmadinepal.fragment.child.AddChildActivity
import com.example.sunmadinepal.fragment.child.activity.EditChildActivity
import com.example.sunmadinepal.fragment.child.model.AddChildModel
import com.example.sunmadinepal.fragment.child.viewmodel.AddChildViewModel
import com.example.sunmadinepal.fragment.doctor.activity.EditAppointmentActivity
import com.example.sunmadinepal.fragment.doctor.adapter.AppointmentAdapter
import com.example.sunmadinepal.fragment.doctor.model.AppointmentModel
import com.example.sunmadinepal.fragment.doctor.viewmodel.AddDoctorAppointmentViewModel
import com.example.sunmadinepal.fragment.home.adapter.DailyHealthTipsAdapter
import com.example.sunmadinepal.fragment.home.adapter.MyChildrenAdapter
import com.example.sunmadinepal.fragment.home.model.DailyHealthTipsModel
import com.example.sunmadinepal.fragment.nutrition.activity.NutritionFoodItemsActivity
import com.example.sunmadinepal.model.RecipesData
import com.example.sunmadinepal.test.TestAdapter
import com.example.sunmadinepal.utils.openActivity
import com.google.android.material.button.MaterialButton
import java.util.*


class HomeFragment : Fragment(), AppointmentAdapter.OnDoctorAppointmentActionClicked,
    MyChildrenAdapter.OnHomeChildEditClicked, TestAdapter.OnHomeChildClicked,
    DailyHealthTipsAdapter.ReadMoreClickListener {

    private lateinit var doctorAppointmentViewModel: DoctorAppointmentViewModel
    lateinit var appointmentViewModel: AddDoctorAppointmentViewModel
    lateinit var addChildViewModel: AddChildViewModel
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var _binding: FragmentHomeBinding
    private lateinit var healthViewModel : HealthViewModel
    private lateinit var breastFeedingViewModel : RecipesViewModel

    private var _events = ArrayList<RecipesData>()


    val string = Locale.getDefault().getLanguage()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        appointmentViewModel = ViewModelProvider(this).get(AddDoctorAppointmentViewModel::class.java)

        doctorAppointmentViewModel = ViewModelProvider(this).get(DoctorAppointmentViewModel::class.java)
        addChildViewModel = ViewModelProvider(this).get(AddChildViewModel::class.java)
        healthViewModel = ViewModelProvider(this).get(HealthViewModel::class.java)
        breastFeedingViewModel = ViewModelProvider(this).get(RecipesViewModel::class.java)


        _binding.selectLanguage.onItemClickListener =
            OnItemClickListener { parent, _, position, _ ->
                val selectedItem = parent.getItemAtPosition(position) as String

                if (selectedItem == "ENG") {
                    UpdatedLocaleHelper.setLanguage(requireContext(), "en")

                } else if (selectedItem == "ने") {
                    UpdatedLocaleHelper.setLanguage(requireContext(), "ne")
                }
                requireActivity().recreate()
            }

        val healthyTipsModel = listOf(
            DailyHealthTipsModel(
                getString(R.string.health_tip_1_desc),
                ResourcesCompat.getDrawable(resources, R.drawable.health_tip_1_image, null),
            ),
            DailyHealthTipsModel(
                getString(R.string.health_tip_2_desc),
                ResourcesCompat.getDrawable(resources, R.drawable.health_tip_2_image, null),
            ),
            DailyHealthTipsModel(
                getString(R.string.health_tip_3_desc),
                ResourcesCompat.getDrawable(resources, R.drawable.health_tip_3_image, null),
            ),
            DailyHealthTipsModel(
                getString(R.string.health_tip_4_desc),
                ResourcesCompat.getDrawable(resources, R.drawable.whentowashhands, null),
            ),
            DailyHealthTipsModel(
                getString(R.string.health_tip_5_desc),
                ResourcesCompat.getDrawable(resources, R.drawable.health_tip_5_image, null),
            ),
            DailyHealthTipsModel(
                getString(R.string.health_tip_6_desc),
                ResourcesCompat.getDrawable(resources, R.drawable.health_tip_6_image, null),
            )
        )

        _binding.noChildRoundImage.setOnClickListener {
            requireActivity().openActivity(AddChildActivity::class.java)
        }
        _binding.dailyHealthTipsRecyclerView.adapter = DailyHealthTipsAdapter(this).apply {
            PagerSnapHelper().also { it.attachToRecyclerView(_binding.dailyHealthTipsRecyclerView) }
            submitList(healthyTipsModel)
        }

        appointmentViewModel.getAllAppointmentData.observe(viewLifecycleOwner, { appointList ->
            val reversedList = appointList.reversed()
            if (reversedList.isNotEmpty()) {
                _binding.upcomingAppointmentTv.visibility = View.VISIBLE
                _binding.noUpComingAppointment.visibility = View.GONE
                _binding.upComingRv.visibility = View.VISIBLE
                _binding.upComingRv.adapter = AppointmentAdapter(this).apply {
                    submitList(reversedList)
                }
            } else {
                _binding.upcomingAppointmentTv.visibility = View.VISIBLE
                _binding.noUpComingAppointment.visibility = View.VISIBLE
                _binding.upComingRv.visibility = View.GONE
            }
        })

        addChildViewModel.getAllChildData.observe(viewLifecycleOwner, {
            if (it.isNotEmpty()) {
                _binding.noChildRoundImage.visibility = View.INVISIBLE
                val menusList: MutableList<Any> = mutableListOf()
                menusList.addAll(it)
                menusList.add(1)
                _binding.myChildrenRecyclerView.adapter =
                    TestAdapter(menusList.toMutableList(), this)
            } else {
                _binding.noChildRoundImage.visibility = View.VISIBLE
            }
        })

        if (string.equals("en")) {
            healthViewModel.fetchEvent_GeneralHealth(
                "GrowthMonitoringTitleEn", "GrowthMonitoringDescriptionEn",
                "HandwashingTitleEn", "HandwashingDescriptionEn",
                "HealthingEatingHabitsTitleEN", "HealthingEatingHabitsDescriptionEN",
                "MalnutritionTitleEn", "MalnutritionDescriptionEn",
                "VitaminsTitleEn", "VitaminsDescriptionEn",
                "IronTitleEn", "IronDescriptionEn",
                "AnemiaTitleEn", "AnemiaDescriptionEn",
                "TDTitleEn", "TDDescriptionEn",
                "SickchildTitleEn", "SickchildDescriptionEn"
            )

        } else if (string.equals("ne")) {
            healthViewModel.fetchEvent_GeneralHealth(
                "GrowthMonitoringTitleNe", "GrowthMonitoringDescriptionNe",
                "HandwashingTitleNe", "HandwashingDescriptionNe",
                "HealthingEatingHabitsTitleNP", "HealthingEatingHabitsDescriptionNP",
                "MalnutritionTitleNe", "MalnutritionDescriptionNe",
                "VitaminsTitleNe", "VitaminsDescriptionNe",
                "IronTitleNe", "IronDescriptionNe",
                "AnemiaTitleNe", "AnemiaDescriptionNe",
                "TDTitleNe", "TDDescriptionNe",
                "SickchildTitleNe", "SickchildDescriptionNe"
            )
        }

        if (string.equals("en")){
            breastFeedingViewModel.fetchEvent_0_6_Months(
                "0to6MonthsBreastfeedingTitleEn","0to6MonthsBreastfeedingDescriptionEn",
                "0to6MonthsExaminationTitleEn","0to6MonthsExaminationDescriptionEn",
                "0to6MonthsVitaminsTitleEn","0to6MonthsVitaminsDescriptionEn")


        }else if (string.equals("ne")){
            breastFeedingViewModel.fetchEvent_0_6_Months(
                "0to6MonthsBreastfeedingTitleNe","0to6MonthsBreastfeedingDescriptionNe",
                "0to6MonthsExaminationTitleNe","0to6MonthsExaminationDescriptionNe",
                "0to6MonthsVitaminsTitleNe","0to6MonthsVitaminsDescriptionNe")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(layoutInflater)
        return _binding.root
    }

    override fun onDoctorEditClicked(appointmentModel: AppointmentModel) {
        requireActivity().openActivity(EditAppointmentActivity::class.java) {
            putSerializable("appointmentModel", appointmentModel)
        }.apply {
            requireActivity().finish()
        }
    }

    override fun onDoctorDeleteClicked(appointmentModel: AppointmentModel) {
        val view = layoutInflater.inflate(R.layout.delete_appointment_dialog, null)
        val alertDialog = AlertDialog.Builder(requireContext()).create()
        val deleteButton = view.findViewById<MaterialButton>(R.id.delete_button)
        val cancelButton = view.findViewById<MaterialButton>(R.id.cancel_button)

        deleteButton.setOnClickListener {
            alertDialog.dismiss()
            appointmentViewModel.deleteAppointmentViewModel(appointmentModel.id)
        }
        cancelButton.setOnClickListener {
            alertDialog.dismiss()
        }

        alertDialog.setView(view)
        alertDialog.setCanceledOnTouchOutside(false)
        alertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        alertDialog.show()
    }

    override fun onHomeChildEditItemClicked(addChildModel: AddChildModel) {

        val intent = Intent(requireContext(), EditChildActivity::class.java)
        intent.putExtra("id", addChildModel.id)
        intent.putExtra("child_name", addChildModel.childName)
        intent.putExtra("birth_date", addChildModel.birthDate)
        intent.putExtra("gender", addChildModel.gender)
        intent.putExtra("allergies", addChildModel.allergies)
        intent.putExtra("image", addChildModel.image)
        startActivity(intent)
    }

    override fun onHomeChildEditItemClicked(addChildModel: Any) {
        val addChildData = addChildModel as AddChildModel
        val intent = Intent(requireContext(), EditChildActivity::class.java)
        intent.putExtra("id", addChildData.id)
        intent.putExtra("child_name", addChildData.childName)
        intent.putExtra("birth_date", addChildData.birthDate)
        intent.putExtra("gender", addChildData.gender)
        intent.putExtra("allergies", addChildData.allergies)
        intent.putExtra("image", addChildData.image)
        startActivity(intent)
    }

    override fun onAddChildItemClicked() {
        requireActivity().openActivity(AddChildActivity::class.java)
    }

    override fun onResume() {
        super.onResume()
        val string = Locale.getDefault().language

        if (string == "ne") {
            _binding.selectLanguage.setText("ने")

            val languageSelect = listOf(
                "ENG", "ने"
            )
            val positionAdapterLanguage = ArrayAdapter(
                requireContext(),
                R.layout.support_simple_spinner_dropdown_item,
                languageSelect
            )
            _binding.selectLanguage.setAdapter(
                positionAdapterLanguage
            )

        } else {
            _binding.selectLanguage.setText("ENG")

            val languageSelect = listOf(
                "ENG", "ने"
            )
            val positionAdapterLanguage = ArrayAdapter(
                requireContext(),
                R.layout.support_simple_spinner_dropdown_item,
                languageSelect
            )
            _binding.selectLanguage.setAdapter(
                positionAdapterLanguage
            )
        }
    }

    override fun onReadMoreClicked(dailyHealthTipsModel: DailyHealthTipsModel, position: Int) {

        when(position){
            0->{
                val intent = Intent(requireContext(), NutritionFoodItemsActivity::class.java).apply {
                    putExtra("tool_bar_title", getString(R.string.recipe_for_strength))
                }
                startActivity(intent)
            }

            1->{
                val intent = Intent(requireContext(), NutritionFoodItemsActivity::class.java).apply {
                    putExtra("tool_bar_title", getString(R.string.recipe_for_protection))
                }
                startActivity(intent)
            }

            2->{
                val intent = Intent(requireContext(), NutritionFoodItemsActivity::class.java).apply {
                    putExtra("tool_bar_title", getString(R.string.recipe_for_energy))
                }
                startActivity(intent)
            }

            3->{
                healthViewModel.events.observe(this, {
                        event ->
                    val viewModelData = event[1]
                    val intent = Intent(requireContext(), DailyHealthTipsDetailActivity::class.java).apply {
                        putExtra("list_data",viewModelData)
                    }
                    startActivity(intent)
                })
            }
            4->{
                healthViewModel.events.observe(this, {
                        event ->
                    val viewModelData = event[2]
                    val intent = Intent(requireContext(), DailyHealthTipsDetailActivity::class.java).apply {
                        putExtra("list_data",viewModelData)
                    }
                    startActivity(intent)
                })
            }
            5->{
                breastFeedingViewModel.events.observe(this, {
                        event ->
                    val viewModelData = event.last()
                    val intent = Intent(requireContext(), DailyHealthTipsDetailActivity::class.java).apply {
                        putExtra("list_data",viewModelData)
                    }
                    startActivity(intent)
                })
            }
        }
    }
}