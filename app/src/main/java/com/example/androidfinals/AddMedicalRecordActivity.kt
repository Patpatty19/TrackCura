package com.example.androidfinals

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class AddMedicalRecordActivity : AppCompatActivity() {
    private lateinit var txtPatientName: TextView
    private lateinit var edtMedicalHistory: EditText
    private lateinit var edtDoctorNotes: EditText
    private lateinit var edtPrescriptions: EditText
    private lateinit var btnSaveRecord: Button
    private lateinit var patientList: MutableList<PatientModel>
    private var selectedPatient: PatientModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_medical_record)

        txtPatientName = findViewById(R.id.txtPatientName)
        edtMedicalHistory = findViewById(R.id.edtMedicalHistory)
        edtDoctorNotes = findViewById(R.id.edtDoctorNotes)
        edtPrescriptions = findViewById(R.id.edtPrescriptions)
        btnSaveRecord = findViewById(R.id.btnSaveRecord)

        // Load patients from file
        patientList = FileHelper.readPatients(this).toMutableList()

        // Get patient username from intent
        val patientUsername = intent.getStringExtra("patientUsername")
        selectedPatient = patientList.find { it.username == patientUsername }

        // Populate fields if the patient exists
        selectedPatient?.let { patient ->
            txtPatientName.text = "Patient: ${patient.name}"
            edtMedicalHistory.setText(patient.medicalHistory)
            edtDoctorNotes.setText(patient.doctorNotes)
            edtPrescriptions.setText(patient.prescriptions)
        }

        // Save record
        btnSaveRecord.setOnClickListener {
            val newMedicalHistory = edtMedicalHistory.text.toString().trim()
            val newDoctorNotes = edtDoctorNotes.text.toString().trim()
            val newPrescriptions = edtPrescriptions.text.toString().trim()

            if (selectedPatient != null) {
                selectedPatient!!.medicalHistory = newMedicalHistory
                selectedPatient!!.doctorNotes = newDoctorNotes
                selectedPatient!!.prescriptions = newPrescriptions

                // Update list and save to file
                val updatedList = patientList.map {
                    if (it.username == selectedPatient!!.username) selectedPatient!! else it
                }
                FileHelper.writePatients(this, updatedList)

                Toast.makeText(this, "Medical record updated successfully!", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "Patient not found!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
