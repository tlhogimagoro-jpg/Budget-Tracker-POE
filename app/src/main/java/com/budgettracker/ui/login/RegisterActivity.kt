package com.budgettracker.ui.login

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.budgettracker.data.AppDatabase
import com.budgettracker.data.entities.User
import com.budgettracker.databinding.ActivityRegisterBinding
import com.budgettracker.utils.ValidationUtils
import kotlinx.coroutines.launch

/**
 * RegisterActivity — lets a new user create an account.
 * On success, finishes itself and returns to LoginActivity.
 */
class RegisterActivity : AppCompatActivity() {

    private val TAG = "RegisterActivity"
    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Create Account"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.btnRegister.setOnClickListener { attemptRegister() }
    }

    private fun attemptRegister() {
        val username  = binding.etUsername.text.toString().trim()
        val password  = binding.etPassword.text.toString()
        val confirm   = binding.etConfirmPassword.text.toString()

        // Clear previous errors
        binding.tilUsername.error        = null
        binding.tilPassword.error        = null
        binding.tilConfirmPassword.error = null

        var hasError = false

        if (!ValidationUtils.isValidUsername(username)) {
            binding.tilUsername.error = "3–20 characters, letters/numbers/underscores only"
            hasError = true
        }

        if (!ValidationUtils.isValidPassword(password)) {
            binding.tilPassword.error = "Password must be at least 6 characters"
            hasError = true
        }

        if (password != confirm) {
            binding.tilConfirmPassword.error = "Passwords do not match"
            hasError = true
        }

        if (hasError) return

        setLoading(true)

        val db = AppDatabase.getDatabase(this)

        lifecycleScope.launch {
            // Check if username already exists
            val existing = db.userDao().getUserByUsername(username)

            if (existing != null) {
                Log.w(TAG, "Registration failed — username already taken: $username")
                runOnUiThread {
                    setLoading(false)
                    binding.tilUsername.error = "Username already taken"
                }
                return@launch
            }

            // Create user with hashed password
            val newUser = User(
                username     = username,
                passwordHash = ValidationUtils.hashPassword(password)
            )

            val rowId = db.userDao().insertUser(newUser)

            runOnUiThread {
                setLoading(false)

                if (rowId > 0) {
                    Log.i(TAG, "Registration successful for username: $username")
                    // Show success and go back to Login
                    com.google.android.material.snackbar.Snackbar
                        .make(binding.root, "Account created! Please log in.", com.google.android.material.snackbar.Snackbar.LENGTH_LONG)
                        .show()
                    // Delay finish slightly so user can read the message
                    binding.root.postDelayed({ finish() }, 1500)
                } else {
                    Log.e(TAG, "DB insert returned -1 for username: $username")
                    binding.tilUsername.error = "Registration failed. Try again."
                }
            }
        }
    }

    private fun setLoading(loading: Boolean) {
        binding.btnRegister.isEnabled = !loading
        binding.progressBar.visibility = if (loading) View.VISIBLE else View.GONE
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
