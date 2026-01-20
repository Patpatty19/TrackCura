package com.example.androidfinals

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import java.util.*

class AppointmentBookingActivity : AppCompatActivity() {
    private lateinit var spinnerDoctor: Spinner
    private lateinit var btnPickDate: Button
    private lateinit var btnPickTime: Button
    private lateinit var btnBookAppointment: Button
    private lateinit var txtSelectedDate: TextView
    private lateinit var txtSelectedTime: TextView
    private lateinit var listViewAppointments: ListView

    private var selectedDate: String = ""
    private var selectedTime: String = ""
    private lateinit var appointmentList: MutableList<AppointmentModel>
    private lateinit var adapter: AppointmentAdapter
    private lateinit var patientName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_appointment_booking)

        spinnerDoctor = findViewById(R.id.spinnerDoctor)
        btnPickDate = findViewById(R.id.btnPickDate)
        btnPickTime = findViewById(R.id.btnPickTime)
        btnBookAppointment = findViewById(R.id.btnBookAppointment)
        txtSelectedDate = findViewById(R.id.txtSelectedDate)
        txtSelectedTime = findViewById(R.id.txtSelectedTime)
        listViewAppointments = findViewById(R.id.listViewAppointments)

        // Get logged-in patient
        patientName = SessionManager.getLoggedInUser(this).toString()

        // Load appointments from file
        appointmentList = FileHelper.readAppointments(this).toMutableList()
        adapter = AppointmentAdapter(this, appointmentList)
        listViewAppointments.adapter = adapter

        // Populate doctor spinner
        val doctorNames = FileHelper.readUsers(this)
            .filter { it.role == "Doctor" }
            .map { it.username }
        spinnerDoctor.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, doctorNames)

        // Date picker
        btnPickDate.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            DatePickerDialog(this, { _, selectedYear, selectedMonth, selectedDay ->
                selectedDate = "$selectedDay/${selectedMonth + 1}/$selectedYear"
                txtSelectedDate.text = "Selected Date: $selectedDate"
            }, year, month, day).show()
        }

        // Time picker
        btnPickTime.setOnClickListener {
            val calendar = Calendar.getInstance()
            val hour = calendar.get(Calendar.HOUR_OF_DAY)
            val minute = calendar.get(Calendar.MINUTE)

            TimePickerDialog(this, { _, selectedHour, selectedMinute ->
                selectedTime = "$selectedHour:$selectedMinute"
                txtSelectedTime.text = "Selected Time: $selectedTime"
            }, hour, minute, false).show()
        }

        // Book appointment
        btnBookAppointment.setOnClickListener {
            if (selectedDate.isNotEmpty() && selectedTime.isNotEmpty() && spinnerDoctor.selectedItem != null) {
                val newAppointment = AppointmentModel(
                    patientName,
                    spinnerDoctor.selectedItem.toString(),
                    selectedDate,
                    selectedTime,
                    patientUsername = patientName
                )
                appointmentList.add(newAppointment)  // Add the new appointment to the list
                FileHelper.writeAppointments(this, appointmentList)  // Save the list to file

                // Show a success message
                Toast.makeText(this, "Appointment booked successfully!", Toast.LENGTH_SHORT).show()

                // Navigate to the ViewAppointmentsActivity with updated list
                val intent = Intent(this, ViewAppointmentsActivity::class.java)
                intent.putExtra("userRole", "Patient")  // Pass user role (or any other required data)
                intent.putExtra("username", patientName)  // Pass the patient's username
                startActivity(intent)  // Start the activity to view appointments
            } else {
                Toast.makeText(this, "Please select a doctor, date, and time", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
