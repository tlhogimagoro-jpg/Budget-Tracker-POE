package com.budgettracker.utils

import java.security.MessageDigest

/**
 * ValidationUtils — input validation and password hashing helpers.
 * Centralising validation prevents duplicate logic across Activities.
 */
object ValidationUtils {

    /**
     * Hash a plain-text password using SHA-256.
     * NEVER store passwords in plain text.
     *
     * Reference: https://developer.android.com/reference/java/security/MessageDigest
     */
    fun hashPassword(password: String): String {
        val digest = MessageDigest.getInstance("SHA-256")
        val hashBytes = digest.digest(password.toByteArray(Charsets.UTF_8))
        return hashBytes.joinToString("") { "%02x".format(it) }
    }

    /** Username must be 3–20 alphanumeric characters. */
    fun isValidUsername(username: String): Boolean {
        return username.matches(Regex("^[a-zA-Z0-9_]{3,20}$"))
    }

    /** Password must be at least 6 characters. */
    fun isValidPassword(password: String): Boolean {
        return password.length >= 6
    }

    /** Validate that the amount string parses to a positive Double. */
    fun isValidAmount(amountStr: String): Boolean {
        return try {
            val amount = amountStr.toDouble()
            amount > 0
        } catch (e: NumberFormatException) {
            false
        }
    }

    /**
     * Validate a time string in "HH:mm" format.
     * Hours 0–23, minutes 0–59.
     */
    fun isValidTime(time: String): Boolean {
        return try {
            val parts = time.split(":")
            if (parts.size != 2) return false
            val h = parts[0].toInt()
            val m = parts[1].toInt()
            h in 0..23 && m in 0..59
        } catch (e: Exception) {
            false
        }
    }

    /**
     * Ensure startTime is before endTime.
     * Times are "HH:mm" strings.
     */
    fun isStartBeforeEnd(startTime: String, endTime: String): Boolean {
        return try {
            val (sh, sm) = startTime.split(":").map { it.toInt() }
            val (eh, em) = endTime.split(":").map { it.toInt() }
            val startMinutes = sh * 60 + sm
            val endMinutes   = eh * 60 + em
            startMinutes < endMinutes
        } catch (e: Exception) {
            false
        }
    }
}
