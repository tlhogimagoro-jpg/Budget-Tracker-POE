package com.budgettracker.ui.goals

import android.os.Bundle
import android.util.Log
import android.widget.SeekBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.budgettracker.data.AppDatabase
import com.budgettracker.data.entities.MonthlyGoal
import com.budgettracker.databinding.ActivityGoalsBinding
import com.budgettracker.utils.DateUtils
import com.budgettracker.utils.SessionManager
import com.budgettracker.utils.ValidationUtils
import kotlinx.coroutines.launch
import java.util.Calendar

/**
 * GoalsActivity — Allows users to set spending goals with session validation.
 */
class GoalsActivity : AppCompatActivity() {

    private val TAG = "GoalsActivity"
    private lateinit var binding: ActivityGoalsBinding
    private lateinit var sessionManager: SessionManager

    private var displayMonth = DateUtils.currentMonth()
    private var displayYear  = DateUtils.currentYear()

    private var updatingFromSeekBar = false
    private var updatingFromText    = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGoalsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Monthly Goals"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        sessionManager = SessionManager(this)

        if (sessionManager.getUserId() == -1) {
            finish()
            return
        }

        setupMonthNav()
        setupSeekBarSync()
        updateMonthDisplay()
        loadGoalForCurrentMonth()

        binding.btnSaveGoal.setOnClickListener { saveGoal() }
    }

    private fun setupMonthNav() {
        binding.btnPrevMonth.setOnClickListener {
            if (displayMonth == 1) { displayMonth = 12; displayYear-- }
            else displayMonth--
            updateMonthDisplay()
            loadGoalForCurrentMonth()
        }

        binding.btnNextMonth.setOnClickListener {
            if (displayMonth == 12) { displayMonth = 1; displayYear++ }
            else displayMonth++
            updateMonthDisplay()
            loadGoalForCurrentMonth()
        }
    }

    private fun updateMonthDisplay() {
        val cal = Calendar.getInstance().apply {
            set(Calendar.MONTH, displayMonth - 1)
            set(Calendar.YEAR, displayYear)
        }
        binding.tvCurrentMonth.text = DateUtils.formatMonth(cal.timeInMillis)
    }

    private fun setupSeekBarSync() {
        binding.seekBarMin.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                if (fromUser && !updatingFromText) {
                    updatingFromSeekBar = true
                    binding.etMinGoal.setText(progress.toString())
                    updatingFromSeekBar = false
                }
            }
            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })

        binding.etMinGoal.addTextChangedListener(object : android.text.TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, st: Int, c: Int, a: Int) {}
            override fun onTextChanged(s: CharSequence?, st: Int, b: Int, c: Int) {}
            override fun afterTextChanged(s: android.text.Editable?) {
                if (!updatingFromSeekBar) {
                    updatingFromText = true
                    val v = s.toString().toDoubleOrNull()?.toInt() ?: 0
                    binding.seekBarMin.progress = v.coerceIn(0, 10000)
                    updatingFromText = false
                }
            }
        })

        binding.seekBarMax.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                if (fromUser && !updatingFromText) {
                    updatingFromSeekBar = true
                    binding.etMaxGoal.setText(progress.toString())
                    updatingFromSeekBar = false
                }
            }
            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })

        binding.etMaxGoal.addTextChangedListener(object : android.text.TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, st: Int, c: Int, a: Int) {}
            override fun onTextChanged(s: CharSequence?, st: Int, b: Int, c: Int) {}
            override fun afterTextChanged(s: android.text.Editable?) {
                if (!updatingFromSeekBar) {
                    updatingFromText = true
                    val v = s.toString().toDoubleOrNull()?.toInt() ?: 0
                    binding.seekBarMax.progress = v.coerceIn(0, 10000)
                    updatingFromText = false
                }
            }
        })
    }

    private fun loadGoalForCurrentMonth() {
        val userId = sessionManager.getUserId()
        lifecycleScope.launch {
            val goal = AppDatabase.getDatabase(this@GoalsActivity)
                .goalDao()
                .getGoalForMonth(userId, displayMonth, displayYear)

            runOnUiThread {
                if (goal != null) {
                    binding.etMinGoal.setText("%.0f".format(goal.minGoal))
                    binding.etMaxGoal.setText("%.0f".format(goal.maxGoal))
                } else {
                    binding.etMinGoal.setText("")
                    binding.etMaxGoal.setText("")
                    binding.seekBarMin.progress = 0
                    binding.seekBarMax.progress = 0
                }
            }
        }
    }

    private fun saveGoal() {
        val minStr = binding.etMinGoal.text.toString().trim()
        val maxStr = binding.etMaxGoal.text.toString().trim()
        val userId = sessionManager.getUserId()

        binding.tilMinGoal.error = null
        binding.tilMaxGoal.error = null

        if (userId == -1) {
            Toast.makeText(this, "Session expired", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        var hasError = false
        if (!ValidationUtils.isValidAmount(minStr)) {
            binding.tilMinGoal.error = "Invalid amount"; hasError = true
        }
        if (!ValidationUtils.isValidAmount(maxStr)) {
            binding.tilMaxGoal.error = "Invalid amount"; hasError = true
        }

        if (hasError) return

        val min = minStr.toDouble()
        val max = maxStr.toDouble()

        if (min >= max) {
            binding.tilMaxGoal.error = "Max must be > Min"
            return
        }

        val goal = MonthlyGoal(
            userId   = userId,
            month    = displayMonth,
            year     = displayYear,
            minGoal  = min,
            maxGoal  = max
        )

        lifecycleScope.launch {
            try {
                val db = AppDatabase.getDatabase(this@GoalsActivity)
                
                // Validate user existence
                if (db.userDao().getUserById(userId) == null) {
                    runOnUiThread {
                        Toast.makeText(this@GoalsActivity, "User not found. Re-login required.", Toast.LENGTH_LONG).show()
                        sessionManager.clearSession()
                        finish()
                    }
                    return@launch
                }

                db.goalDao().insertOrUpdateGoal(goal)
                Log.i(TAG, "Goal saved for $displayMonth/$displayYear")

                runOnUiThread {
                    Toast.makeText(this@GoalsActivity, "Goal saved!", Toast.LENGTH_SHORT).show()
                    finish()
                }
            } catch (e: Exception) {
                Log.e(TAG, "Save goal failed", e)
                runOnUiThread {
                    Toast.makeText(this@GoalsActivity, "Error: ${e.message}", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean { finish(); return true }
}
