package com.example.androidfinals
import java.io.Serializable


data class PatientModel(
    val username: String,
    val name: String,
    val age: String,
    val contact: String,
    var medicalHistory: String,
    var doctorNotes: String,
    var prescriptions: String,
    val contactInfo: String,
    val gender: String
) : Serializable


