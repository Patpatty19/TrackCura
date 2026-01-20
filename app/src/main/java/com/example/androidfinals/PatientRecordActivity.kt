package com.example.androidfinals


import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class PatientRecordActivity : AppCompatActivity() {
    private lateinit var txtPatientName: TextView
    private lateinit var txtPatientAge: TextView
    private lateinit var txtContactInfo: TextView
    private lateinit var txtMedicalHistory: TextView
    private lateinit var txtDoctorNotes: TextView
    private lateinit var txtPrescriptions: TextView
    private lateinit var patientList: List<PatientModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_patient_records)

        txtPatientName = findViewById(R.id.txtPatientName)
        txtPatientAge = findViewById(R.id.txtPatientAge)
        txtContactInfo = findViewById(R.id.txtContactInfo)
        txtMedicalHistory = findViewById(R.id.txtMedicalHistory)
        txtDoctorNotes = findViewById(R.id.txtDoctorNotes)
        txtPrescriptions = findViewById(R.id.txtPrescriptions)

        // Load patients from file
        patientList = FileHelper.readPatients(this)

        // Get patient username from intent
        val patientUsername = intent.getStringExtra("patientUsername")
        val selectedPatient = patientList.find { it.username == patientUsername }

        // Display patient details
        selectedPatient?.let { patient ->
            txtPatientName.text = "Name: ${patient.name}"
            txtPatientAge.text = "Age: ${patient.age}"
            txtContactInfo.text = "Contact: ${patient.contact}"
            txtMedicalHistory.text = "Medical History: ${patient.medicalHistory}"
            txtDoctorNotes.text = "Doctor's Notes: ${patient.doctorNotes}"
            txtPrescriptions.text = "Prescriptions: ${patient.prescriptions}"
        } ?: run {
            Toast.makeText(this, "Patient record not found!", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}
