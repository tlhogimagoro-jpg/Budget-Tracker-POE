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
import com.budgettracker.databinding.ItemCategoryBinding

/**
 * CategoryAdapter — RecyclerView adapter for displaying the list of categories.
 * Now supports Premium Icons and Upgrade functionality.
 */
class CategoryAdapter(
    private val onDeleteClick: (Category) -> Unit,
    private val onUpgradeClick: (Category) -> Unit
) : ListAdapter<Category, CategoryAdapter.ViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCategoryBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(private val binding: ItemCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(category: Category) {
            binding.tvCategoryName.text = category.name

            val isPremium = category.iconType == "PREMIUM"

            // UI for Premium vs Standard
            if (isPremium) {
                binding.viewColor.visibility = View.GONE
                binding.tvPremiumIcon.visibility = View.VISIBLE
                binding.tvPremiumStatus.visibility = View.VISIBLE
                binding.btnUpgrade.visibility = View.GONE
                binding.tvPremiumIcon.text = "👑" // Or a "Golden Bag" emoji 💰
            } else {
                binding.viewColor.visibility = View.VISIBLE
                binding.tvPremiumIcon.visibility = View.GONE
                binding.tvPremiumStatus.visibility = View.GONE
                binding.btnUpgrade.visibility = View.VISIBLE
                
                val drawable = GradientDrawable().apply {
                    shape = GradientDrawable.OVAL
                    try {
                        setColor(Color.parseColor(category.colorHex))
                    } catch (e: IllegalArgumentException) {
                        setColor(Color.GRAY)
                    }
                }
                binding.viewColor.background = drawable
            }

            binding.btnDelete.setOnClickListener {
                onDeleteClick(category)
            }
            
            binding.btnUpgrade.setOnClickListener {
                onUpgradeClick(category)
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Category>() {
            override fun areItemsTheSame(old: Category, new: Category) = old.id == new.id
            override fun areContentsTheSame(old: Category, new: Category) = old == new
        }
    }
}
