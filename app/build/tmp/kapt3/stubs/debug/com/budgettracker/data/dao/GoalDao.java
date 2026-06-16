package com.budgettracker.data.dao;

/**
 * GoalDao — manages monthly spending goals per user.
 *
 * Reference: https://developer.android.com/training/data-storage/room/accessing-data
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0003\bg\u0018\u00002\u00020\u0001J\u001c\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\u00a7@\u00a2\u0006\u0002\u0010\u0007J(\u0010\b\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u0006H\u00a7@\u00a2\u0006\u0002\u0010\u000bJ\u0016\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0004H\u00a7@\u00a2\u0006\u0002\u0010\u000f\u00a8\u0006\u0010"}, d2 = {"Lcom/budgettracker/data/dao/GoalDao;", "", "getAllGoalsForUser", "", "Lcom/budgettracker/data/entities/MonthlyGoal;", "userId", "", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getGoalForMonth", "month", "year", "(IIILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertOrUpdateGoal", "", "goal", "(Lcom/budgettracker/data/entities/MonthlyGoal;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
@androidx.room.Dao()
public abstract interface GoalDao {
    
    /**
     * Insert or replace goal — REPLACE strategy means updating the goal for
     * the same month/year overwrites the previous entry (via unique constraint in DB).
     */
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertOrUpdateGoal(@org.jetbrains.annotations.NotNull()
    com.budgettracker.data.entities.MonthlyGoal goal, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    /**
     * Fetch goal for a specific month and year for the logged-in user.
     * Returns null if no goal has been set yet.
     */
    @androidx.room.Query(value = "SELECT * FROM monthly_goals WHERE userId = :userId AND month = :month AND year = :year LIMIT 1")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getGoalForMonth(int userId, int month, int year, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.budgettracker.data.entities.MonthlyGoal> $completion);
    
    /**
     * Get all goals for a user — used to show goal history.
     */
    @androidx.room.Query(value = "SELECT * FROM monthly_goals WHERE userId = :userId ORDER BY year DESC, month DESC")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getAllGoalsForUser(int userId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.budgettracker.data.entities.MonthlyGoal>> $completion);
}