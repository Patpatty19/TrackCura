package com.example.androidfinals

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class AddUserActivity : AppCompatActivity() {
    private lateinit var edtUsername: EditText
    private lateinit var edtPassword: EditText
    private lateinit var spinnerRole: Spinner
    private lateinit var btnAddUser: Button
    private lateinit var userList: MutableList<UserModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_user)

        edtUsername = findViewById(R.id.edtUsername)
        edtPassword = findViewById(R.id.edtPassword)
        spinnerRole = findViewById(R.id.spinnerRole)
        btnAddUser = findViewById(R.id.btnAddUser)

        // Load existing users
        userList = FileHelper.readUsers(this).toMutableList()

        // Role selection
        val roles = arrayOf("Doctor", "Nurse", "Patient")
        spinnerRole.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, roles)

        // Add user
        btnAddUser.setOnClickListener {
            val username = edtUsername.text.toString().trim()
            val password = edtPassword.text.toString().trim()
            val role = spinnerRole.selectedItem.toString()

            if (username.isNotEmpty() && password.isNotEmpty()) {
                val newUser = UserModel(
                    id = System.currentTimeMillis().toString(), // Unique ID
                    username = username,
                    role = role,
                    email = "default@example.com", // Replace this with a real email field if needed
                    name = "New User", // Replace this with a real name input if needed
                    password = password
                )

                userList.add(newUser)
                FileHelper.writeUsers(this, userList)

                Toast.makeText(this, "User added successfully!", Toast.LENGTH_SHORT).show()
                finish() // Go back to Admin Dashboard
            } else {
                Toast.makeText(this, "Please fill in all fields!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
