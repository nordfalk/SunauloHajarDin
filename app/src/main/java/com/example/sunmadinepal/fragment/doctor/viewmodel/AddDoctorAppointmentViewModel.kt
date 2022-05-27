package com.example.sunmadinepal.fragment.doctor.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.sunmadinepal.database.local.AppDatabase
import com.example.sunmadinepal.database.local.repository.AddDoctorAppointmentRepository
import com.example.sunmadinepal.fragment.doctor.model.AppointmentModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddDoctorAppointmentViewModel (application: Application): AndroidViewModel(application) {
    val getAllAppointmentData: LiveData<List<AppointmentModel>>
    private val repository: AddDoctorAppointmentRepository


    init {
        val doctorAppointmentDao = AppDatabase.getDatabase(application).addAppointmentDao()
        repository = AddDoctorAppointmentRepository(doctorAppointmentDao)
        getAllAppointmentData = repository.doctorAppointmentList
    }


    fun addDoctorAppointmentViewModel(appointmentModel: AppointmentModel){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addDoctorAppointmentRepo(appointmentModel)
        }
    }

    fun deleteAppointmentViewModel(id : Int){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAppointment(id)
        }
    }
    fun updateAppointmentViewModel(  id : Int, childName: String,
                                    doctorName: String,
                                    hospitalCenter: String,
                                    appointmentDateTime: String){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateAppointmentRepository(id,childName,doctorName,hospitalCenter,appointmentDateTime)
        }
    }
}