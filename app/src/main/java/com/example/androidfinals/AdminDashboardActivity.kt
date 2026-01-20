package com.example.androidfinals


import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class AdminDashboardActivity : AppCompatActivity() {
    private lateinit var listViewUsers: ListView
    private lateinit var btnAddUser: Button
    private lateinit var btnLogout: Button
    private lateinit var userList: MutableList<UserModel>
    private lateinit var adapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_dashboard)

        listViewUsers = findViewById(R.id.listViewUsers)
        btnAddUser = findViewById(R.id.btnAddUser)
        btnLogout = findViewById(R.id.btnLogout)

        // Load users from file
        userList = FileHelper.readUsers(this).toMutableList()
        adapter = UserAdapter(this, userList)
        listViewUsers.adapter = adapter

        // Add new user
        btnAddUser.setOnClickListener {
            startActivity(Intent(this, AddUserActivity::class.java))
        }

        // Logout function
        btnLogout.setOnClickListener {
            SessionManager.clearUserSession(this)
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

        // Handle user selection for edit or delete
        listViewUsers.setOnItemClickListener { _, _, position, _ ->
            val selectedUser = userList[position]
            val options = arrayOf("Edit", "Delete")
            AlertDialog.Builder(this)
                .setTitle("Manage User")
                .setItems(options) { _, which ->
                    when (which) {
                        0 -> editUser(selectedUser)  // Edit user
                        1 -> deleteUser(selectedUser) // Delete user
                    }
                }
                .show()
        }
    }

    private fun editUser(user: UserModel) {
        val intent = Intent(this, EditUserActivity::class.java)
        intent.putExtra("username", user.username)
        startActivity(intent)
    }

    private fun deleteUser(user: UserModel) {
        userList.remove(user)
        FileHelper.writeUsers(this, userList)
        adapter.notifyDataSetChanged()
        Toast.makeText(this, "User deleted successfully!", Toast.LENGTH_SHORT).show()
    }

    override fun onResume() {
        super.onResume()
        // Refresh user list after adding/editing
        userList = FileHelper.readUsers(this).toMutableList()
        adapter = UserAdapter(this, userList)
        listViewUsers.adapter = adapter
    }
}
