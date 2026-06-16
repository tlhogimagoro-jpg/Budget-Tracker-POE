package com.budgettracker.utils;

/**
 * DateUtils — helper functions for formatting and manipulating dates.
 * All dates are stored as epoch milliseconds (Long) in the database.
 *
 * Reference: https://developer.android.com/reference/java/text/SimpleDateFormat
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0006\u0010\b\u001a\u00020\tJ\u0006\u0010\n\u001a\u00020\tJ\u000e\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\fJ\u0016\u0010\u000e\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\t2\u0006\u0010\u0010\u001a\u00020\tJ\u000e\u0010\u0011\u001a\u00020\u00122\u0006\u0010\r\u001a\u00020\fJ\u000e\u0010\u0013\u001a\u00020\u00122\u0006\u0010\r\u001a\u00020\fJ\u000e\u0010\u0014\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\fJ\u0016\u0010\u0015\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\t2\u0006\u0010\u0010\u001a\u00020\tR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0016"}, d2 = {"Lcom/budgettracker/utils/DateUtils;", "", "()V", "dateFormat", "Ljava/text/SimpleDateFormat;", "dbDateFormat", "monthFormat", "timeFormat", "currentMonth", "", "currentYear", "endOfDay", "", "epochMs", "endOfMonth", "month", "year", "formatDate", "", "formatMonth", "startOfDay", "startOfMonth", "app_debug"})
public final class DateUtils {
    @org.jetbrains.annotations.NotNull()
    private static final java.text.SimpleDateFormat dateFormat = null;
    @org.jetbrains.annotations.NotNull()
    private static final java.text.SimpleDateFormat timeFormat = null;
    @org.jetbrains.annotations.NotNull()
    private static final java.text.SimpleDateFormat monthFormat = null;
    @org.jetbrains.annotations.NotNull()
    private static final java.text.SimpleDateFormat dbDateFormat = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.budgettracker.utils.DateUtils INSTANCE = null;
    
    private DateUtils() {
        super();
    }
    
    /**
     * Format epoch ms to readable date: "15 Jan 2025"
     */
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String formatDate(long epochMs) {
        return null;
    }
    
    /**
     * Format epoch ms to "January 2025"
     */
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String formatMonth(long epochMs) {
        return null;
    }
    
    /**
     * Get the start of a given day (00:00:00.000) as epoch ms.
     * Used for database range queries.
     */
    public final long startOfDay(long epochMs) {
        return 0L;
    }
    
    /**
     * Get the end of a given day (23:59:59.999) as epoch ms.
     */
    public final long endOfDay(long epochMs) {
        return 0L;
    }
    
    /**
     * Get start of the current month.
     */
    public final long startOfMonth(int month, int year) {
        return 0L;
    }
    
    /**
     * Get end of the current month.
     */
    public final long endOfMonth(int month, int year) {
        return 0L;
    }
    
    /**
     * Returns the current month (1-12).
     */
    public final int currentMonth() {
        return 0;
    }
    
    /**
     * Returns the current year e.g. 2025.
     */
    public final int currentYear() {
        return 0;
    }
}