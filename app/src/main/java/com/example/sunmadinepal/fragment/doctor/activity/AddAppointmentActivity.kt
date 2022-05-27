package com.example.sunmadinepal.fragment.doctor.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatAutoCompleteTextView
import androidx.appcompat.widget.AppCompatEditText
import androidx.lifecycle.ViewModelProvider
import com.example.sunmadinepal.Navigation
import com.example.sunmadinepal.R
import com.example.sunmadinepal.calendarpicker.CalendarPickerDialogFragment
import com.example.sunmadinepal.calendarpicker.TimePickerDialogFragment
import com.example.sunmadinepal.databinding.ActivityAddAppointmentBinding
import com.example.sunmadinepal.fragment.child.viewmodel.AddChildViewModel
import com.example.sunmadinepal.fragment.doctor.model.AppointmentModel
import com.example.sunmadinepal.fragment.doctor.viewmodel.AddDoctorAppointmentViewModel
import com.example.sunmadinepal.utils.changeStatusBarColor
import com.example.sunmadinepal.utils.changeStatusBarIconTextColor
import com.example.sunmadinepal.utils.onErrorTextFieldValidator
import com.example.sunmadinepal.utils.openActivity
import com.mobsandgeeks.saripaar.ValidationError
import com.mobsandgeeks.saripaar.Validator
import com.mobsandgeeks.saripaar.annotation.NotEmpty

class AddAppointmentActivity : AppCompatActivity(), Validator.ValidationListener,
    CalendarPickerDialogFragment.OnOKCalendarClicked,
    TimePickerDialogFragment.OnOKTimePickerClicked {

    @NotEmpty(message = "Child Name Field is required")
    lateinit var childName: AppCompatAutoCompleteTextView

    @NotEmpty(message = "Hospital Field is required")
    lateinit var hospitalCenter: AppCompatAutoCompleteTextView

    @NotEmpty(message = "Doctor Name Field is required")
    lateinit var doctorName: AppCompatEditText

    @NotEmpty(message = "Appointment Date Field is required")
    lateinit var appointmentDate: AppCompatEditText

    lateinit var activityAddAppointmentBinding: ActivityAddAppointmentBinding
    private lateinit var addChildViewModel: AddChildViewModel
    private lateinit var addDoctorAppointmentViewModel: AddDoctorAppointmentViewModel

    lateinit var validator: Validator
    var pickedYear = 0
    var pickedMonth = ""
    var pickedDay = ""
    var childDateOfBirth = ""
    var gender = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityAddAppointmentBinding = ActivityAddAppointmentBinding.inflate(layoutInflater)
        setContentView(activityAddAppointmentBinding.root)

        changeStatusBarIconTextColor(true)
        changeStatusBarColor()

        addChildViewModel = ViewModelProvider(this).get(AddChildViewModel::class.java)
        addDoctorAppointmentViewModel = ViewModelProvider(this).get(AddDoctorAppointmentViewModel::class.java)

        initializing()

        activityAddAppointmentBinding.addAppointmentToolBar.toolbarActionTitle.text = getString(R.string.new_appointment)
        activityAddAppointmentBinding.addAppointmentToolBar.toolbarActionBackImageView.setOnClickListener {
            finish()
        }

        validator = Validator(this)
        validator.setValidationListener(this)
        addChildViewModel.getAllChildData.observe(this, {

            val positionAdapterSubject = ArrayAdapter(
                this,
                R.layout.support_simple_spinner_dropdown_item,
                it
            )
            activityAddAppointmentBinding.selectChildAutoTv.setAdapter(
                positionAdapterSubject
            )
        })


        val selectHospital = listOf(
            "Patan Hospital", "Global Hospital", "Kathmandu Hospital Pvt. Ltd.",
            "Hardik Poly Clinic Pvt. Ltd."
        )
        val positionAdapterSubject = ArrayAdapter(
            this,
            R.layout.support_simple_spinner_dropdown_item,
            selectHospital
        )
        activityAddAppointmentBinding.healthCenterAutoTv.setAdapter(
            positionAdapterSubject
        )

        activityAddAppointmentBinding.appointmentDateTimeTv.setOnClickListener {

            val ft = supportFragmentManager.beginTransaction()
            val dialogFragment = CalendarPickerDialogFragment()
            dialogFragment.itemClicked = this@AddAppointmentActivity
            dialogFragment.show(ft, "dialog")
        }

        activityAddAppointmentBinding.addButton.setOnClickListener {
            validator.validate()
        }
    }


    private fun initializing() {
        childName = activityAddAppointmentBinding.selectChildAutoTv
        doctorName = activityAddAppointmentBinding.doctorNameTv
        hospitalCenter = activityAddAppointmentBinding.healthCenterAutoTv
        appointmentDate = activityAddAppointmentBinding.appointmentDateTimeTv

    }


    @SuppressLint("SetTextI18n")
    override fun onOkItemClicked(year: Int, month: Int, day: Int) {
        pickedYear = year
        val addingZeroMonth = if (month > 9) {
            month
        } else {
            "0${month}"
        }

        val addingZeroDay = if (day > 9) {
            day
        } else {
            "0${day}"
        }
        Log.d("texfield", year.toString())
        Log.d("texfield", addingZeroDay.toString())

        pickedMonth = addingZeroMonth.toString()
        pickedDay = addingZeroDay.toString()
        val ft = supportFragmentManager.beginTransaction()
        val dialogFragment = TimePickerDialogFragment()
        dialogFragment.itemClicked = this@AddAppointmentActivity
        dialogFragment.show(ft, "dialog")
    }

    @SuppressLint("SetTextI18n")
    override fun onOkTimeClicked(hour: Int, minutes: Int, amOrPm: String) {
        val amOrPmChoose = when (amOrPm) {
            "0" -> {
                "AM"
            }
            "1" -> {
                "PM"
            }
            else -> {
                "AM"
            }
        }
        activityAddAppointmentBinding.appointmentDateTimeTv.setText("$pickedYear/$pickedMonth/$pickedDay $hour:$minutes $amOrPmChoose")
    }

    override fun onValidationSucceeded() {
        val childName = childName.text?.toString()
        val doctorName = doctorName.text.toString()
        val healthHospitalLocation = hospitalCenter.text.toString()
        val appointmentDate = appointmentDate.text.toString()


        addChildViewModel.getAllChildData.observe(this, {

            it.forEach {data->
                if (data.childName == childName){
                   childDateOfBirth = data.birthDate
                   gender = data.gender
                }
            }
        })

        val addAppointmentModel = AppointmentModel(
            childName.toString(), doctorName, healthHospitalLocation,
            appointmentDate, childDateOfBirth,gender
        )

        addDoctorAppointmentViewModel.addDoctorAppointmentViewModel(addAppointmentModel)

        openActivity(Navigation::class.java).apply {
            finish()
        }
    }

    override fun onValidationFailed(errors: MutableList<ValidationError>?) {
        onErrorTextFieldValidator(errors)
    }
}