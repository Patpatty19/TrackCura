package com.example.androidfinals

import java.io.Serializable

data class AppointmentModel(
    val patientName: String,
    val doctorName: String,
    val date: String,
    val time: String,
    val patientUsername: String // Change this from 'Any' to 'String'
)
