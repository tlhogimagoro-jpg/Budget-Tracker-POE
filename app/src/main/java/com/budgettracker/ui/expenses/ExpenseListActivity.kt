package com.budgettracker.ui.expenses

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.budgettracker.data.AppDatabase
import com.budgettracker.data.entities.Category
import com.budgettracker.data.entities.ExpenseEntry
import com.budgettracker.databinding.ActivityExpenseListBinding
import com.budgettracker.ui.adapters.ExpenseAdapter
import com.budgettracker.utils.DateUtils
import com.budgettracker.utils.SessionManager
import com.google.android.material.datepicker.MaterialDatePicker
import kotlinx.coroutines.launch

/**
 * ExpenseListActivity — shows expenses filtered by a user-selected date range.
 * Tapping an expense opens ExpenseDetailActivity (via explicit Intent with expense ID).
 */
class ExpenseListActivity : AppCompatActivity() {

    private val TAG = "ExpenseListActivity"
    private lateinit var binding: ActivityExpenseListBinding
    private lateinit var sessionManager: SessionManager
    private lateinit var adapter: ExpenseAdapter

    private var fromDateMs: Long = DateUtils.startOfMonth(DateUtils.currentMonth(), DateUtils.currentYear())
    private var toDateMs:   Long = DateUtils.endOfMonth(DateUtils.currentMonth(), DateUtils.currentYear())

    // Map of categoryId → Category to resolve names in adapter
    private var categoryMap: Map<Int, Category> = emptyMap()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExpenseListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Expense List"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        sessionManager = SessionManager(this)

        // Show default range (current month)
        binding.etFromDate.setText(DateUtils.formatDate(fromDateMs))
        binding.etToDate.setText(DateUtils.formatDate(toDateMs))

        setupRecyclerView()
        loadCategories()
        setupDatePickers()

        binding.btnFilter.setOnClickListener { applyFilter() }
    }

    private fun setupRecyclerView() {
        adapter = ExpenseAdapter(
            categoryMap = categoryMap,
            onItemClick = { expense ->
                Log.d(TAG, "Opening detail for expense id=${expense.id}")
                val intent = Intent(this, ExpenseDetailActivity::class.java)
                intent.putExtra(ExpenseDetailActivity.EXTRA_EXPENSE_ID, expense.id)
                startActivity(intent)
            }
        )
        binding.rvExpenses.layoutManager = LinearLayoutManager(this)
        binding.rvExpenses.adapter = adapter
    }

    private fun loadCategories() {
        lifecycleScope.launch {
            val cats = AppDatabase.getDatabase(this@ExpenseListActivity)
                .categoryDao()
                .getCategoriesByUserSync(sessionManager.getUserId())
            categoryMap = cats.associateBy { it.id }
            adapter.updateCategoryMap(categoryMap)
            // Load expenses after categories are ready
            applyFilter()
        }
    }

    private fun setupDatePickers() {
        val openFromPicker = {
            MaterialDatePicker.Builder.datePicker()
                .setTitleText("From date")
                .setSelection(fromDateMs)
                .build()
                .apply {
                    addOnPositiveButtonClickListener { ms ->
                        fromDateMs = DateUtils.startOfDay(ms)
                        binding.etFromDate.setText(DateUtils.formatDate(fromDateMs))
                    }
                }.show(supportFragmentManager, "fromDate")
        }

        val openToPicker = {
            MaterialDatePicker.Builder.datePicker()
                .setTitleText("To date")
                .setSelection(toDateMs)
                .build()
                .apply {
                    addOnPositiveButtonClickListener { ms ->
                        toDateMs = DateUtils.endOfDay(ms)
                        binding.etToDate.setText(DateUtils.formatDate(toDateMs))
                    }
                }.show(supportFragmentManager, "toDate")
        }

        binding.etFromDate.setOnClickListener { openFromPicker() }
        binding.tilFromDate.setEndIconOnClickListener { openFromPicker() }
        binding.etToDate.setOnClickListener { openToPicker() }
        binding.tilToDate.setEndIconOnClickListener { openToPicker() }
    }

    private fun applyFilter() {
        if (fromDateMs > toDateMs) {
            binding.tilFromDate.error = "From date must be before To date"
            return
        }
        binding.tilFromDate.error = null

        Log.d(TAG, "Filtering expenses: ${DateUtils.formatDate(fromDateMs)} to ${DateUtils.formatDate(toDateMs)}")

        val db = AppDatabase.getDatabase(this)

        // Observe live data for the selected range
        db.expenseDao()
            .getExpensesByDateRange(sessionManager.getUserId(), fromDateMs, toDateMs)
            .observe(this) { expenses ->
                Log.d(TAG, "Expense list updated: ${expenses.size} items")
                adapter.submitList(expenses)

                val isEmpty = expenses.isEmpty()
                binding.tvEmpty.visibility     = if (isEmpty) View.VISIBLE else View.GONE
                binding.rvExpenses.visibility  = if (isEmpty) View.GONE  else View.VISIBLE

                // Show period total
                val total = expenses.sumOf { it.amount }
                binding.tvPeriodTotal.text = "Total: R %.2f".format(total)
            }
    }

    override fun onSupportNavigateUp(): Boolean { finish(); return true }
}
