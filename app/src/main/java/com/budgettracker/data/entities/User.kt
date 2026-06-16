package com.budgettracker.data.entities

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

/**
 * User entity — stores login credentials and gamification data.
 * Added a unique index on username to prevent duplicate accounts and support OnConflictStrategy.IGNORE.
 */
@Entity(
    tableName = "users",
    indices = [Index(value = ["username"], unique = true)]
)
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val username: String,       // Unique username chosen at registration
    val passwordHash: String,   // SHA-256 hash of the password
    val multiplierPoints: Int = 0, // Points earned via streaks
    val lastStreakAwardDate: Long = 0, // Epoch MS of the last time a streak was rewarded
    val createdAt: Long = System.currentTimeMillis()
)
