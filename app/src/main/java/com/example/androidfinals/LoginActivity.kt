package com.example.androidfinals


import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val usernameField = findViewById<EditText>(R.id.username)
        val passwordField = findViewById<EditText>(R.id.password)
        val loginButton = findViewById<Button>(R.id.btnLogin)

        loginButton.setOnClickListener {
            val username = usernameField.text.toString().trim()
            val password = passwordField.text.toString().trim()

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please enter username and password", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val user = authenticateUser(username, password)
            if (user != null) {
                Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()

                // Save user session
                SessionManager.saveUserSession(this, user)

                // Redirect to the appropriate dashboard based on role
                val intent = when (user.role) {
                    "Admin" -> Intent(this, AdminDashboardActivity::class.java)
                    "Doctor" -> Intent(this, DoctorDashboardActivity::class.java)
                    "Nurse" -> Intent(this, NurseDashboardActivity::class.java)
                    "Patient" -> Intent(this, PatientDashboardActivity::class.java)
                    else -> null
                }
                intent?.let {
                    startActivity(it)
                    finish()
                }
            } else {
                Toast.makeText(this, "Invalid Credentials", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun authenticateUser(username: String, password: String): UserModel? {
        val users = FileHelper.readUsers(this)

        println("Debug: Checking credentials for username: $username and password: $password")
        println("Debug: User list loaded -> ${users.size} users")

        for (user in users) {
            println("Debug: Checking user -> Username: ${user.username}, Password: ${user.password}")
            if (user.username == username && user.password == password) {
                println("Debug: Match found! Returning user -> $user")
                return user  // ✅ Add this log
            }
        }

        println("Debug: No matching user found.")
        return null
    }


}
