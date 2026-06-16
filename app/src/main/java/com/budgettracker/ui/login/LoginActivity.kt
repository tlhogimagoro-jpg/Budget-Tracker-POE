package com.budgettracker.ui.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.budgettracker.data.AppDatabase
import com.budgettracker.databinding.ActivityLoginBinding
import com.budgettracker.ui.dashboard.DashboardActivity
import com.budgettracker.utils.SessionManager
import com.budgettracker.utils.ValidationUtils
import kotlinx.coroutines.launch

/**
 * LoginActivity — entry point of the app.
 * Validates credentials against Room DB and starts a session on success.
 *
 * Uses ViewBinding to avoid boilerplate findViewById calls.
 * Reference: https://developer.android.com/topic/libraries/view-binding
 */
class LoginActivity : AppCompatActivity() {

    private val TAG = "LoginActivity"

    // ViewBinding instance — inflated in onCreate
    private lateinit var binding: ActivityLoginBinding
    private lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sessionManager = SessionManager(this)

        // If user is already logged in, skip straight to Dashboard
        if (sessionManager.isLoggedIn()) {
            Log.i(TAG, "User already logged in — skipping to Dashboard")
            navigateToDashboard()
            return
        }

        setupClickListeners()
    }

    private fun setupClickListeners() {
        // Login button
        binding.btnLogin.setOnClickListener {
            attemptLogin()
        }

        // Navigate to registration screen
        binding.tvRegisterLink.setOnClickListener {
            Log.d(TAG, "Navigating to RegisterActivity")
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }

    /**
     * Reads form inputs, validates them, queries the DB, and either
     * starts a session or shows an error message.
     */
    private fun attemptLogin() {
        val username = binding.etUsername.text.toString().trim()
        val password = binding.etPassword.text.toString()

        // Clear previous errors
        binding.tilUsername.error = null
        binding.tilPassword.error = null

        // Client-side validation before hitting the DB
        var hasError = false

        if (username.isEmpty()) {
            binding.tilUsername.error = "Username is required"
            hasError = true
        }

        if (password.isEmpty()) {
            binding.tilPassword.error = "Password is required"
            hasError = true
        }

        if (hasError) return

        // Show loading state
        setLoading(true)

        val db = AppDatabase.getDatabase(this)
        val passwordHash = ValidationUtils.hashPassword(password)

        lifecycleScope.launch {
            Log.d(TAG, "Attempting login for username: $username")

            val user = db.userDao().login(username, passwordHash)

            runOnUiThread {
                setLoading(false)

                if (user != null) {
                    Log.i(TAG, "Login successful for userId=${user.id}")
                    sessionManager.saveSession(user.id, user.username)
                    navigateToDashboard()
                } else {
                    Log.w(TAG, "Login failed — invalid credentials for username: $username")
                    binding.tilPassword.error = "Invalid username or password"
                }
            }
        }
    }

    private fun setLoading(loading: Boolean) {
        binding.btnLogin.isEnabled = !loading
        binding.progressBar.visibility = if (loading) View.VISIBLE else View.GONE
    }

    /**
     * Navigate to Dashboard and clear back stack so user can't go back to Login.
     */
    private fun navigateToDashboard() {
        val intent = Intent(this, DashboardActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        startActivity(intent)
        finish()
    }
}
