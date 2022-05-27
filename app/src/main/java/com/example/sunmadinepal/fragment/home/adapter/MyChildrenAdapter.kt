package com.example.sunmadinepal.fragment.home.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.sunmadinepal.databinding.MyChildrenItemLayoutBinding
import com.example.sunmadinepal.dateconverter.Converter
import com.example.sunmadinepal.fragment.child.model.AddChildModel
import com.example.sunmadinepal.utils.currentDateFormat
import com.example.sunmadinepal.utils.dateDifferenceBetweenTwoDays

class MyChildrenAdapter(private val itemClick : OnHomeChildEditClicked) : ListAdapter<AddChildModel, MyChildrenAdapter.ViewHolder>(
    diffUtil
) {
    private val converter = Converter()

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<AddChildModel>() {
            override fun areItemsTheSame(oldItem: AddChildModel, newItem: AddChildModel): Boolean =
                oldItem == newItem

            override fun areContentsTheSame(oldItem: AddChildModel, newItem: AddChildModel): Boolean =
                oldItem == newItem

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(MyChildrenItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.updateView(currentList[position])
    }

    inner class ViewHolder(private val view: MyChildrenItemLayoutBinding) :
        RecyclerView.ViewHolder(view.root) {
        @SuppressLint("SetTextI18n")
        fun updateView(addChildModel: AddChildModel) {

            view.homeChildNameTv.text = addChildModel.childName
            view.homeChildDateTv.text = addChildModel.birthDate
            view.homeChildGender.text = addChildModel.gender

            view.myChildrenImageView.setImageURI(addChildModel.image)

            val result = addChildModel.birthDate.split("/".toRegex()).toTypedArray()
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
                    view.homeChildDateTv.text = "0 Months"
                }

                dateDifference%2==0 -> {
                    val calculateMonths = dateDifference/30
                    view.homeChildDateTv.text = "$calculateMonths Months"
                }
                else -> {
                    view.homeChildDateTv.text = "$dateDifference Day"
                }
            }

            view.apply {
                homeChildEdit.setOnClickListener {
                    itemClick.onHomeChildEditItemClicked(addChildModel)
                }
            }
        }
    }

    interface OnHomeChildEditClicked{
        fun onHomeChildEditItemClicked(addChildModel: AddChildModel)
    }
}