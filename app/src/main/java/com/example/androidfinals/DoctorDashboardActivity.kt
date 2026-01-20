package com.example.androidfinals

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class DoctorDashboardActivity : AppCompatActivity() {

    private lateinit var patientRecyclerView: RecyclerView

    private val testPatients = listOf(
        PatientModel(
            username = "john_doe_1",
            name = "John Doe",
            age = "30",
            contact = "09123456789",
            medicalHistory = "No known allergies. History of hypertension.",
            doctorNotes = "Regular check-up needed. Prescribed vitamins.",
            prescriptions = "Vitamin C, Paracetamol",
            contactInfo = "09123456789",
            gender = "Male"
        ),
        PatientModel(
            username = "jane_smith_2",
            name = "Jane Smith",
            age = "25",
            contact = "09876543210",
            medicalHistory = "Asthma since childhood.",
            doctorNotes = "Advised to avoid allergens. Continue medication.",
            prescriptions = "Salbutamol, Antihistamine",
            contactInfo = "09876543210",
            gender = "Female"
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_doctor_dashboard)

        val btnLogout = findViewById<Button>(R.id.btnLogout)
        patientRecyclerView = findViewById(R.id.patientListView) // ✅ Make sure the ID matches your XML

        // ✅ Setup RecyclerView
        patientRecyclerView.layoutManager = LinearLayoutManager(this)
        val patientAdapter = PatientRecyclerAdapter(this, testPatients)
        patientRecyclerView.adapter = patientAdapter

        btnLogout.setOnClickListener {
            SessionManager.clearUserSession(this)
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}
