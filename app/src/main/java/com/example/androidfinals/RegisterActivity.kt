package com.example.androidfinals

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.androidfinals.R


class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val usernameField = findViewById<EditText>(R.id.regUsername)
        val passwordField = findViewById<EditText>(R.id.regPassword)
        val roleSpinner = findViewById<Spinner>(R.id.regRole)
        val registerButton = findViewById<Button>(R.id.btnRegister)

        // Populate role dropdown
        val roles = arrayOf("Admin", "Doctor", "Nurse", "Patient")
        roleSpinner.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, roles)

        registerButton.setOnClickListener {
            val username = usernameField.text.toString().trim()
            val password = passwordField.text.toString().trim()
            val role = roleSpinner.selectedItem.toString()

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Check if user already exists
            val users = FileHelper.readUsers(this)
            if (users.any { it.username == username }) {
                Toast.makeText(this, "Username already exists!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Save new user
            val newUser = UserModel(
                username, password, role,
                email = TODO(),
                name = TODO(),
                password = TODO()
            )
            FileHelper.saveUser(this, newUser)

            Toast.makeText(this, "Registration Successful!", Toast.LENGTH_SHORT).show()
            finish() // Go back to login screen
        }
    }
}
