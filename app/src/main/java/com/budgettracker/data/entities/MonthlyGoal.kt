package com.budgettracker.data.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

/**
 * MonthlyGoal entity — stores the user's minimum and maximum spending goals
 * for a given month/year pair.
 *
 * Added a unique index on userId, month, and year to ensure OnConflictStrategy.REPLACE works correctly.
 */
@Entity(
    tableName = "monthly_goals",
    foreignKeys = [
        ForeignKey(
            entity = User::class,
            parentColumns = ["id"],
            childColumns = ["userId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index(value = ["userId", "month", "year"], unique = true)
    ]
)
data class MonthlyGoal(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val userId: Int,
    val month: Int,         // 1-12
    val year: Int,          // e.g. 2025

    val minGoal: Double,
    val maxGoal: Double,

    val updatedAt: Long = System.currentTimeMillis()
)
