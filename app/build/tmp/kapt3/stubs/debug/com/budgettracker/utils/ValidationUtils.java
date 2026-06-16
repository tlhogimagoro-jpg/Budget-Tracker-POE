package com.budgettracker.utils;

/**
 * ValidationUtils — input validation and password hashing helpers.
 * Centralising validation prevents duplicate logic across Activities.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\n\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004J\u0016\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u0004J\u000e\u0010\n\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\u0004J\u000e\u0010\f\u001a\u00020\u00072\u0006\u0010\u0005\u001a\u00020\u0004J\u000e\u0010\r\u001a\u00020\u00072\u0006\u0010\u000e\u001a\u00020\u0004J\u000e\u0010\u000f\u001a\u00020\u00072\u0006\u0010\u0010\u001a\u00020\u0004\u00a8\u0006\u0011"}, d2 = {"Lcom/budgettracker/utils/ValidationUtils;", "", "()V", "hashPassword", "", "password", "isStartBeforeEnd", "", "startTime", "endTime", "isValidAmount", "amountStr", "isValidPassword", "isValidTime", "time", "isValidUsername", "username", "app_debug"})
public final class ValidationUtils {
    @org.jetbrains.annotations.NotNull()
    public static final com.budgettracker.utils.ValidationUtils INSTANCE = null;
    
    private ValidationUtils() {
        super();
    }
    
    /**
     * Hash a plain-text password using SHA-256.
     * NEVER store passwords in plain text.
     *
     * Reference: https://developer.android.com/reference/java/security/MessageDigest
     */
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String hashPassword(@org.jetbrains.annotations.NotNull()
    java.lang.String password) {
        return null;
    }
    
    /**
     * Username must be 3–20 alphanumeric characters.
     */
    public final boolean isValidUsername(@org.jetbrains.annotations.NotNull()
    java.lang.String username) {
        return false;
    }
    
    /**
     * Password must be at least 6 characters.
     */
    public final boolean isValidPassword(@org.jetbrains.annotations.NotNull()
    java.lang.String password) {
        return false;
    }
    
    /**
     * Validate that the amount string parses to a positive Double.
     */
    public final boolean isValidAmount(@org.jetbrains.annotations.NotNull()
    java.lang.String amountStr) {
        return false;
    }
    
    /**
     * Validate a time string in "HH:mm" format.
     * Hours 0–23, minutes 0–59.
     */
    public final boolean isValidTime(@org.jetbrains.annotations.NotNull()
    java.lang.String time) {
        return false;
    }
    
    /**
     * Ensure startTime is before endTime.
     * Times are "HH:mm" strings.
     */
    public final boolean isStartBeforeEnd(@org.jetbrains.annotations.NotNull()
    java.lang.String startTime, @org.jetbrains.annotations.NotNull()
    java.lang.String endTime) {
        return false;
    }
}