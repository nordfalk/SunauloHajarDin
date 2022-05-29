package com.example.sunmadinepal.calendarpicker

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.NumberPicker
import androidx.fragment.app.DialogFragment
import com.example.helper.EnglishAndNepaliDateMonth.Companion.englishMonthsTable
import com.example.helper.EnglishAndNepaliDateMonth.Companion.monthsInEnglish
import com.example.helper.EnglishAndNepaliDateMonth.Companion.monthsInNepali
import com.example.helper.EnglishAndNepaliDateMonth.Companion.yearMonthSpanLookupTable
import com.example.helper.UpdatedLocaleHelper
import com.example.sunmadinepal.databinding.DateCalendarFragmentDialogLayoutBinding
import com.example.sunmadinepal.dateconverter.Converter
import java.text.SimpleDateFormat
import java.util.*

class CalendarPickerDialogFragment : DialogFragment() {

    private var isAdOrBs = false
    private lateinit var fragmentCalendarPickerDialogFragment: DateCalendarFragmentDialogLayoutBinding
    var itemClicked: OnOKCalendarClicked? = null
    private val converter = Converter()
    private val calendar = Calendar.getInstance()
    private val englishYearFormatter = SimpleDateFormat("yyyy", Locale.US).format(calendar.time).toInt()
    private val englishMonthFormatter = SimpleDateFormat("MM", Locale.US).format(calendar.time).toInt()
    private val englishDayFormatter = SimpleDateFormat("dd", Locale.US).format(calendar.time).toInt()
    private var dateYear = 0
    private var dateMonths = 0
    private var dateDay = 0


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        fragmentCalendarPickerDialogFragment =
            DateCalendarFragmentDialogLayoutBinding.inflate(layoutInflater)
        if (dialog != null && dialog?.window != null) {
            dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog?.window?.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        }
        return fragmentCalendarPickerDialogFragment.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val string = Locale.getDefault().language

        if (string == "ne") {
            UpdatedLocaleHelper.setLanguage(requireContext(), "ne")
        }else{
            UpdatedLocaleHelper.setLanguage(requireContext(), "en")

        }

        val currentDateNepaliConverter =
            converter.getNepaliDate(
                englishYearFormatter,
                englishMonthFormatter,
                englishDayFormatter
            )
        dateYear = currentDateNepaliConverter.saal
        dateMonths = currentDateNepaliConverter.mahina - 1
        dateDay = currentDateNepaliConverter.gatey


        setYearWheelPicker(
            fragmentCalendarPickerDialogFragment.yearPicker,
            false,
            dateYear
        )


        setMonthsWheelPicker(fragmentCalendarPickerDialogFragment.monthPicker, false)

        setDayWheelPicker(
            fragmentCalendarPickerDialogFragment.dayPicker,
            dateYear,
            dateMonths,
            false
        )


        fragmentCalendarPickerDialogFragment.yearPicker.setOnValueChangedListener { _, _, _ ->
            dateYear = fragmentCalendarPickerDialogFragment.yearPicker.value
            if (isAdOrBs) {
                setMonthsWheelPicker(fragmentCalendarPickerDialogFragment.monthPicker, true)
                setDayWheelPicker(
                    fragmentCalendarPickerDialogFragment.dayPicker,
                    englishYearFormatter,
                    dateMonths,
                    isAdOrBs
                )
            } else {
                setMonthsWheelPicker(fragmentCalendarPickerDialogFragment.monthPicker, false)
                setDayWheelPicker(
                    fragmentCalendarPickerDialogFragment.dayPicker,
                    dateYear,
                    dateMonths,
                    isAdOrBs
                )
            }
        }

        fragmentCalendarPickerDialogFragment.monthPicker.setOnValueChangedListener { numberPicker, _, _ ->
            dateMonths = numberPicker.value
            setDayWheelPicker(
                fragmentCalendarPickerDialogFragment.dayPicker,
                dateYear,
                dateMonths,
                isAdOrBs
            )
        }

        fragmentCalendarPickerDialogFragment.dayPicker.setOnValueChangedListener { _, _, _ ->
            dateDay = fragmentCalendarPickerDialogFragment.dayPicker.value
        }


        fragmentCalendarPickerDialogFragment.dismissCalendarDialog.setOnClickListener {
            dialog?.dismiss()
        }


        fragmentCalendarPickerDialogFragment.okCalendar.setOnClickListener {
            dialog?.dismiss()
            Log.d("pickerclicked", dateYear.toString())
            Log.d("pickerclicked", dateMonths.toString())
            pickDateConfirmClick(dateYear, dateMonths, dateDay)

        }
    }


    private fun pickDateConfirmClick(
        pickedYear: Int,
        pickedMonths: Int,
        pickedDay: Int
    ) {
        itemClicked?.onOkItemClicked(pickedYear, pickedMonths + 1, pickedDay)
    }


    private fun setDayWheelPicker(
        numberPicker: NumberPicker,
        year: Int,
        months: Int,
        adOrBs: Boolean
    ) {

        val maxValue = if (adOrBs) {
            englishMonthsTable[0][months]
        } else {
            yearMonthSpanLookupTable[(year - 2000)][months]
        }

        numberPicker.maxValue = maxValue
        numberPicker.minValue = 1
        numberPicker.value = dateDay

    }

    private fun setYearWheelPicker(
        numberPicker: NumberPicker,
        adOrBs: Boolean,
        numPickerYear: Int
    ) {
        if (adOrBs) {
            numberPicker.maxValue = 2042
            numberPicker.minValue = 1943
            numberPicker.value = numPickerYear
        } else {
            numberPicker.maxValue = 2099
            numberPicker.minValue = 2000
            numberPicker.value = numPickerYear
        }
    }


    private fun setMonthsWheelPicker(numberPicker: NumberPicker, adOrBs: Boolean) {

        numberPicker.maxValue = 11
        numberPicker.minValue = 0
        numberPicker.value = dateMonths
        if (adOrBs) {
            numberPicker.displayedValues = monthsInEnglish
        } else {
            numberPicker.displayedValues = monthsInNepali
        }

    }


    interface OnOKCalendarClicked {
        fun onOkItemClicked(year: Int, month: Int, day: Int)
    }
}