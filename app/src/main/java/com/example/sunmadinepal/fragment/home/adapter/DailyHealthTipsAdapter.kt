package com.example.sunmadinepal.fragment.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.sunmadinepal.R
import com.example.sunmadinepal.databinding.DailyHealthTipsItemLayoutBinding
import com.example.sunmadinepal.fragment.home.model.DailyHealthTipsModel

class DailyHealthTipsAdapter(private val readMoreClickListener: ReadMoreClickListener) :
    ListAdapter<DailyHealthTipsModel, DailyHealthTipsAdapter.ViewHolder>(
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
            Glide.with(view.root.context).load(dailyHealthTipsModel.imageDrawable).apply(
                RequestOptions().error(R.drawable.ic_launcher_background)
            ).into(view.imageView)
            view.healthyTipsDescription.text = dailyHealthTipsModel.description
            view.dailyHealthTipsCard.setOnClickListener {
                readMoreClickListener.onReadMoreClicked(
                    dailyHealthTipsModel,
                    bindingAdapterPosition
                )
            }
        }
    }

    interface ReadMoreClickListener {
        fun onReadMoreClicked(dailyHealthTipsModel: DailyHealthTipsModel, position: Int)
    }
}