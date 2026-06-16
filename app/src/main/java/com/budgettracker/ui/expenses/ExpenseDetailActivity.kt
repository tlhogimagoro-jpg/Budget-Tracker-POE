package com.budgettracker.ui.expenses

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.budgettracker.data.AppDatabase
import com.budgettracker.databinding.ActivityExpenseDetailBinding
import com.budgettracker.utils.DateUtils
import kotlinx.coroutines.launch
import java.io.File

/**
 * ExpenseDetailActivity — shows the full details of a single expense.
 * If a photo was attached, it is displayed full-width at the top.
 * The user can delete the expense from here.
 *
 * Navigation: receives the expense ID via Intent extra.
 */
class ExpenseDetailActivity : AppCompatActivity() {

    private val TAG = "ExpenseDetailActivity"
    private lateinit var binding: ActivityExpenseDetailBinding

    companion object {
        const val EXTRA_EXPENSE_ID = "extra_expense_id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExpenseDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Expense Detail"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val expenseId = intent.getIntExtra(EXTRA_EXPENSE_ID, -1)

        if (expenseId == -1) {
            Log.e(TAG, "No expense ID passed — finishing")
            finish()
            return
        }

        loadExpense(expenseId)
    }

    private fun loadExpense(id: Int) {
        lifecycleScope.launch {
            val db      = AppDatabase.getDatabase(this@ExpenseDetailActivity)
            val expense = db.expenseDao().getExpenseById(id)

            if (expense == null) {
                Log.e(TAG, "Expense id=$id not found in DB")
                runOnUiThread { finish() }
                return@launch
            }

            Log.d(TAG, "Loaded expense: ${expense.description}, amount=${expense.amount}")

            // Resolve category name
            val categoryName = expense.categoryId?.let {
                db.categoryDao().getCategoryById(it)?.name
            } ?: "Uncategorised"

            runOnUiThread {
                binding.tvDetailDescription.text = expense.description
                binding.tvDetailAmount.text      = "R %.2f".format(expense.amount)
                binding.tvDetailDate.text        = DateUtils.formatDate(expense.date)
                binding.tvDetailTime.text        = "${expense.startTime} – ${expense.endTime}"
                binding.tvDetailCategory.text    = categoryName

                // Show photo if available
                if (!expense.photoPath.isNullOrBlank()) {
                    val file = File(expense.photoPath)
                    if (file.exists()) {
                        binding.ivExpensePhoto.visibility = View.VISIBLE
                        binding.ivExpensePhoto.setImageURI(Uri.fromFile(file))
                        Log.d(TAG, "Photo loaded from: ${expense.photoPath}")
                    } else {
                        Log.w(TAG, "Photo path exists in DB but file not found: ${expense.photoPath}")
                    }
                }

                binding.btnDeleteExpense.setOnClickListener {
                    AlertDialog.Builder(this@ExpenseDetailActivity)
                        .setTitle("Delete Expense")
                        .setMessage("Are you sure you want to delete this expense?")
                        .setPositiveButton("Delete") { _, _ ->
                            lifecycleScope.launch {
                                db.expenseDao().deleteExpense(expense)
                                Log.i(TAG, "Expense id=${expense.id} deleted")
                                runOnUiThread { finish() }
                            }
                        }
                        .setNegativeButton("Cancel", null)
                        .show()
                }
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean { finish(); return true }
}
