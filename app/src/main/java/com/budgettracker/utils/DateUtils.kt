package com.budgettracker.utils

import java.text.SimpleDateFormat
import java.util.*

/**
 * DateUtils — helper functions for formatting and manipulating dates.
 * All dates are stored as epoch milliseconds (Long) in the database.
 *
 * Reference: https://developer.android.com/reference/java/text/SimpleDateFormat
 */
object DateUtils {

    private val dateFormat    = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
    private val timeFormat    = SimpleDateFormat("HH:mm", Locale.getDefault())
    private val monthFormat   = SimpleDateFormat("MMMM yyyy", Locale.getDefault())
    private val dbDateFormat  = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

    /** Format epoch ms to readable date: "15 Jan 2025" */
    fun formatDate(epochMs: Long): String = dateFormat.format(Date(epochMs))

    /** Format epoch ms to "January 2025" */
    fun formatMonth(epochMs: Long): String = monthFormat.format(Date(epochMs))

    /**
     * Get the start of a given day (00:00:00.000) as epoch ms.
     * Used for database range queries.
     */
    fun startOfDay(epochMs: Long): Long {
        val cal = Calendar.getInstance().apply {
            timeInMillis = epochMs
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }
        return cal.timeInMillis
    }

    /**
     * Get the end of a given day (23:59:59.999) as epoch ms.
     */
    fun endOfDay(epochMs: Long): Long {
        val cal = Calendar.getInstance().apply {
            timeInMillis = epochMs
            set(Calendar.HOUR_OF_DAY, 23)
            set(Calendar.MINUTE, 59)
            set(Calendar.SECOND, 59)
            set(Calendar.MILLISECOND, 999)
        }
        return cal.timeInMillis
    }

    /**
     * Get start of the current month.
     */
    fun startOfMonth(month: Int, year: Int): Long {
        val cal = Calendar.getInstance().apply {
            set(Calendar.YEAR, year)
            set(Calendar.MONTH, month - 1) // Calendar.MONTH is 0-based
            set(Calendar.DAY_OF_MONTH, 1)
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }
        return cal.timeInMillis
    }

    /**
     * Get end of the current month.
     */
    fun endOfMonth(month: Int, year: Int): Long {
        val cal = Calendar.getInstance().apply {
            set(Calendar.YEAR, year)
            set(Calendar.MONTH, month - 1)
            set(Calendar.DAY_OF_MONTH, getActualMaximum(Calendar.DAY_OF_MONTH))
            set(Calendar.HOUR_OF_DAY, 23)
            set(Calendar.MINUTE, 59)
            set(Calendar.SECOND, 59)
            set(Calendar.MILLISECOND, 999)
        }
        return cal.timeInMillis
    }

    /** Returns the current month (1-12). */
    fun currentMonth(): Int = Calendar.getInstance().get(Calendar.MONTH) + 1

    /** Returns the current year e.g. 2025. */
    fun currentYear(): Int = Calendar.getInstance().get(Calendar.YEAR)
}
