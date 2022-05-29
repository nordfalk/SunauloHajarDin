package com.example.sunmadinepal.fragment.doctor.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.sunmadinepal.databinding.AppointmentListItemLayoutBinding
import com.example.sunmadinepal.dateconverter.Converter
import com.example.sunmadinepal.fragment.doctor.model.AppointmentModel
import com.example.sunmadinepal.utils.currentDateFormat
import com.example.sunmadinepal.utils.dateDifferenceBetweenTwoDays

class AppointmentAdapter(private val itemClick: OnDoctorAppointmentActionClicked) :
    ListAdapter<AppointmentModel, AppointmentAdapter.ViewHolder>(
        diffUtil
    ) {
    private val converter = Converter()

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<AppointmentModel>() {
            override fun areItemsTheSame(
                oldItem: AppointmentModel,
                newItem: AppointmentModel
            ): Boolean =
                oldItem == newItem

            override fun areContentsTheSame(
                oldItem: AppointmentModel,
                newItem: AppointmentModel
            ): Boolean =
                oldItem == newItem

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            AppointmentListItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.updateView(currentList[position])
    }

    inner class ViewHolder(private val view: AppointmentListItemLayoutBinding) :
        RecyclerView.ViewHolder(view.root) {
        @SuppressLint("SetTextI18n")
        fun updateView(appointmentModel: AppointmentModel) {

            view.appointmentDateTimeTv.text = appointmentModel.appointmentDate
            view.doctorNameTv.text = appointmentModel.doctorName
            view.hospitalHealthCenter.text = appointmentModel.healthCenterLocation
            view.childName.text = appointmentModel.childName

            view.childGender.text = appointmentModel.childGender

            if (appointmentModel.childDateOfBirth.isNotEmpty()) {
                val result = appointmentModel.childDateOfBirth.split("/".toRegex()).toTypedArray()
                val convertingNepaliToEnglish = converter.getEnglishDate(
                    result[0].toInt(),
                    result[1].toInt(),
                    result[2].toInt()
                )

                val year = convertingNepaliToEnglish.year
                val month = convertingNepaliToEnglish.month
                val day = convertingNepaliToEnglish.date

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

                val fullDate = "${year}/${addingZeroMonth}/${addingZeroDay}"
                val dateDifference = dateDifferenceBetweenTwoDays(fullDate,currentDateFormat())
                Log.d("fullDate", fullDate)
                Log.d("dateDifference", dateDifference.toString())

                when {
                    dateDifference < 0 -> {
                        view.childDob.text = "0 Months"
                    }
                    dateDifference%2==0 -> {
                        val calculateMonths = dateDifference/30
                        view.childDob.text = "$calculateMonths Months"
                    }
                    else -> {
                        view.childDob.text = "$dateDifference Day"
                    }
                }
            }


            view.apply {
                deleteAppointment.setOnClickListener {
                    itemClick.onDoctorDeleteClicked(appointmentModel)
                }

                editAppointment.setOnClickListener {
                    itemClick.onDoctorEditClicked(appointmentModel)
                }
            }
        }
    }

    interface OnDoctorAppointmentActionClicked {
        fun onDoctorEditClicked(appointmentModel: AppointmentModel)
        fun onDoctorDeleteClicked(appointmentModel: AppointmentModel)
    }


}