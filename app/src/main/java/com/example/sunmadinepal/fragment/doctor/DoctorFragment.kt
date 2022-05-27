package com.example.sunmadinepal.fragment.doctor

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.sunmadinepal.R
import com.example.sunmadinepal.ViewModel.DoctorAppointmentViewModel
import com.example.sunmadinepal.databinding.FragmentProfileBinding
import com.example.sunmadinepal.fragment.doctor.activity.AddAppointmentActivity
import com.example.sunmadinepal.fragment.doctor.activity.EditAppointmentActivity
import com.example.sunmadinepal.fragment.doctor.adapter.AppointmentAdapter
import com.example.sunmadinepal.fragment.doctor.model.AppointmentModel
import com.example.sunmadinepal.fragment.doctor.viewmodel.AddDoctorAppointmentViewModel
import com.example.sunmadinepal.utils.changeStatusBarColor
import com.example.sunmadinepal.utils.changeStatusBarIconTextColor
import com.example.sunmadinepal.utils.openActivity
import com.google.android.material.button.MaterialButton

class DoctorFragment : Fragment(), AppointmentAdapter.OnDoctorAppointmentActionClicked {


    lateinit var binding: FragmentProfileBinding
    private lateinit var doctorAppointmentViewModel: DoctorAppointmentViewModel
    lateinit var appointmentViewModel: AddDoctorAppointmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        doctorAppointmentViewModel = ViewModelProvider(this).get(DoctorAppointmentViewModel::class.java)
        appointmentViewModel = ViewModelProvider(this).get(AddDoctorAppointmentViewModel::class.java)

        binding.addNewAppointmentButton.setOnClickListener {
            requireActivity().openActivity(AddAppointmentActivity::class.java)
        }

        appointmentViewModel.getAllAppointmentData.observe(viewLifecycleOwner, {appointList->
            binding.appointmentRv.adapter = AppointmentAdapter(this).also {
                if (appointList.isNotEmpty()) {
                    binding.dontHaveAppointmentTv.visibility = View.GONE
                    binding.noAppointmentImage.visibility = View.GONE
                    it.submitList(appointList)
                } else {
                    binding.dontHaveAppointmentTv.visibility = View.VISIBLE
                    binding.noAppointmentImage.visibility = View.VISIBLE
                }
            }
        })
    }

    @SuppressLint("InflateParams")
    override fun onDoctorEditClicked(appointmentModel: AppointmentModel) {
        requireActivity().openActivity(EditAppointmentActivity::class.java) {
           putSerializable("appointmentModel",appointmentModel)
        }
    }

    override fun onDoctorDeleteClicked(appointmentModel: AppointmentModel) {

        val view = layoutInflater.inflate(R.layout.delete_appointment_dialog, null)
        val alertDialog = AlertDialog.Builder(requireContext()).create()
        val deleteButton = view.findViewById<MaterialButton>(R.id.delete_button)
        val cancelButton = view.findViewById<MaterialButton>(R.id.cancel_button)

        deleteButton.setOnClickListener {
            alertDialog.dismiss()
            appointmentViewModel.deleteAppointmentViewModel(appointmentModel.id)
        }
        cancelButton.setOnClickListener {
            alertDialog.dismiss()
        }

        alertDialog.setView(view)
        alertDialog.setCanceledOnTouchOutside(false)
        alertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        alertDialog.show()
    }
}


