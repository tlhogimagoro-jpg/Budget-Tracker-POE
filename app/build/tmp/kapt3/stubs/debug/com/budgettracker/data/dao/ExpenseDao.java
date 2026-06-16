package com.budgettracker.data.dao;

/**
 * ExpenseDao — handles all expense entry queries including date-range filtering
 * and per-category totals used in the reports screen.
 *
 * Reference: https://developer.android.com/training/data-storage/room/accessing-data
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0004\bg\u0018\u00002\u00020\u0001:\u0001\u001aJ\u0016\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J,\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\rH\u00a7@\u00a2\u0006\u0002\u0010\u000fJ\u0018\u0010\u0010\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0011\u001a\u00020\u000bH\u00a7@\u00a2\u0006\u0002\u0010\u0012J,\u0010\u0013\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\b0\u00142\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\rH\'J,\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00050\b2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\rH\u00a7@\u00a2\u0006\u0002\u0010\u000fJ&\u0010\u0016\u001a\u00020\u00172\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\rH\u00a7@\u00a2\u0006\u0002\u0010\u000fJ\u0016\u0010\u0018\u001a\u00020\r2\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u0016\u0010\u0019\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006\u00a8\u0006\u001b"}, d2 = {"Lcom/budgettracker/data/dao/ExpenseDao;", "", "deleteExpense", "", "expense", "Lcom/budgettracker/data/entities/ExpenseEntry;", "(Lcom/budgettracker/data/entities/ExpenseEntry;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getCategoryTotals", "", "Lcom/budgettracker/data/dao/ExpenseDao$CategoryTotal;", "userId", "", "startDate", "", "endDate", "(IJJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getExpenseById", "expenseId", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getExpensesByDateRange", "Landroidx/lifecycle/LiveData;", "getExpensesByDateRangeSync", "getTotalForPeriod", "", "insertExpense", "updateExpense", "CategoryTotal", "app_debug"})
@androidx.room.Dao()
public abstract interface ExpenseDao {
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertExpense(@org.jetbrains.annotations.NotNull()
    com.budgettracker.data.entities.ExpenseEntry expense, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    @androidx.room.Update()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object updateExpense(@org.jetbrains.annotations.NotNull()
    com.budgettracker.data.entities.ExpenseEntry expense, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Delete()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteExpense(@org.jetbrains.annotations.NotNull()
    com.budgettracker.data.entities.ExpenseEntry expense, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    /**
     * Get all expenses for a user within a date range.
     * [startDate] and [endDate] are epoch milliseconds (start of day / end of day).
     * Ordered by date descending (newest first).
     */
    @androidx.room.Query(value = "\n        SELECT * FROM expense_entries \n        WHERE userId = :userId \n        AND date >= :startDate \n        AND date <= :endDate \n        ORDER BY date DESC, startTime ASC\n    ")
    @org.jetbrains.annotations.NotNull()
    public abstract androidx.lifecycle.LiveData<java.util.List<com.budgettracker.data.entities.ExpenseEntry>> getExpensesByDateRange(int userId, long startDate, long endDate);
    
    /**
     * Non-LiveData version for sync access (e.g. report calculations).
     */
    @androidx.room.Query(value = "\n        SELECT * FROM expense_entries \n        WHERE userId = :userId \n        AND date >= :startDate \n        AND date <= :endDate \n        ORDER BY date DESC\n    ")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getExpensesByDateRangeSync(int userId, long startDate, long endDate, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.budgettracker.data.entities.ExpenseEntry>> $completion);
    
    /**
     * Returns the sum of expenses grouped by category for a date range.
     * This powers the "spending by category" report.
     */
    @androidx.room.Query(value = "\n        SELECT categoryId, SUM(amount) as total \n        FROM expense_entries \n        WHERE userId = :userId \n        AND date >= :startDate \n        AND date <= :endDate \n        GROUP BY categoryId\n    ")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getCategoryTotals(int userId, long startDate, long endDate, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.budgettracker.data.dao.ExpenseDao.CategoryTotal>> $completion);
    
    /**
     * Get total spending for a specific month — used for goal comparison on dashboard.
     */
    @androidx.room.Query(value = "\n        SELECT COALESCE(SUM(amount), 0.0) \n        FROM expense_entries \n        WHERE userId = :userId \n        AND date >= :startDate \n        AND date <= :endDate\n    ")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getTotalForPeriod(int userId, long startDate, long endDate, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Double> $completion);
    
    /**
     * Get a single expense by ID — used when opening detail view.
     */
    @androidx.room.Query(value = "SELECT * FROM expense_entries WHERE id = :expenseId LIMIT 1")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getExpenseById(int expenseId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.budgettracker.data.entities.ExpenseEntry> $completion);
    
    /**
     * Data class to hold per-category totals from a GROUP BY query.
     * Used on the Reports screen.
     */
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0010\u0010\f\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003\u00a2\u0006\u0002\u0010\bJ\t\u0010\r\u001a\u00020\u0005H\u00c6\u0003J$\u0010\u000e\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005H\u00c6\u0001\u00a2\u0006\u0002\u0010\u000fJ\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0013\u001a\u00020\u0003H\u00d6\u0001J\t\u0010\u0014\u001a\u00020\u0015H\u00d6\u0001R\u0015\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\n\n\u0002\u0010\t\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b\u00a8\u0006\u0016"}, d2 = {"Lcom/budgettracker/data/dao/ExpenseDao$CategoryTotal;", "", "categoryId", "", "total", "", "(Ljava/lang/Integer;D)V", "getCategoryId", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getTotal", "()D", "component1", "component2", "copy", "(Ljava/lang/Integer;D)Lcom/budgettracker/data/dao/ExpenseDao$CategoryTotal;", "equals", "", "other", "hashCode", "toString", "", "app_debug"})
    public static final class CategoryTotal {
        @org.jetbrains.annotations.Nullable()
        private final java.lang.Integer categoryId = null;
        private final double total = 0.0;
        
        public CategoryTotal(@org.jetbrains.annotations.Nullable()
        java.lang.Integer categoryId, double total) {
            super();
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.Integer getCategoryId() {
            return null;
        }
        
        public final double getTotal() {
            return 0.0;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.Integer component1() {
            return null;
        }
        
        public final double component2() {
            return 0.0;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.budgettracker.data.dao.ExpenseDao.CategoryTotal copy(@org.jetbrains.annotations.Nullable()
        java.lang.Integer categoryId, double total) {
            return null;
        }
        
        @java.lang.Override()
        public boolean equals(@org.jetbrains.annotations.Nullable()
        java.lang.Object other) {
            return false;
        }
        
        @java.lang.Override()
        public int hashCode() {
            return 0;
        }
        
        @java.lang.Override()
        @org.jetbrains.annotations.NotNull()
        public java.lang.String toString() {
            return null;
        }
    }
}