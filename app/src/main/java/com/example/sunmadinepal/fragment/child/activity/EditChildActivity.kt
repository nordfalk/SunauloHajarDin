package com.example.sunmadinepal.fragment.child.activity

import android.annotation.SuppressLint
import android.app.Activity
import android.net.Uri
import android.os.Bundle
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
import com.example.sunmadinepal.databinding.ActivityEditChildBinding
import com.example.sunmadinepal.fragment.child.viewmodel.AddChildViewModel
import com.example.sunmadinepal.utils.onErrorTextFieldValidator
import com.example.sunmadinepal.utils.openActivity
import com.example.sunmadinepal.utils.showToast
import com.github.drjacky.imagepicker.ImagePicker
import com.mobsandgeeks.saripaar.ValidationError
import com.mobsandgeeks.saripaar.Validator
import com.mobsandgeeks.saripaar.annotation.NotEmpty

class EditChildActivity : AppCompatActivity(), CalendarPickerDialogFragment.OnOKCalendarClicked,
    Validator.ValidationListener {

    @NotEmpty(message = "Child Name Field is required")
    lateinit var editChildName: AppCompatEditText

    @NotEmpty(message = "Field is required")
    lateinit var editBirthDate: AppCompatEditText

    @NotEmpty(message = "Field is required")
    lateinit var editGender: AppCompatAutoCompleteTextView

    @NotEmpty(message = "Field is required")
    lateinit var editAllergies: AppCompatEditText

    lateinit var editChildBinding: ActivityEditChildBinding
    private var editUri: Uri? = null
    private lateinit var addChildViewModel: AddChildViewModel

    lateinit var validator: Validator
    var childId : Int = 0
    var intentChildImage : Uri?=null
    private val launcher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            when (result.resultCode) {
                Activity.RESULT_OK -> {
                    editUri = result.data?.data
                    editChildBinding.uploadedImage.setImageURI(editUri)
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
        editChildBinding = ActivityEditChildBinding.inflate(layoutInflater)
        setContentView(editChildBinding.root)

        editChildBinding.addChildToolBar.toolbarActionTitle.text =
           getString(R.string.edit_child)
        editChildBinding.addChildToolBar.toolbarActionBackImageView.setOnClickListener {
            finish()
        }

        childId = intent?.extras?.getInt("id",0) as Int
       val  childName = intent?.extras?.getString("child_name")as String
       val  birthDate = intent?.extras?.getString("birth_date")as String
       val  gender = intent?.extras?.getString("gender")as String
       val  allergies = intent?.extras?.getString("allergies") as String
        editUri = intent?.extras?.get("image") as Uri

        addChildViewModel = ViewModelProvider(this).get(AddChildViewModel::class.java)
        validator = Validator(this)
        validator.setValidationListener(this)
        initializing()


        fillingDataWhenEditClicked(childName,birthDate,gender,allergies, editUri!!)
        editChildBinding.uploadedImage.setOnClickListener {
            ImagePicker.with(this)
                .crop()
                .createIntentFromDialog {
                    launcher.launch(it)
                }
        }

        editChildBinding.editBirthDateText.setOnClickListener {
            val ft = supportFragmentManager.beginTransaction()
            val dialogFragment = CalendarPickerDialogFragment()
            dialogFragment.itemClicked = this@EditChildActivity
            dialogFragment.show(ft, "dialog")
        }

        editChildBinding.editDoneButton.setOnClickListener {
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
        editChildBinding.editGenderCompleteTv.setAdapter(
            positionAdapterSubject
        )
    }

    private fun fillingDataWhenEditClicked(childName:String,birthDate:String,
                                           gender:String,allergies:String, image:Uri) {

        editChildBinding.editChildNameTv.setText(childName)
        editChildBinding.editBirthDateText.setText(birthDate)
        editChildBinding.editAllergiesEditText.setText(allergies)

        editChildBinding.editGenderCompleteTv.setText(
            gender,
            false
        )

        editChildBinding.uploadedImage.setImageURI(image)
    }

    private fun initializing() {
        editChildName = editChildBinding.editChildNameTv
        editBirthDate = editChildBinding.editBirthDateText
        editGender = editChildBinding.editGenderCompleteTv
        editAllergies = editChildBinding.editAllergiesEditText

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
        editChildBinding.editBirthDateText.setText("$year/$addingZeroMonth/$addingZeroDay")

    }

    override fun onValidationSucceeded() {

        if (editUri != null) {
            val childName = editChildName.text?.toString()
            val birthDate = editBirthDate.text.toString()
            val gender = editGender.text.toString()
            val allergies = editAllergies.text.toString()

            addChildViewModel.updateChildViewModel(
                childId,
                childName.toString(), birthDate, gender, allergies, editUri?.path.toString()
            )
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