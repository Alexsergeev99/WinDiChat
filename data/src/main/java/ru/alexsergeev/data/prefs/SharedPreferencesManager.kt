package ru.alexsergeev.data.prefs

import android.content.Context
import android.content.SharedPreferences

class SharedPreferencesManager(context: Context) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)

    private val TOKEN_KEY = "access_token"

    private val sharedPreferencesRefresh: SharedPreferences =
        context.getSharedPreferences("MyPreferencesRefresh", Context.MODE_PRIVATE)

    private val TOKEN_KEY_REFRESH = "refresh_token"

    fun saveToken(token: String) {
        with(sharedPreferences.edit()) {
            putString(TOKEN_KEY, token)
            apply()
        }
    }

    fun saveTokenRefresh(token: String) {
        with(sharedPreferencesRefresh.edit()) {
            putString(TOKEN_KEY_REFRESH, token)
            apply()
        }
    }

    fun getToken(): String? {
        return sharedPreferences.getString(TOKEN_KEY, null)
    }

    fun getTokenRefresh(): String? {
        return sharedPreferencesRefresh.getString(TOKEN_KEY_REFRESH, null)
    }

    fun removeToken() {
        with(sharedPreferences.edit()) {
            remove(TOKEN_KEY)
            apply()
        }
    }
}