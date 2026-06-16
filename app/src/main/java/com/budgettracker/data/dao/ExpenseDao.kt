package com.budgettracker.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.budgettracker.data.entities.ExpenseEntry

/**
 * ExpenseDao — handles all expense entry queries including date-range filtering
 * and per-category totals used in the reports screen.
 *
 * Reference: https://developer.android.com/training/data-storage/room/accessing-data
 */
@Dao
interface ExpenseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExpense(expense: ExpenseEntry): Long

    @Update
    suspend fun updateExpense(expense: ExpenseEntry)

    @Delete
    suspend fun deleteExpense(expense: ExpenseEntry)

    /**
     * Get all expenses for a user within a date range.
     * [startDate] and [endDate] are epoch milliseconds (start of day / end of day).
     * Ordered by date descending (newest first).
     */
    @Query("""
        SELECT * FROM expense_entries 
        WHERE userId = :userId 
        AND date >= :startDate 
        AND date <= :endDate 
        ORDER BY date DESC, startTime ASC
    """)
    fun getExpensesByDateRange(userId: Int, startDate: Long, endDate: Long): LiveData<List<ExpenseEntry>>

    /**
     * Non-LiveData version for sync access (e.g. report calculations).
     */
    @Query("""
        SELECT * FROM expense_entries 
        WHERE userId = :userId 
        AND date >= :startDate 
        AND date <= :endDate 
        ORDER BY date DESC
    """)
    suspend fun getExpensesByDateRangeSync(userId: Int, startDate: Long, endDate: Long): List<ExpenseEntry>

    /**
     * Data class to hold per-category totals from a GROUP BY query.
     * Used on the Reports screen.
     */
    data class CategoryTotal(
        val categoryId: Int?,
        val total: Double
    )

    /**
     * Returns the sum of expenses grouped by category for a date range.
     * This powers the "spending by category" report.
     */
    @Query("""
        SELECT categoryId, SUM(amount) as total 
        FROM expense_entries 
        WHERE userId = :userId 
        AND date >= :startDate 
        AND date <= :endDate 
        GROUP BY categoryId
    """)
    suspend fun getCategoryTotals(userId: Int, startDate: Long, endDate: Long): List<CategoryTotal>

    /**
     * Get total spending for a specific month — used for goal comparison on dashboard.
     */
    @Query("""
        SELECT COALESCE(SUM(amount), 0.0) 
        FROM expense_entries 
        WHERE userId = :userId 
        AND date >= :startDate 
        AND date <= :endDate
    """)
    suspend fun getTotalForPeriod(userId: Int, startDate: Long, endDate: Long): Double

    /**
     * Get a single expense by ID — used when opening detail view.
     */
    @Query("SELECT * FROM expense_entries WHERE id = :expenseId LIMIT 1")
    suspend fun getExpenseById(expenseId: Int): ExpenseEntry?
}
