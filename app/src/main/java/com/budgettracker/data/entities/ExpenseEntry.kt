package com.budgettracker.data.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

/**
 * ExpenseEntry entity — a single expense log with date, time range,
 * description, amount, category, and optional photo.
 *
 * Timestamps are stored as epoch milliseconds (Long) for easy range queries.
 *
 * Reference: https://developer.android.com/training/data-storage/room/defining-data
 */
@Entity(
    tableName = "expense_entries",
    foreignKeys = [
        ForeignKey(
            entity = User::class,
            parentColumns = ["id"],
            childColumns = ["userId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Category::class,
            parentColumns = ["id"],
            childColumns = ["categoryId"],
            onDelete = ForeignKey.SET_NULL  // Keep expense if category deleted
        )
    ]
)
data class ExpenseEntry(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val userId: Int,                    // Owner
    val categoryId: Int?,               // Nullable: category may be deleted
    val description: String,            // What was spent on
    val amount: Double,                 // Amount in ZAR

    val date: Long,                     // Date as epoch ms (date only, midnight)
    val startTime: String,              // "HH:mm" format e.g. "08:30"
    val endTime: String,                // "HH:mm" format e.g. "09:00"

    val photoPath: String? = null,      // Absolute file path to saved photo, or null
    val createdAt: Long = System.currentTimeMillis()
)
