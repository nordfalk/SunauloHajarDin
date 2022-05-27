package com.example.sunmadinepal.fragment.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.sunmadinepal.databinding.DailyHealthTipsItemLayoutBinding
import com.example.sunmadinepal.fragment.home.model.DailyHealthTipsModel

class DailyHealthTipsAdapter : ListAdapter<DailyHealthTipsModel, DailyHealthTipsAdapter.ViewHolder>(
    diffUtil
) {

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<DailyHealthTipsModel>() {
            override fun areItemsTheSame(
                oldItem: DailyHealthTipsModel,
                newItem: DailyHealthTipsModel
            ): Boolean =
                oldItem == newItem

            override fun areContentsTheSame(
                oldItem: DailyHealthTipsModel,
                newItem: DailyHealthTipsModel
            ): Boolean =
                oldItem == newItem

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            DailyHealthTipsItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.updateView(currentList[position])
    }

    inner class ViewHolder(private val view: DailyHealthTipsItemLayoutBinding) :
        RecyclerView.ViewHolder(view.root) {
        fun updateView(dailyHealthTipsModel: DailyHealthTipsModel) {

            view.healthyTipsTitle.text = dailyHealthTipsModel.title
            view.healthyTipsDescription.text = dailyHealthTipsModel.description

        }
    }
}