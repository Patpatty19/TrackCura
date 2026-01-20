package com.example.androidfinals


import android.content.Context
import com.google.gson.Gson

object SessionManager {
    private const val PREF_NAME = "UserSession"
    private const val KEY_USER = "logged_in_user"

    fun saveUserSession(context: Context, user: UserModel) {
        val sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val userJson = Gson().toJson(user)

        println("Debug: Saving session -> $userJson")  // Debugging line
        editor.putString(KEY_USER, userJson)
        editor.apply()
    }


    fun getUserSession(context: Context): UserModel? {
        val sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val userJson = sharedPreferences.getString(KEY_USER, null)
        println("Session Data: $userJson")
        return if (userJson != null) Gson().fromJson(userJson, UserModel::class.java) else null
    }

    fun clearUserSession(context: Context) {
        val sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.remove(KEY_USER)
        editor.apply()
    }

    fun getLoggedInUser(context: Context): String? {
        val user = getUserSession(context)
        return user?.name  // Assuming UserModel has a 'name' field
    }

}
