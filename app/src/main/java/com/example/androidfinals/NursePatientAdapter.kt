package com.example.androidfinals


import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageButton
import android.widget.TextView

class NursePatientAdapter(private val context: Context, private val patientList: List<PatientModel>) : BaseAdapter() {
    override fun getCount(): Int = patientList.size
    override fun getItem(position: Int): Any = patientList[position]
    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.item_patient, parent, false)
        val patient = patientList[position]

        val btnViewDetails = view.findViewById<ImageButton>(R.id.btnViewDetails)

        // Correct IDs from XML
        val txtName = view.findViewById<TextView>(R.id.tvPatientName) // Corrected ID
        val txtCondition = view.findViewById<TextView>(R.id.txtPatientCondition)

        if (txtName == null || txtCondition == null) {
            Log.e("NursePatientAdapter", "One or more views are null! Check item_patient.xml")
        }

        txtName?.text = patient.name
        txtCondition?.text = "Doctor Notes: ${patient.doctorNotes}" // Ensure it exists in PatientModel

        btnViewDetails.setOnClickListener {
            val intent = Intent(context, NursePatientDetailsActivity::class.java).apply {
                putExtra("patient", patient)
            }
            context.startActivity(intent)
        }

        return view
    }

}
