package com.example.androidfinals

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class NursePatientDetailsActivity : AppCompatActivity() {
    private lateinit var txtName: TextView
    private lateinit var txtAge: TextView
    private lateinit var txtGender: TextView
    private lateinit var txtContact: TextView
    private lateinit var txtMedicalHistory: TextView

    private lateinit var patient: PatientModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nurse_patient_details)

        // Initialize views
        txtName = findViewById(R.id.txtName)
        txtAge = findViewById(R.id.txtAge)
        txtGender = findViewById(R.id.txtGender)
        txtContact = findViewById(R.id.txtContact)
        txtMedicalHistory = findViewById(R.id.txtMedicalHistory)

        // Get patient object from intent
        patient = intent.getSerializableExtra("patient") as PatientModel

        // Display patient details (limited access)
        txtName.text = patient.name
        txtAge.text = "Age: ${patient.age}"
        txtGender.text = "Gender: ${patient.gender}"
        txtContact.text = "Contact: ${patient.contactInfo}"
        txtMedicalHistory.text = "Medical History: ${patient.medicalHistory}"
    }
}
