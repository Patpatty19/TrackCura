package com.example.androidfinals

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageButton
import android.widget.TextView

class AppointmentAdapter(private val context: Context, private val appointments: List<AppointmentModel>) : BaseAdapter() {

    override fun getCount(): Int = appointments.size

    override fun getItem(position: Int): Any = appointments[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.item_appointment, parent, false)
        val appointment = appointments[position]

        // Bind data to the layout views
        view.findViewById<TextView>(R.id.tvDoctorName).text = "Doctor: ${appointment.doctorName}"
        view.findViewById<TextView>(R.id.tvAppointmentDate).text = "Date: ${appointment.date}"
        view.findViewById<TextView>(R.id.tvAppointmentTime).text = "Time: ${appointment.time}"

        // Handle cancel button click (if needed)
        val cancelButton = view.findViewById<ImageButton>(R.id.btnCancelAppointment)
        cancelButton.setOnClickListener {
            // Handle cancel appointment logic here
        }

        return view
    }
}


