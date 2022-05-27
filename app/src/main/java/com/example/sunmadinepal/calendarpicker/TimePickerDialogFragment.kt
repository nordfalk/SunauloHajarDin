package com.example.sunmadinepal.calendarpicker

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.NumberPicker
import androidx.fragment.app.DialogFragment
import com.example.helper.HourAndMinute
import com.example.helper.UpdatedLocaleHelper
import com.example.sunmadinepal.databinding.TimePickerDialogBinding
import java.util.*

class TimePickerDialogFragment : DialogFragment() {
    private lateinit var timePickerDialogBinding: TimePickerDialogBinding
    var itemClicked: OnOKTimePickerClicked? = null
    private var hour = 0
    private var minutes = 0
    private var amOrPmValue =""
    private val calendar = Calendar.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        timePickerDialogBinding =
            TimePickerDialogBinding.inflate(layoutInflater)
        if (dialog != null && dialog?.window != null) {
            dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog?.window?.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        }
        return timePickerDialogBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val amOrPm = arrayOf("AM", "PM")

        val string = Locale.getDefault().language

        if (string == "ne") {
            UpdatedLocaleHelper.setLanguage(requireContext(), "ne")
        }else{
            UpdatedLocaleHelper.setLanguage(requireContext(), "en")

        }

        hour = calendar.get(Calendar.HOUR)
        minutes  = calendar.get(Calendar.MINUTE)
        HourAndMinute.hour.forEach {_->
            setHourPicker(
                timePickerDialogBinding.hourPicker,
                hour
            )
        }

        HourAndMinute.minute.forEach {_->
            setMinutePicker(
                timePickerDialogBinding.minutePicker,
                minutes
            )
        }

        setAmOrPm(timePickerDialogBinding.amPmPicker, amOrPm)

        timePickerDialogBinding.hourPicker.setOnValueChangedListener { _, _, _ ->
            hour = timePickerDialogBinding.hourPicker.value

        }

        timePickerDialogBinding.minutePicker.setOnValueChangedListener { _, _, _ ->
            minutes = timePickerDialogBinding.minutePicker.value

        }

        timePickerDialogBinding.amPmPicker.setOnValueChangedListener { _, _, _ ->
            amOrPmValue = timePickerDialogBinding.amPmPicker.value.toString()

        }
        timePickerDialogBinding.okTimePicker.setOnClickListener {
            dialog?.dismiss()
            pickTimeConfirmClick(hour,minutes,amOrPmValue)
        }

        timePickerDialogBinding.dismissTimeDialog.setOnClickListener {
            dialog?.dismiss()
        }
    }

    private fun pickTimeConfirmClick(
        pickedHour: Int,
        pickedMinute:Int,
        amOrPm: String
    ) {
        itemClicked?.onOkTimeClicked(pickedHour,pickedMinute,amOrPm)
    }

    private fun setAmOrPm(amPmPicker: NumberPicker, it: Array<String>) {

        amPmPicker.minValue = 0
        amPmPicker.maxValue = it.size - 1
        amPmPicker.wrapSelectorWheel = true
        amPmPicker.displayedValues = it
    }

    private fun setHourPicker(hourPicker: NumberPicker, hour: Int) {

        hourPicker.maxValue = 12
        hourPicker.minValue = 1
        hourPicker.value = hour
    }

    private fun setMinutePicker(minutePicker: NumberPicker, minute: Int) {
        minutePicker.maxValue = 59
        minutePicker.minValue = 0
        minutePicker.value = minute
        minutePicker.setFormatter { i -> String.format("%02d", i) }
    }

    interface OnOKTimePickerClicked {
        fun onOkTimeClicked(hour:Int, minutes: Int,amOrPm: String)
    }
}