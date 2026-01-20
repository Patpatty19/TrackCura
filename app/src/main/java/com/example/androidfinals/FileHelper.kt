package com.example.androidfinals

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File

object FileHelper {
    private const val PATIENTS_FILE = "patients.json"
    private const val USERS_FILE = "users.json"
    private const val APPOINTMENTS_FILE = "appointments.json"



    fun readPatients(context: Context): List<PatientModel> {
        val file = File(context.filesDir, PATIENTS_FILE)
        if (!file.exists()) return emptyList()

        val json = file.readText()
        println("Debug: Raw Patient Data -> $json")
        return Gson().fromJson(json, object : TypeToken<List<PatientModel>>() {}.type)
    }

    fun writePatients(context: Context, patients: List<PatientModel>) {
        val file = File(context.filesDir, PATIENTS_FILE)
        file.writeText(Gson().toJson(patients))
    }

    fun readUsers(context: Context): List<UserModel> {
        val file = File(context.filesDir, USERS_FILE)

        // Check if file exists
        if (!file.exists()) {
            println("Debug: users.json file does not exist!")
            return emptyList()
        }

        // Read data
        val json = file.readText()
        println("Debug: Raw JSON data -> $json")

        return try {
            Gson().fromJson(json, object : TypeToken<List<UserModel>>() {}.type) ?: emptyList()
        } catch (e: Exception) {
            println("Debug: Error parsing users.json -> ${e.message}")
            emptyList()
        }
    }

    fun writeUsers(context: Context, users: List<UserModel>) {
        val file = File(context.filesDir, USERS_FILE)
        val json = Gson().toJson(users)
        file.writeText(json)

        println("Debug: Users saved -> $json")
    }


    fun readAppointments(context: Context): List<AppointmentModel> {
        val file = File(context.filesDir, APPOINTMENTS_FILE)
        if (!file.exists()) return emptyList()

        val json = file.readText()
        return Gson().fromJson(json, object : TypeToken<List<AppointmentModel>>() {}.type)
    }

    fun writeAppointments(context: Context, appointments: List<AppointmentModel>) {
        val file = File(context.filesDir, APPOINTMENTS_FILE)
        file.writeText(Gson().toJson(appointments))
    }

    fun saveUser(registerActivity: RegisterActivity, newUser: UserModel) {

    }
}
