package com.example.sunmadinepal.test

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sunmadinepal.R
import com.example.sunmadinepal.dateconverter.Converter
import com.example.sunmadinepal.fragment.child.model.AddChildModel
import com.example.sunmadinepal.utils.currentDateFormat
import com.example.sunmadinepal.utils.dateDifferenceBetweenTwoDays
import com.google.android.material.card.MaterialCardView

class TestAdapter(
    private val childList: MutableList<Any>,
    private val itemClick: OnHomeChildClicked
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val converter = Converter()

    companion object {
        const val CHILD_LIST: Int = 0
        const val EMPTY: Int = 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        Log.d("viewType ", viewType.toString())
        return when (viewType) {
            CHILD_LIST -> ChildViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.my_children_item_layout,
                    parent,
                    false
                )
            )
            EMPTY -> EmptyViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.add_child_layout,
                    parent,
                    false
                )
            )
            else -> {
                ChildViewHolder(
                    LayoutInflater.from(parent.context).inflate(
                        R.layout.my_children_item_layout,
                        parent,
                        false
                    )
                )
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when {
            childList[position] is AddChildModel -> CHILD_LIST
            else -> EMPTY
        }
    }

    override fun getItemCount(): Int {
        return childList.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val menu = childList[position]

        if (holder is ChildViewHolder) {
            val addChild = menu as AddChildModel

            holder.homeChildNameTv.text = addChild.childName
            holder.homeChildDateTv.text = addChild.birthDate
            holder.homeChildGender.text = addChild.gender

            holder.myChildrenImageView.setImageURI(addChild.image)

            val result = addChild.birthDate.split("/".toRegex()).toTypedArray()
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
            val dateDifference = dateDifferenceBetweenTwoDays(fullDate, currentDateFormat())
            Log.d("fullDate", fullDate)
            Log.d("dateDifference", dateDifference.toString())

            when {
                dateDifference < 0 -> {
                    holder.homeChildDateTv.text = "0 Months"
                }

                dateDifference % 2 == 0 -> {
                    val calculateMonths = dateDifference / 30
                    holder.homeChildDateTv.text = "$calculateMonths Months"
                }
                else -> {
                    holder.homeChildDateTv.text = "$dateDifference Day"
                }
            }

            holder.homeChildEdit.setOnClickListener {
                itemClick.onHomeChildEditItemClicked(menu)
            }
        }

        if (holder is EmptyViewHolder) {

            holder.addChildCardView.setOnClickListener {
                itemClick.onAddChildItemClicked()
            }
        }
    }

    class ChildViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val homeChildNameTv: TextView = view.findViewById(R.id.home_child_name_tv)
        val homeChildDateTv: TextView = view.findViewById(R.id.home_child_date_tv)
        val homeChildGender: TextView = view.findViewById(R.id.home_child_gender)
        val homeChildEdit: ImageView = view.findViewById(R.id.home_child_edit)
        val myChildrenImageView: ImageView = view.findViewById(R.id.my_children_image_view)
    }


    class EmptyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val addChildCardView: MaterialCardView = view.findViewById(R.id.add_child_card_view)
    }

    interface OnHomeChildClicked {
        fun onHomeChildEditItemClicked(addChildModel: Any)
        fun onAddChildItemClicked()
    }

}