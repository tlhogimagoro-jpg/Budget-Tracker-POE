package com.budgettracker.ui.categories

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.budgettracker.data.AppDatabase
import com.budgettracker.data.entities.Category
import com.budgettracker.databinding.ActivityCategoryBinding
import com.budgettracker.ui.adapters.CategoryAdapter
import com.budgettracker.utils.SessionManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * CategoryActivity — lists all categories.
 * Now supports upgrading categories to Premium using Multiplier Points.
 */
class CategoryActivity : AppCompatActivity() {

    private val TAG = "CategoryActivity"
    private lateinit var binding: ActivityCategoryBinding
    private lateinit var sessionManager: SessionManager
    private lateinit var adapter: CategoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Categories"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        sessionManager = SessionManager(this)
        setupRecyclerView()
        observeCategories()

        binding.fabAddCategory.setOnClickListener {
            startActivity(Intent(this, AddCategoryActivity::class.java))
        }
    }

    private fun setupRecyclerView() {
        adapter = CategoryAdapter(
            onDeleteClick = { category -> confirmDelete(category) },
            onUpgradeClick = { category -> checkAndUpgrade(category) }
        )
        binding.rvCategories.layoutManager = LinearLayoutManager(this)
        binding.rvCategories.adapter = adapter
    }

    private fun observeCategories() {
        val db = AppDatabase.getDatabase(this)
        db.categoryDao()
            .getCategoriesByUser(sessionManager.getUserId())
            .observe(this, Observer { categories ->
                adapter.submitList(categories)
                binding.tvEmpty.visibility = if (categories.isEmpty()) View.VISIBLE else View.GONE
                binding.rvCategories.visibility = if (categories.isEmpty()) View.GONE else View.VISIBLE
            })
    }

    /**
     * Check if user has enough Multiplier Points (e.g., 20) to upgrade to Premium.
     */
    private fun checkAndUpgrade(category: Category) {
        val userId = sessionManager.getUserId()
        val db = AppDatabase.getDatabase(this)
        val UPGRADE_COST = 20

        lifecycleScope.launch {
            val user = db.userDao().getUserById(userId)
            val currentPoints = user?.multiplierPoints ?: 0

            if (currentPoints >= UPGRADE_COST) {
                withContext(Dispatchers.Main) {
                    AlertDialog.Builder(this@CategoryActivity)
                        .setTitle("Upgrade Category")
                        .setMessage("Spend $UPGRADE_COST points to unlock Premium Icons for \"${category.name}\"?")
                        .setPositiveButton("Upgrade") { _, _ ->
                            performUpgrade(category.id, UPGRADE_COST)
                        }
                        .setNegativeButton("Cancel", null)
                        .show()
                }
            } else {
                withContext(Dispatchers.Main) {
                    Toast.makeText(
                        this@CategoryActivity,
                        "Not enough points! You need $UPGRADE_COST points. Current: $currentPoints",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }

    private fun performUpgrade(categoryId: Int, cost: Int) {
        val db = AppDatabase.getDatabase(this)
        lifecycleScope.launch {
            // Deduct points
            db.userDao().incrementMultiplierPoints(sessionManager.getUserId(), -cost)
            // Update category
            db.categoryDao().updateIconType(categoryId, "PREMIUM")
            
            withContext(Dispatchers.Main) {
                Toast.makeText(this@CategoryActivity, "Category Upgraded to Premium! 👑", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun confirmDelete(category: Category) {
        AlertDialog.Builder(this)
            .setTitle("Delete Category")
            .setMessage("Delete \"${category.name}\"?")
            .setPositiveButton("Delete") { _, _ ->
                lifecycleScope.launch(Dispatchers.IO) {
                    AppDatabase.getDatabase(this@CategoryActivity).categoryDao().deleteCategory(category)
                }
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    override fun onSupportNavigateUp(): Boolean { finish(); return true }
}
