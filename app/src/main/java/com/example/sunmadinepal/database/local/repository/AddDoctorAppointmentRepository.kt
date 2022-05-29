package com.example.sunmadinepal.database.local.repository

import androidx.lifecycle.LiveData
import com.example.sunmadinepal.fragment.doctor.dao.AppointmentDao
import com.example.sunmadinepal.fragment.doctor.model.AppointmentModel

class AddDoctorAppointmentRepository(private val appointmentDao: AppointmentDao) {

    val doctorAppointmentList: LiveData<List<AppointmentModel>> = appointmentDao.getAllAppointment()

    suspend fun addDoctorAppointmentRepo(appointmentModel: AppointmentModel) {
        appointmentDao.insertAppointment(appointmentModel)
    }

    suspend fun deleteAppointment(id : Int) {
        appointmentDao.deleteAppointment(id)
    }

    fun updateAppointmentRepository(
        id: Int, childName: String,
        doctorName: String,
        hospitalCenter: String,
        appointmentDateTime: String
    ) {
        appointmentDao.updateAppointment(
            id,
            childName,
            doctorName,
            hospitalCenter,
            appointmentDateTime
        )
    }


}