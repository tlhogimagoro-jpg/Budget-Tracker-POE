package com.budgettracker.data.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

/**
 * Category entity — expense categories belong to a specific user.
 * Now includes iconType to support premium gamification assets.
 */
@Entity(
    tableName = "categories",
    foreignKeys = [
        ForeignKey(
            entity = User::class,
            parentColumns = ["id"],
            childColumns = ["userId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class Category(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val userId: Int,
    val name: String,
    val colorHex: String = "#4CAF50",
    val iconType: String = "STANDARD", // STANDARD, PREMIUM
    val createdAt: Long = System.currentTimeMillis()
)
