package com.budgettracker.ui.categories

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.budgettracker.data.AppDatabase
import com.budgettracker.data.entities.Category
import com.budgettracker.databinding.ActivityAddCategoryBinding
import com.budgettracker.utils.SessionManager
import kotlinx.coroutines.launch

/**
 * AddCategoryActivity — Handles adding a new category with session validation.
 */
class AddCategoryActivity : AppCompatActivity() {

    private val TAG = "AddCategoryActivity"
    private lateinit var binding: ActivityAddCategoryBinding
    private lateinit var sessionManager: SessionManager

    private val colorSwatches = listOf(
        "#F44336", "#E91E63", "#9C27B0", "#673AB7", "#3F51B5",
        "#2196F3", "#03A9F4", "#00BCD4", "#009688", "#4CAF50",
        "#8BC34A", "#CDDC39", "#FFEB3B", "#FFC107", "#FF9800",
        "#FF5722", "#795548", "#9E9E9E", "#607D8B", "#000000"
    )

    private var selectedColor = "#4CAF50"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Add Category"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        sessionManager = SessionManager(this)
        
        // Safety check: if session is invalid, redirect to login
        if (sessionManager.getUserId() == -1) {
            finish()
            return
        }

        buildColorGrid()
        binding.btnSaveCategory.setOnClickListener { saveCategory() }
    }

    private fun buildColorGrid() {
        val size = resources.getDimensionPixelSize(android.R.dimen.notification_large_icon_height) / 2
        val margin = 8

        colorSwatches.forEach { hex ->
            val swatch = View(this).apply {
                val params = android.widget.GridLayout.LayoutParams().apply {
                    width = size
                    height = size
                    setMargins(margin, margin, margin, margin)
                }
                layoutParams = params

                val drawable = GradientDrawable().apply {
                    shape = GradientDrawable.OVAL
                    setColor(Color.parseColor(hex))
                }
                background = drawable

                setOnClickListener {
                    selectedColor = hex
                    highlightSelected(hex)
                }
            }
            binding.gridColors.addView(swatch)
        }
    }

    private fun highlightSelected(hex: String) {
        for (i in 0 until binding.gridColors.childCount) {
            val child = binding.gridColors.getChildAt(i)
            val colorHex = colorSwatches[i]
            val drawable = GradientDrawable().apply {
                shape = GradientDrawable.OVAL
                setColor(Color.parseColor(colorHex))
                if (colorHex == hex) {
                    setStroke(4, Color.WHITE)
                }
            }
            child.background = drawable
        }
    }

    private fun saveCategory() {
        val name = binding.etCategoryName.text.toString().trim()
        val userId = sessionManager.getUserId()

        if (name.isEmpty()) {
            binding.tilCategoryName.error = "Category name is required"
            return
        }

        if (userId == -1) {
            Toast.makeText(this, "Session expired. Please log in again.", Toast.LENGTH_LONG).show()
            finish()
            return
        }

        val category = Category(
            userId   = userId,
            name     = name,
            colorHex = selectedColor,
            iconType = "STANDARD"
        )

        lifecycleScope.launch {
            try {
                val db = AppDatabase.getDatabase(this@AddCategoryActivity)
                
                // Double check if user exists in DB to prevent foreign key violation
                val userExists = db.userDao().getUserById(userId) != null
                if (!userExists) {
                    runOnUiThread {
                        Toast.makeText(this@AddCategoryActivity, "Session invalid. Please re-login.", Toast.LENGTH_LONG).show()
                        sessionManager.clearSession()
                        finish()
                    }
                    return@launch
                }

                val rowId = db.categoryDao().insertCategory(category)
                Log.i(TAG, "Category saved successfully. RowId: $rowId")

                runOnUiThread {
                    Toast.makeText(this@AddCategoryActivity, "Category added!", Toast.LENGTH_SHORT).show()
                    finish()
                }
            } catch (e: Exception) {
                Log.e(TAG, "Failed to save category", e)
                runOnUiThread {
                    Toast.makeText(this@AddCategoryActivity, "Error saving category: ${e.message}", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean { finish(); return true }
}
