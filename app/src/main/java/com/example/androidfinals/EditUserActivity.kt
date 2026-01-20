package com.example.androidfinals

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class EditUserActivity : AppCompatActivity() {
    private lateinit var edtUsername: EditText
    private lateinit var edtPassword: EditText
    private lateinit var spinnerRole: Spinner
    private lateinit var btnSaveChanges: Button
    private lateinit var userList: MutableList<UserModel>
    private var selectedUser: UserModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_user)

        edtUsername = findViewById(R.id.edtUsername)
        edtPassword = findViewById(R.id.edtPassword)
        spinnerRole = findViewById(R.id.spinnerRole)
        btnSaveChanges = findViewById(R.id.btnSaveChanges)

        // Load users from file
        userList = FileHelper.readUsers(this).toMutableList()

        // Get username passed from Admin Dashboard
        val username = intent.getStringExtra("username")
        selectedUser = userList.find { it.username == username }

        // Populate fields with existing data
        selectedUser?.let {
            edtUsername.setText(it.username)
            edtPassword.setText(it.password.toString())


            val roles = arrayOf("Doctor", "Nurse", "Patient")
            spinnerRole.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, roles)
            spinnerRole.setSelection(roles.indexOf(it.role))
        }

        // Save changes
        btnSaveChanges.setOnClickListener {
            val newUsername = edtUsername.text.toString().trim()
            val newPassword = edtPassword.text.toString().trim()
            val newRole = spinnerRole.selectedItem.toString()

            if (newUsername.isNotEmpty() && newPassword.isNotEmpty()) {
                selectedUser?.let { user ->
                    user.username = newUsername
                    user.password = newPassword
                    user.role = newRole

                    FileHelper.writeUsers(this, userList)

                    Toast.makeText(this, "User updated successfully!", Toast.LENGTH_SHORT).show()
                    finish() // Go back to Admin Dashboard
                }
            } else {
                Toast.makeText(this, "Please fill in all fields!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
