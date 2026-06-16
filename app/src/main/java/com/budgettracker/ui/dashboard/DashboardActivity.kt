package com.budgettracker.ui.dashboard

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.budgettracker.data.AppDatabase
import com.budgettracker.data.entities.MonthlyGoal
import com.budgettracker.databinding.ActivityDashboardBinding
import com.budgettracker.ui.categories.CategoryActivity
import com.budgettracker.ui.expenses.AddExpenseActivity
import com.budgettracker.ui.expenses.ExpenseListActivity
import com.budgettracker.ui.goals.GoalsActivity
import com.budgettracker.ui.login.LoginActivity
import com.budgettracker.ui.reports.ReportActivity
import com.budgettracker.utils.DateUtils
import com.budgettracker.utils.SessionManager
import kotlinx.coroutines.launch

/**
 * DashboardActivity — Main hub.
 * Handles spending summary, visual nudges, and streak multipliers.
 */
class DashboardActivity : AppCompatActivity() {

    private val TAG = "DashboardActivity"
    private lateinit var binding: ActivityDashboardBinding
    private lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sessionManager = SessionManager(this)

        setupUI()
        setupClickListeners()
    }

    override fun onResume() {
        super.onResume()
        validateSessionAndLoadData()
    }

    private fun setupUI() {
        binding.tvWelcome.text = "Welcome, ${sessionManager.getUsername()}!"
        binding.tvMonth.text   = DateUtils.formatMonth(System.currentTimeMillis())
    }

    private fun setupClickListeners() {
        binding.cardCategories.setOnClickListener {
            startActivity(Intent(this, CategoryActivity::class.java))
        }
        binding.cardAddExpense.setOnClickListener {
            startActivity(Intent(this, AddExpenseActivity::class.java))
        }
        binding.cardViewExpenses.setOnClickListener {
            startActivity(Intent(this, ExpenseListActivity::class.java))
        }
        binding.cardGoals.setOnClickListener {
            startActivity(Intent(this, GoalsActivity::class.java))
        }
        binding.cardReports.setOnClickListener {
            startActivity(Intent(this, ReportActivity::class.java))
        }
        binding.btnLogout.setOnClickListener {
            confirmLogout()
        }
    }

    private fun validateSessionAndLoadData() {
        val userId = sessionManager.getUserId()
        if (userId == -1) {
            Log.e(TAG, "No valid userId in session. Redirecting to login.")
            navigateToLogin()
            return
        }

        val db = AppDatabase.getDatabase(this)
        lifecycleScope.launch {
            val user = db.userDao().getUserById(userId)
            if (user == null) {
                Log.w(TAG, "User $userId not found in database. Redirecting to login.")
                sessionManager.clearSession()
                runOnUiThread { navigateToLogin() }
                return@launch
            }
            
            loadMonthSummary(user)
        }
    }

    private fun loadMonthSummary(user: com.budgettracker.data.entities.User) {
        val userId = user.id
        val month  = DateUtils.currentMonth()
        val year   = DateUtils.currentYear()
        val db     = AppDatabase.getDatabase(this)

        lifecycleScope.launch {
            val start = DateUtils.startOfMonth(month, year)
            val end   = DateUtils.endOfMonth(month, year)

            val totalSpent = db.expenseDao().getTotalForPeriod(userId, start, end)
            val goal       = db.goalDao().getGoalForMonth(userId, month, year)
            
            // Calculate streak (last 5 days)
            var streakCount = 0
            val todayStart = DateUtils.startOfDay(System.currentTimeMillis())
            for (i in 0..4) {
                val day = todayStart - (i * 24 * 60 * 60 * 1000L)
                val s = day
                val e = DateUtils.endOfDay(day)
                if (db.expenseDao().getExpensesByDateRangeSync(userId, s, e).isNotEmpty()) {
                    streakCount++
                } else {
                    break
                }
            }

            // Award points for 5-day streak (once per day)
            if (streakCount == 5 && user.lastStreakAwardDate < todayStart) {
                db.userDao().incrementMultiplierPoints(userId, 10)
                db.userDao().updateLastStreakAwardDate(userId, todayStart)
                runOnUiThread {
                    Toast.makeText(this@DashboardActivity, "🔥 5-Day Streak! +10 Multiplier Points!", Toast.LENGTH_LONG).show()
                }
            }

            // Get updated user for UI
            val latestUser = db.userDao().getUserById(userId)
            val loggedToday = db.expenseDao().getExpensesByDateRangeSync(userId, todayStart, DateUtils.endOfDay(todayStart)).isNotEmpty()

            runOnUiThread {
                updateSpendingUI(totalSpent, goal)
                updateStatusCard(totalSpent, goal)
                updateBadges(totalSpent, goal, loggedToday, streakCount >= 5)
                applyContextualNudges(totalSpent, goal)
                
                latestUser?.let {
                    binding.tvWelcome.text = "Welcome, ${it.username}! (Points: ${it.multiplierPoints})"
                }
            }
        }
    }

    private fun updateSpendingUI(totalSpent: Double, goal: MonthlyGoal?) {
        binding.tvTotalSpent.text = "R %.2f".format(totalSpent)
        if (goal != null) {
            binding.tvGoalRange.text = "Goal: R %.2f – R %.2f".format(goal.minGoal, goal.maxGoal)
            binding.progressGoal.max = goal.maxGoal.toInt().coerceAtLeast(1)
            binding.progressGoal.progress = totalSpent.toInt().coerceAtMost(goal.maxGoal.toInt())
        } else {
            binding.tvGoalRange.text = "No goal set for this month"
            binding.progressGoal.progress = 0
            binding.tvTotalSpent.setTextColor(getColor(com.budgettracker.R.color.text_primary))
        }
    }

    private fun updateStatusCard(totalSpent: Double, goal: MonthlyGoal?) {
        if (goal == null) {
            binding.cardStatus.visibility = View.VISIBLE
            binding.tvStatusIcon.text = "ℹ️"
            binding.tvStatusTitle.text = "No Goal Set"
            binding.tvStatusDesc.text = "Set a monthly goal to track your progress."
            return
        }

        val percentUsed = totalSpent / goal.maxGoal

        when {
            totalSpent > goal.maxGoal -> {
                binding.tvStatusIcon.text = "⚠️"
                binding.tvStatusTitle.text = "Over Budget"
                binding.tvStatusDesc.text = "You've exceeded your maximum spending goal."
                binding.tvTotalSpent.setTextColor(getColor(com.budgettracker.R.color.over_budget))
            }
            percentUsed >= 0.9 -> {
                binding.tvStatusIcon.text = "🌇"
                binding.tvStatusTitle.text = "Sunset Warning"
                binding.tvStatusDesc.text = "You've used over 90% of your budget!"
                binding.tvTotalSpent.setTextColor(getColor(com.budgettracker.R.color.under_min))
            }
            totalSpent < goal.minGoal -> {
                binding.tvStatusIcon.text = "📉"
                binding.tvStatusTitle.text = "Under Minimum"
                binding.tvStatusDesc.text = "Spending is below your awareness floor."
                binding.tvTotalSpent.setTextColor(getColor(com.budgettracker.R.color.under_min))
            }
            else -> {
                binding.tvStatusIcon.text = "☀️"
                binding.tvStatusTitle.text = "Sunny Skies"
                binding.tvStatusDesc.text = "Great job! You're staying within your budget."
                binding.tvTotalSpent.setTextColor(getColor(com.budgettracker.R.color.on_track))
            }
        }
    }

    private fun applyContextualNudges(totalSpent: Double, goal: MonthlyGoal?) {
        if (goal == null) {
            binding.root.setBackgroundColor(getColor(com.budgettracker.R.color.background))
            return
        }

        val percentUsed = totalSpent / goal.maxGoal
        when {
            percentUsed >= 0.9 -> binding.root.setBackgroundColor(getColor(com.budgettracker.R.color.theme_sunset))
            totalSpent <= goal.maxGoal -> binding.root.setBackgroundColor(getColor(com.budgettracker.R.color.theme_sunny))
            else -> binding.root.setBackgroundColor(getColor(com.budgettracker.R.color.background))
        }
    }

    private fun updateBadges(totalSpent: Double, goal: MonthlyGoal?, loggedToday: Boolean, isHighStreak: Boolean) {
        val layout = binding.layoutBadges
        layout.getChildAt(0).alpha = if (goal != null) 1.0f else 0.3f
        layout.getChildAt(1).alpha = if (goal != null && totalSpent <= goal.maxGoal) 1.0f else 0.3f
        layout.getChildAt(2).alpha = if (loggedToday) 1.0f else 0.3f
        
        val streakBadge = layout.getChildAt(3) as android.widget.TextView
        streakBadge.alpha = if (isHighStreak) 1.0f else 0.3f
        if (isHighStreak) streakBadge.text = "🔥 x5" else streakBadge.text = "🔥"
    }

    private fun navigateToLogin() {
        val intent = Intent(this, LoginActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        startActivity(intent)
        finish()
    }

    private fun confirmLogout() {
        AlertDialog.Builder(this)
            .setTitle("Log Out")
            .setMessage("Are you sure you want to log out?")
            .setPositiveButton("Log Out") { _, _ ->
                sessionManager.clearSession()
                navigateToLogin()
            }
            .setNegativeButton("Cancel", null)
            .show()
    }
}
