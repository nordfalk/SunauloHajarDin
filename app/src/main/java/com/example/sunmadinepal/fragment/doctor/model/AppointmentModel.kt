package com.example.sunmadinepal.fragment.doctor.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "appointment")
data class AppointmentModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int,

    @ColumnInfo(name = "child_name")
    var childName: String,

    @ColumnInfo(name = "doctor_name")
    val doctorName: String,

    @ColumnInfo(name = "health_center_location")
    val healthCenterLocation: String,

    @ColumnInfo(name = "appointment_date")
    val appointmentDate: String,

    @ColumnInfo(name = "child_dob")
    val childDateOfBirth: String,
    @ColumnInfo(name = "child_gender")
    val childGender: String
): Serializable {

    constructor(
        childName: String,
        doctorName: String,
        healthCenterLocation: String,
        appointmentDate: String,
        childDateOfBirth :String,
        childGender :String

    ) : this(
        0, childName,
        doctorName,
        healthCenterLocation,
        appointmentDate,
        childDateOfBirth,
        childGender

    )
}