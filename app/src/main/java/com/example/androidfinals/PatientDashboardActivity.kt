package com.example.androidfinals


import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class PatientDashboardActivity : AppCompatActivity() {
    private lateinit var txtName: TextView
    private lateinit var txtAge: TextView
    private lateinit var txtGender: TextView
    private lateinit var txtContact: TextView
    private lateinit var txtMedicalHistory: TextView
    private lateinit var txtPrescriptions: TextView
    private lateinit var btnLogout: Button

    private lateinit var patient: PatientModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_patient_dashboard)

        // Buttons

        val btnLogout = findViewById<Button>(R.id.btnLogout)
        val btnBookAppointments = findViewById<Button>(R.id.btnBookAppointment)


        // Book Appointments Button
        btnBookAppointments.setOnClickListener {
            SessionManager.clearUserSession(this)
            startActivity(Intent(this, AppointmentBookingActivity::class.java))
            finish()
        }

        // Logout Button
        btnLogout.setOnClickListener {
            SessionManager.clearUserSession(this)
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

        // Initialize views
        txtName = findViewById(R.id.txtName)
        txtAge = findViewById(R.id.txtAge)
        txtGender = findViewById(R.id.txtGender)
        txtContact = findViewById(R.id.txtContact)
        txtMedicalHistory = findViewById(R.id.txtMedicalHistory)
        txtPrescriptions = findViewById(R.id.txtPrescriptions)

        // Get patient info from session
        val username = SessionManager.getLoggedInUser(this)
        val patientList = FileHelper.readPatients(this)

        patient = patientList.find { it.name == username } ?: return

        // Display patient details
        txtName.text = patient.name
        txtAge.text = "Age: ${patient.age}"
        txtGender.text = "Gender: ${patient.gender}"
        txtContact.text = "Contact: ${patient.contactInfo}"
        txtMedicalHistory.text = "Medical History: ${patient.medicalHistory}"
        txtPrescriptions.text = "Prescriptions: ${patient.prescriptions}"
    }
}

