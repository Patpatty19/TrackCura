package com.example.androidfinals

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class PatientDetailsActivity : AppCompatActivity() {

    private lateinit var txtName: TextView
    private lateinit var txtAge: TextView
    private lateinit var txtGender: TextView
    private lateinit var txtContact: TextView
    private lateinit var editMedicalHistory: EditText
    private lateinit var editPrescriptions: EditText
    private lateinit var btnSave: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_patient_details)

        // Initialize views
        txtName = findViewById(R.id.txtName)
        txtAge = findViewById(R.id.txtAge)
        txtGender = findViewById(R.id.txtGender)
        txtContact = findViewById(R.id.txtContact)
        editMedicalHistory = findViewById(R.id.editMedicalHistory)
        editPrescriptions = findViewById(R.id.editPrescriptions)
        btnSave = findViewById(R.id.btnSave)

        // Get the patient data passed from the previous activity
        val patient = intent.getSerializableExtra("patient") as? PatientModel

        // Check if the patient data is available
        patient?.let {
            // Set patient details in the views
            txtName.text = "Name: ${it.name}"
            txtAge.text = "Age: ${it.age}"
            txtGender.text = "Gender: ${it.gender}"
            txtContact.text = "Contact: ${it.contactInfo}"

            // Set existing values for editable fields
            editMedicalHistory.setText(it.medicalHistory)
            editPrescriptions.setText(it.prescriptions)
        }

        // Save button listener
        btnSave.setOnClickListener {
            // Save the changes (update the PatientModel with the new values)
            patient?.medicalHistory = editMedicalHistory.text.toString()
            patient?.prescriptions = editPrescriptions.text.toString()

            // Here, you can either save the updated patient data to your database
            // or send it back to the previous activity or server if needed.

            // Display a message to confirm that the changes were saved
            Toast.makeText(this, "Changes saved successfully", Toast.LENGTH_SHORT).show()
        }
    }
}
