package com.example.sunmadinepal.database.local

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sunmadinepal.databinding.FragmentDateListBinding
import com.example.sunmadinepal.model.DoctorAppointment

class ListAdapter(): RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    private var doctorAppointmentList = emptyList<DoctorAppointment>()

    class MyViewHolder(val view: FragmentDateListBinding): RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(FragmentDateListBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = doctorAppointmentList[position]
        holder.view.dateBox1.text = currentItem.date

      /*  holder.view.apply {
            delete.setOnClickListener {
                itemClick.onDoctorEditClicked(currentItem)
            }

            edit.setOnClickListener {
                itemClick.onDoctorDeleteClicked(currentItem)
            }
        }*/
    }

    override fun getItemCount(): Int {
        return doctorAppointmentList.size
    }

    fun setData(doctorAppointment: List<DoctorAppointment>) {
        this.doctorAppointmentList = doctorAppointment
        notifyDataSetChanged()
    }


}