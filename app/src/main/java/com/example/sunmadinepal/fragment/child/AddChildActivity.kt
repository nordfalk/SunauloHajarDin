package com.example.sunmadinepal.fragment.child

import android.annotation.SuppressLint
import android.app.Activity
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatAutoCompleteTextView
import androidx.appcompat.widget.AppCompatEditText
import androidx.lifecycle.ViewModelProvider
import com.example.sunmadinepal.Navigation
import com.example.sunmadinepal.R
import com.example.sunmadinepal.calendarpicker.CalendarPickerDialogFragment
import com.example.sunmadinepal.databinding.ActivityAddChildBinding
import com.example.sunmadinepal.fragment.child.model.AddChildModel
import com.example.sunmadinepal.fragment.child.viewmodel.AddChildViewModel
import com.example.sunmadinepal.utils.*
import com.github.drjacky.imagepicker.ImagePicker
import com.mobsandgeeks.saripaar.ValidationError
import com.mobsandgeeks.saripaar.Validator
import com.mobsandgeeks.saripaar.annotation.NotEmpty


class AddChildActivity : AppCompatActivity(),
    CalendarPickerDialogFragment.OnOKCalendarClicked, Validator.ValidationListener {

    @NotEmpty(message = "Child Name Field is required")
    lateinit var childName: AppCompatEditText

    @NotEmpty(message = "Field is required")
    lateinit var birthDate: AppCompatEditText

    @NotEmpty(message = "Field is required")
    lateinit var gender: AppCompatAutoCompleteTextView

    @NotEmpty(message = "Field is required")
    lateinit var allergies: AppCompatEditText

    lateinit var addChildBinding: ActivityAddChildBinding
    private var uri: Uri? = null
    private lateinit var addChildViewModel: AddChildViewModel

    lateinit var validator: Validator

    private val launcher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            when (result.resultCode) {
                Activity.RESULT_OK -> {
                    uri = result.data?.data
                    addChildBinding.uploadedImage.setImageURI(uri)
                    Log.d("imageurl", uri.toString())
                }
                ImagePicker.RESULT_ERROR -> {
                    showToast(ImagePicker.getError(result.data))
                }
                else -> {
                    showToast("Cancelled")
                }
            }
        }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        changeStatusBarIconTextColor(true)
        changeStatusBarColor()

        addChildBinding = ActivityAddChildBinding.inflate(layoutInflater)
        setContentView(addChildBinding.root)

        addChildViewModel = ViewModelProvider(this).get(AddChildViewModel::class.java)
        validator = Validator(this)
        validator.setValidationListener(this)
        initializing()

        addChildBinding.addChildToolBar.toolbarActionTitle.text = getString(R.string.add_child)
        addChildBinding.addChildToolBar.toolbarActionBackImageView.setOnClickListener {
            finish()
        }
        addChildBinding.uploadedImage.setOnClickListener {
            ImagePicker.with(this)
                .crop()
                .createIntentFromDialog {
                    launcher.launch(it)
                }
        }

        addChildBinding.birthDateText.setOnClickListener {
            val ft = supportFragmentManager.beginTransaction()
            val dialogFragment = CalendarPickerDialogFragment()
            dialogFragment.itemClicked = this@AddChildActivity
            dialogFragment.show(ft, "dialog")
        }

        addChildBinding.doneButton.setOnClickListener {
            validator.validate()
        }

        val selectGender = listOf(
            "Male", "Female", "Others"
        )
        val positionAdapterSubject = ArrayAdapter(
            this,
            R.layout.support_simple_spinner_dropdown_item,
            selectGender
        )
        addChildBinding.genderCompleteTv.setAdapter(
            positionAdapterSubject
        )
    }

    private fun initializing() {
        childName = addChildBinding.childNameTv
        birthDate = addChildBinding.birthDateText
        gender = addChildBinding.genderCompleteTv
        allergies = addChildBinding.allergiesEditText

    }

    @SuppressLint("SetTextI18n")
    override fun onOkItemClicked(year: Int, month: Int, day: Int) {
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
        addChildBinding.birthDateText.setText("$year/$addingZeroMonth/$addingZeroDay")

    }

    override fun onValidationSucceeded() {

        if (uri != null) {
            val childName = childName.text?.toString()
            val birthDate = birthDate.text.toString()
            val gender = gender.text.toString()
            val allergies = allergies.text.toString()
            val childModel = AddChildModel(
                childName.toString(), birthDate, gender, allergies,
                uri!!
            )
            addChildViewModel.addChildViewModel(childModel)
            openActivity(Navigation::class.java).apply {
                finish()
            }
        } else {
            showToast("Please Upload Image")
        }

    }


    override fun onValidationFailed(errors: MutableList<ValidationError>?) {
        onErrorTextFieldValidator(errors)
    }
}