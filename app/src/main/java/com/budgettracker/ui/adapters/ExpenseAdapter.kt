package com.budgettracker.ui.adapters

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.budgettracker.data.entities.Category
import com.budgettracker.data.entities.ExpenseEntry
import com.budgettracker.databinding.ItemExpenseBinding
import com.budgettracker.utils.DateUtils

/**
 * ExpenseAdapter — RecyclerView adapter for the expense list.
 * Displays description, date/time, category, amount, and a camera icon if a photo is attached.
 *
 * [categoryMap] maps categoryId → Category for name and color lookup.
 * Updated via [updateCategoryMap] once categories load from DB.
 */
class ExpenseAdapter(
    private var categoryMap: Map<Int, Category>,
    private val onItemClick: (ExpenseEntry) -> Unit
) : ListAdapter<ExpenseEntry, ExpenseAdapter.ViewHolder>(DIFF_CALLBACK) {

    fun updateCategoryMap(map: Map<Int, Category>) {
        categoryMap = map
        notifyDataSetChanged()  // Refresh all items with updated category names
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemExpenseBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(private val binding: ItemExpenseBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(expense: ExpenseEntry) {
            binding.tvExpenseDescription.text = expense.description
            binding.tvAmount.text = "R %.2f".format(expense.amount)
            binding.tvExpenseMeta.text = "${DateUtils.formatDate(expense.date)}  •  ${expense.startTime}–${expense.endTime}"

            // Resolve category
            val category = expense.categoryId?.let { categoryMap[it] }
            binding.tvCategory.text = category?.name ?: "Uncategorised"

            // Category color stripe
            val colorHex = category?.colorHex ?: "#9E9E9E"
            val drawable = GradientDrawable().apply {
                shape = GradientDrawable.RECTANGLE
                cornerRadius = 8f
                try { setColor(Color.parseColor(colorHex)) }
                catch (e: IllegalArgumentException) { setColor(Color.GRAY) }
            }
            binding.viewCategoryColor.background = drawable

            // Show camera icon if expense has a photo
            binding.ivPhotoIndicator.visibility =
                if (!expense.photoPath.isNullOrBlank()) View.VISIBLE else View.GONE

            binding.root.setOnClickListener { onItemClick(expense) }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ExpenseEntry>() {
            override fun areItemsTheSame(old: ExpenseEntry, new: ExpenseEntry) = old.id == new.id
            override fun areContentsTheSame(old: ExpenseEntry, new: ExpenseEntry) = old == new
        }
    }
}
