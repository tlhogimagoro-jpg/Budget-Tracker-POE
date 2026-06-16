package com.budgettracker.utils

import android.content.Context
import android.content.SharedPreferences
import android.util.Log

/**
 * SessionManager — handles user session persistence.
 * Uses applicationContext to prevent memory leaks and ensure consistency.
 */
class SessionManager(context: Context) {

    private val TAG = "SessionManager"
    private val prefs: SharedPreferences =
        context.applicationContext.getSharedPreferences("budget_tracker_session", Context.MODE_PRIVATE)

    companion object {
        private const val KEY_IS_LOGGED_IN = "is_logged_in"
        private const val KEY_USER_ID      = "user_id"
        private const val KEY_USERNAME     = "username"
    }

    /**
     * Save session. Using commit() instead of apply() to ensure the write
     * is finished before we navigate to the next screen.
     */
    fun saveSession(userId: Int, username: String) {
        Log.d(TAG, "Saving session: $username (id=$userId)")
        val success = prefs.edit()
            .putBoolean(KEY_IS_LOGGED_IN, true)
            .putInt(KEY_USER_ID, userId)
            .putString(KEY_USERNAME, username)
            .commit()
        Log.d(TAG, "Session save success: $success")
    }

    fun clearSession() {
        Log.d(TAG, "Clearing session")
        prefs.edit().clear().commit()
    }

    fun isLoggedIn(): Boolean = prefs.getBoolean(KEY_IS_LOGGED_IN, false)

    /**
     * Get the logged-in user's ID. 
     * Returns -1 if no session is active.
     */
    fun getUserId(): Int {
        val id = prefs.getInt(KEY_USER_ID, -1)
        if (id == -1) Log.w(TAG, "getUserId returned -1. No active session.")
        return id
    }

    fun getUsername(): String = prefs.getString(KEY_USERNAME, "") ?: ""
}
