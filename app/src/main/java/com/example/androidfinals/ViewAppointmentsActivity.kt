package com.example.androidfinals

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import android.util.Log

class ViewAppointmentsActivity : AppCompatActivity() {
    private lateinit var listViewAppointments: ListView
    private lateinit var adapter: AppointmentAdapter
    private var appointmentList: MutableList<AppointmentModel> = mutableListOf()
    private var filteredAppointments: MutableList<AppointmentModel> = mutableListOf()
    private var userRole: String? = null
    private var username: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Set content view before accessing any views
        setContentView(R.layout.activity_view_appointments)

        // Initialize the ListView after setContentView
        listViewAppointments = findViewById(R.id.listViewAppointments)

        // Get the logged-in user role and username from intent
        userRole = intent.getStringExtra("userRole")
        username = intent.getStringExtra("username")

        // Log user details for debugging
        Log.d("ViewAppointmentsActivity", "Received username: $username, userRole: $userRole")

        // Load appointments from file
        appointmentList = FileHelper.readAppointments(this).toMutableList()

        Log.d("ViewAppointments", "Loaded appointments: ${appointmentList.size}")
        appointmentList.forEach {
            Log.d("ViewAppointments", "Appointment: Patient Username = ${it.patientUsername}")
        }

        // Log number of appointments loaded
        Log.d("ViewAppointmentsActivity", "Loaded ${appointmentList.size} appointments from file.")

        // Filter appointments based on user role
        filterAppointments()

        // Check the filtered list before updating the adapter
        Log.d("ViewAppointmentsActivity", "Filtered ${filteredAppointments.size} appointments.")

        // Initialize the adapter and set it to the ListView
        adapter = AppointmentAdapter(this, filteredAppointments)
        listViewAppointments.adapter = adapter


    }



    private fun filterAppointments() {
        filteredAppointments.clear() // Clear any previous filters


        // Filter based on the user role
        when (userRole) {
            "Patient" -> {
                // Filter appointments for the current patient
                val filteredList = appointmentList.filter {
                    Log.d("ViewAppointments", "Checking appointment for ${it.patientUsername} and username: $username")
                    it.patientUsername.equals(username, ignoreCase = true)
                }


                filteredAppointments.addAll(filteredList)
                Log.d("ViewAppointments", "Filtered ${filteredAppointments.size} appointments for username: $username")
            }
            "Nurse", "Doctor", "Admin" -> {
                // Show all appointments for admin, doctor, or nurse
                filteredAppointments.addAll(appointmentList)
            }
        }
    }



    // Optionally, you can refresh the data when the user comes back to the activity
    override fun onResume() {
        super.onResume()
        // Reload appointments from file to ensure updated data is loaded
        appointmentList = FileHelper.readAppointments(this).toMutableList()
        filterAppointments() // Reapply the filter

        // Check the filtered list before updating the adapter
        Log.d("ViewAppointmentsActivity", "Filtered appointments onResume: ${filteredAppointments.size}")

        // Ensure the adapter is reinitialized with the updated filtered list
        adapter = AppointmentAdapter(this, filteredAppointments)
        listViewAppointments.adapter = adapter

        // Notify the adapter of any changes to the list
        adapter.notifyDataSetChanged()
    }
}
