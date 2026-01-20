package com.example.androidfinals

import android.content.Intent
import android.os.Bundle
import android.widget.AdapterView
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class NurseDashboardActivity : AppCompatActivity() {
    private lateinit var patientListView: ListView
    private lateinit var patientAdapter: NursePatientAdapter
    private lateinit var patients: MutableList<PatientModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nurse_dashboard)

        patientListView = findViewById(R.id.patientListView)
        val btnLogout = findViewById<Button>(R.id.btnLogout)

        // Load patient data from file
        patients = FileHelper.readPatients(this).toMutableList()
        patientAdapter = NursePatientAdapter(this, patients)
        patientListView.adapter = patientAdapter

        // Open limited patient details on item click
        patientListView.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            val intent = Intent(this, NursePatientDetailsActivity::class.java)
            intent.putExtra("patient", patients[position])
            startActivity(intent)
        }

        // Logout function
        btnLogout.setOnClickListener {
            SessionManager.clearUserSession(this)
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}
