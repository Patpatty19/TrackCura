package com.example.androidfinals

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PatientRecyclerAdapter(
    private val context: Context,
    private val patientList: List<PatientModel>
) : RecyclerView.Adapter<PatientRecyclerAdapter.PatientViewHolder>() {

    inner class PatientViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtPatientName: TextView = view.findViewById(R.id.tvPatientName)
        val txtPatientCondition: TextView = view.findViewById(R.id.txtPatientCondition)
        val btnViewDetails: ImageButton = view.findViewById(R.id.btnViewDetails)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PatientViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_patient, parent, false)
        return PatientViewHolder(view)
    }

    override fun onBindViewHolder(holder: PatientViewHolder, position: Int) {
        val patient = patientList[position]
        holder.txtPatientName.text = patient.name
        holder.txtPatientCondition.text = "Doctor Notes: ${patient.doctorNotes}"

        holder.btnViewDetails.setOnClickListener {
            val intent = Intent(context, PatientDetailsActivity::class.java).apply {
                putExtra("patient", patient)
            }
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = patientList.size
}
