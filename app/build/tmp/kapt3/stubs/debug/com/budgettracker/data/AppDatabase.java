package com.budgettracker.data;

/**
 * AppDatabase — singleton Room database instance.
 * Explicitly enables Foreign Keys to ensure data integrity between Users, Categories, and Goals.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\'\u0018\u0000 \u000b2\u00020\u0001:\u0001\u000bB\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H&J\b\u0010\u0005\u001a\u00020\u0006H&J\b\u0010\u0007\u001a\u00020\bH&J\b\u0010\t\u001a\u00020\nH&\u00a8\u0006\f"}, d2 = {"Lcom/budgettracker/data/AppDatabase;", "Landroidx/room/RoomDatabase;", "()V", "categoryDao", "Lcom/budgettracker/data/dao/CategoryDao;", "expenseDao", "Lcom/budgettracker/data/dao/ExpenseDao;", "goalDao", "Lcom/budgettracker/data/dao/GoalDao;", "userDao", "Lcom/budgettracker/data/dao/UserDao;", "Companion", "app_debug"})
@androidx.room.Database(entities = {com.budgettracker.data.entities.User.class, com.budgettracker.data.entities.Category.class, com.budgettracker.data.entities.ExpenseEntry.class, com.budgettracker.data.entities.MonthlyGoal.class}, version = 2, exportSchema = false)
public abstract class AppDatabase extends androidx.room.RoomDatabase {
    @kotlin.jvm.Volatile()
    @org.jetbrains.annotations.Nullable()
    private static volatile com.budgettracker.data.AppDatabase INSTANCE;
    @org.jetbrains.annotations.NotNull()
    public static final com.budgettracker.data.AppDatabase.Companion Companion = null;
    
    public AppDatabase() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.budgettracker.data.dao.UserDao userDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.budgettracker.data.dao.CategoryDao categoryDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.budgettracker.data.dao.ExpenseDao expenseDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.budgettracker.data.dao.GoalDao goalDao();
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0007R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\b"}, d2 = {"Lcom/budgettracker/data/AppDatabase$Companion;", "", "()V", "INSTANCE", "Lcom/budgettracker/data/AppDatabase;", "getDatabase", "context", "Landroid/content/Context;", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.budgettracker.data.AppDatabase getDatabase(@org.jetbrains.annotations.NotNull()
        android.content.Context context) {
            return null;
        }
    }
}