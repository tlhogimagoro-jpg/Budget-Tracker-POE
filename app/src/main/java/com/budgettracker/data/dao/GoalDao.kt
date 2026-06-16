package com.budgettracker.data.dao

import androidx.room.*
import com.budgettracker.data.entities.MonthlyGoal

/**
 * GoalDao — manages monthly spending goals per user.
 *
 * Reference: https://developer.android.com/training/data-storage/room/accessing-data
 */
@Dao
interface GoalDao {

    /**
     * Insert or replace goal — REPLACE strategy means updating the goal for
     * the same month/year overwrites the previous entry (via unique constraint in DB).
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdateGoal(goal: MonthlyGoal)

    /**
     * Fetch goal for a specific month and year for the logged-in user.
     * Returns null if no goal has been set yet.
     */
    @Query("SELECT * FROM monthly_goals WHERE userId = :userId AND month = :month AND year = :year LIMIT 1")
    suspend fun getGoalForMonth(userId: Int, month: Int, year: Int): MonthlyGoal?

    /**
     * Get all goals for a user — used to show goal history.
     */
    @Query("SELECT * FROM monthly_goals WHERE userId = :userId ORDER BY year DESC, month DESC")
    suspend fun getAllGoalsForUser(userId: Int): List<MonthlyGoal>
}
