package com.example.sunmadinepal.fragment.doctor.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.sunmadinepal.fragment.doctor.model.AppointmentModel

@Dao
interface AppointmentDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAppointment(appointmentModel: AppointmentModel)

    @Query("SELECT * FROM appointment")
    fun getAllAppointment(): LiveData<List<AppointmentModel>>

    @Query("UPDATE appointment SET child_name = :childName , doctor_name = :doctorName, health_center_location = :hospitalCenter, appointment_date = :appointmentDateTime WHERE id =:id")
    fun updateAppointment(
       id : Int,
        childName: String,
        doctorName: String,
        hospitalCenter: String,
        appointmentDateTime: String
    )

    @Query("DELETE FROM appointment WHERE id=:id")
    fun deleteAppointment(id : Int)
}