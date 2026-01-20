package com.example.androidfinals

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val testPatients = listOf(
            PatientModel(
                username = "john_doe_1", // Add a username here
                name = "John Doe",
                age = "30",
                contact = "09123456789", // Add a contact here
                medicalHistory = "No known allergies. History of hypertension.",
                doctorNotes = "Regular check-up needed. Prescribed vitamins.",
                prescriptions = "Vitamin C, Paracetamol",
                contactInfo = "09123456789",
                gender = "Male"
            ),
            PatientModel(
                username = "jane_smith_2", // Add a username here
                name = "Jane Smith",
                age = "25",
                contact = "09876543210", // Add a contact here
                medicalHistory = "Asthma since childhood.",
                doctorNotes = "Advised to avoid allergens. Continue medication.",
                prescriptions = "Salbutamol, Antihistamine",
                contactInfo = "09876543210",
                gender = "Female"
            )
        )


// Save test patients
        FileHelper.writePatients(this, testPatients)
        println("Debug: Test Patients Saved -> ${testPatients.size}")

// Save test patients
        FileHelper.writePatients(this, testPatients)
        println("Debug: Test Patients Saved -> ${testPatients.size}")


        val imgLogo: ImageView = findViewById(R.id.imgLogo)
        val tvWelcome: TextView = findViewById(R.id.tvWelcome)
        val btnLogin: Button = findViewById(R.id.btnLogin)

        // Fade-in animation
        fun fadeIn(view: View, delay: Long) {
            view.alpha = 0f
            view.animate().alpha(1f).setDuration(1000).setStartDelay(delay).start()
        }

        // Apply animations
        fadeIn(imgLogo, 500)
        fadeIn(tvWelcome, 1000)
        fadeIn(btnLogin, 1500)

        // Move to LoginActivity on button click
        btnLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }
}
