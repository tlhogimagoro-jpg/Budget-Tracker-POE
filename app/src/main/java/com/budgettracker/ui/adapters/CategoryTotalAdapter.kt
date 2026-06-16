package com.budgettracker.ui.adapters

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.budgettracker.databinding.ItemCategoryTotalBinding

/**
 * CategoryTotalAdapter — shows per-category spending totals on the Reports screen.
 * Each row includes a color dot, category name, amount, progress bar, and percentage.
 */
class CategoryTotalAdapter :
    ListAdapter<CategoryTotalAdapter.Row, CategoryTotalAdapter.ViewHolder>(DIFF_CALLBACK) {

    /**
     * Data class representing one row in the report.
     */
    data class Row(
        val categoryName: String,
        val colorHex: String,
        val total: Double,
        val sharePercent: Int
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCategoryTotalBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(private val binding: ItemCategoryTotalBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(row: Row) {
            binding.tvCategoryName.text  = row.categoryName
            binding.tvCategoryTotal.text = "R %.2f".format(row.total)
            binding.tvSharePercent.text  = "${row.sharePercent}% of total"
            binding.progressShare.progress = row.sharePercent

            val drawable = GradientDrawable().apply {
                shape = GradientDrawable.OVAL
                try { setColor(Color.parseColor(row.colorHex)) }
                catch (e: IllegalArgumentException) { setColor(Color.GRAY) }
            }
            binding.viewCategoryColor.background = drawable

            // Progress bar tinted to category color
            try {
                binding.progressShare.progressTintList =
                    android.content.res.ColorStateList.valueOf(Color.parseColor(row.colorHex))
            } catch (e: IllegalArgumentException) { /* leave default */ }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Row>() {
            override fun areItemsTheSame(old: Row, new: Row) = old.categoryName == new.categoryName
            override fun areContentsTheSame(old: Row, new: Row) = old == new
        }
    }
}
