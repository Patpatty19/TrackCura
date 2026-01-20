package com.example.androidfinals


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class UserAdapter(private val context: Context, private val users: List<UserModel>) : BaseAdapter() {
    override fun getCount(): Int = users.size
    override fun getItem(position: Int): Any = users[position]
    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.item_user, parent, false)
        val user = users[position]

        view.findViewById<TextView>(R.id.txtUsername).text = "Username: ${user.username}"
        view.findViewById<TextView>(R.id.txtRole).text = "Role: ${user.role}"

        return view
    }
}
