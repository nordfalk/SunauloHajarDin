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
import com.example.sunmadinepal.databinding.ActivityEditAppointmentBinding
import com.example.sunmadinepal.fragment.child.viewmodel.AddChildViewModel
import com.example.sunmadinepal.fragment.doctor.model.AppointmentModel
import com.example.sunmadinepal.fragment.doctor.viewmodel.AddDoctorAppointmentViewModel
import com.example.sunmadinepal.utils.onErrorTextFieldValidator
import com.example.sunmadinepal.utils.openActivity
import com.mobsandgeeks.saripaar.ValidationError
import com.mobsandgeeks.saripaar.Validator
import com.mobsandgeeks.saripaar.annotation.NotEmpty

class EditAppointmentActivity : AppCompatActivity(), Validator.ValidationListener,
    CalendarPickerDialogFragment.OnOKCalendarClicked,
    TimePickerDialogFragment.OnOKTimePickerClicked {

    @NotEmpty(message = "Child Name Field is required")
    lateinit var editChildName: AppCompatAutoCompleteTextView

    @NotEmpty(message = "Hospital Field is required")
    lateinit var editHospitalCenter: AppCompatAutoCompleteTextView

    @NotEmpty(message = "Doctor Name Field is required")
    lateinit var editDoctorName: AppCompatEditText

    @NotEmpty(message = "Appointment Date Field is required")
    lateinit var editAppointmentDate: AppCompatEditText

    lateinit var editAppointmentBinding: ActivityEditAppointmentBinding
    private lateinit var addChildViewModel: AddChildViewModel
    private lateinit var addDoctorAppointmentViewModel: AddDoctorAppointmentViewModel

    lateinit var validator: Validator
    var pickedYear = 0
    var pickedMonth = ""
    var pickedDay = ""
    var appointmentIntentData: AppointmentModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        editAppointmentBinding = ActivityEditAppointmentBinding.inflate(layoutInflater)
        setContentView(editAppointmentBinding.root)

        editAppointmentBinding.editAppointmentToolBar.toolbarActionTitle.text =
            getString(R.string.edit_appointment)
        editAppointmentBinding.editAppointmentToolBar.toolbarActionBackImageView.setOnClickListener {
            finish()
        }
        appointmentIntentData = intent.extras?.get("appointmentModel") as AppointmentModel
        Log.d("dataintent", appointmentIntentData.toString())

        fillingDataWhenEditClicked(appointmentIntentData!!)

        addChildViewModel = ViewModelProvider(this).get(AddChildViewModel::class.java)
        addDoctorAppointmentViewModel =
            ViewModelProvider(this).get(AddDoctorAppointmentViewModel::class.java)

        initializing()


        validator = Validator(this)
        validator.setValidationListener(this)
        addChildViewModel.getAllChildData.observe(this, {

            val positionAdapterSubject = ArrayAdapter(
                this,
                R.layout.support_simple_spinner_dropdown_item,
                it
            )
            editAppointmentBinding.editSelectChildAutoTv.setAdapter(
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
        editAppointmentBinding.editHealthCenterAutoTv.setAdapter(
            positionAdapterSubject
        )

        editAppointmentBinding.calendarSelect.setOnClickListener {

            val ft = supportFragmentManager.beginTransaction()
            val dialogFragment = CalendarPickerDialogFragment()
            dialogFragment.itemClicked = this@EditAppointmentActivity
            dialogFragment.show(ft, "dialog")
        }

        editAppointmentBinding.addButton.setOnClickListener {
            validator.validate()
        }
    }

    private fun initializing() {
        editChildName = editAppointmentBinding.editSelectChildAutoTv
        editDoctorName = editAppointmentBinding.editDoctorNameTv
        editHospitalCenter = editAppointmentBinding.editHealthCenterAutoTv
        editAppointmentDate = editAppointmentBinding.editAppointmentDateTimeTv

    }

    private fun fillingDataWhenEditClicked(appointmentIntentData: AppointmentModel) {

        editAppointmentBinding.editDoctorNameTv.setText(appointmentIntentData.doctorName)
        editAppointmentBinding.editDoctorNameTv.setText(appointmentIntentData.doctorName)
        editAppointmentBinding.editAppointmentDateTimeTv.setText(appointmentIntentData.appointmentDate)

        editAppointmentBinding.editSelectChildAutoTv.setText(
            appointmentIntentData.childName,
            false
        )

        editAppointmentBinding.editHealthCenterAutoTv.setText(
            appointmentIntentData.healthCenterLocation,
            false
        )

    }


    override fun onValidationSucceeded() {
        val childName = editChildName.text.toString()
        val doctorName = editDoctorName.text.toString()
        val healthHospitalLocation = editHospitalCenter.text.toString()
        val appointmentDate = editAppointmentDate.text.toString()

        addDoctorAppointmentViewModel.updateAppointmentViewModel(
            appointmentIntentData?.id!!,
            childName,
            doctorName,
            healthHospitalLocation,
            appointmentDate
        )

        openActivity(Navigation::class.java).apply {
            finish()
        }
    }

    override fun onValidationFailed(errors: MutableList<ValidationError>?) {
        onErrorTextFieldValidator(errors)
    }

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
        dialogFragment.itemClicked = this@EditAppointmentActivity
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
        editAppointmentBinding.editAppointmentDateTimeTv.setText("$pickedYear/$pickedMonth/$pickedDay $hour:$minutes $amOrPmChoose")

    }
}