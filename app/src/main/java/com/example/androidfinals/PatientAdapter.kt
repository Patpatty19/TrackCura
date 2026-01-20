package com.example.androidfinals

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageButton
import android.widget.TextView

class PatientAdapter(private val context: Context, private val patientList: List<PatientModel>) : BaseAdapter() {
    override fun getCount(): Int = patientList.size
    override fun getItem(position: Int): Any = patientList[position]
    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.item_patient, parent, false)
        val patient = patientList[position]
        val btnViewDetails = view.findViewById<ImageButton>(R.id.btnViewDetails)

        println("Debug: Binding Patient -> ${patient.name}")
        view.findViewById<TextView>(R.id.txtPatientName).text = patient.name
        view.findViewById<TextView>(R.id.txtPatientAge).text = "Age: ${patient.age}"
        view.findViewById<TextView>(R.id.txtPatientCondition).text = "Doctor Notes: ${patient.doctorNotes}"

        // Set OnClickListener for the ImageButton to open PatientDetailsActivity
        btnViewDetails.setOnClickListener {
            // Pass the patient data to the PatientDetailsActivity
            val intent = Intent(context, PatientDetailsActivity::class.java).apply {
                putExtra("patient", patient) // Pass the PatientModel object
            }
            context.startActivity(intent) // Start the PatientDetailsActivity
        }


        return view
    }
}
