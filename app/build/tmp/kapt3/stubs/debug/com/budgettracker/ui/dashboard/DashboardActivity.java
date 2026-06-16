package com.budgettracker.ui.dashboard;

/**
 * DashboardActivity — Main hub.
 * Handles spending summary, visual nudges, and streak multipliers.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u001a\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0002J\b\u0010\u000f\u001a\u00020\nH\u0002J\u0010\u0010\u0010\u001a\u00020\n2\u0006\u0010\u0011\u001a\u00020\u0012H\u0002J\b\u0010\u0013\u001a\u00020\nH\u0002J\u0012\u0010\u0014\u001a\u00020\n2\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0014J\b\u0010\u0017\u001a\u00020\nH\u0014J\b\u0010\u0018\u001a\u00020\nH\u0002J\b\u0010\u0019\u001a\u00020\nH\u0002J*\u0010\u001a\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001cH\u0002J\u001a\u0010\u001e\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0002J\u001a\u0010\u001f\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0002J\b\u0010 \u001a\u00020\nH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006!"}, d2 = {"Lcom/budgettracker/ui/dashboard/DashboardActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "TAG", "", "binding", "Lcom/budgettracker/databinding/ActivityDashboardBinding;", "sessionManager", "Lcom/budgettracker/utils/SessionManager;", "applyContextualNudges", "", "totalSpent", "", "goal", "Lcom/budgettracker/data/entities/MonthlyGoal;", "confirmLogout", "loadMonthSummary", "user", "Lcom/budgettracker/data/entities/User;", "navigateToLogin", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onResume", "setupClickListeners", "setupUI", "updateBadges", "loggedToday", "", "isHighStreak", "updateSpendingUI", "updateStatusCard", "validateSessionAndLoadData", "app_debug"})
public final class DashboardActivity extends androidx.appcompat.app.AppCompatActivity {
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String TAG = "DashboardActivity";
    private com.budgettracker.databinding.ActivityDashboardBinding binding;
    private com.budgettracker.utils.SessionManager sessionManager;
    
    public DashboardActivity() {
        super();
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override()
    protected void onResume() {
    }
    
    private final void setupUI() {
    }
    
    private final void setupClickListeners() {
    }
    
    private final void validateSessionAndLoadData() {
    }
    
    private final void loadMonthSummary(com.budgettracker.data.entities.User user) {
    }
    
    private final void updateSpendingUI(double totalSpent, com.budgettracker.data.entities.MonthlyGoal goal) {
    }
    
    private final void updateStatusCard(double totalSpent, com.budgettracker.data.entities.MonthlyGoal goal) {
    }
    
    private final void applyContextualNudges(double totalSpent, com.budgettracker.data.entities.MonthlyGoal goal) {
    }
    
    private final void updateBadges(double totalSpent, com.budgettracker.data.entities.MonthlyGoal goal, boolean loggedToday, boolean isHighStreak) {
    }
    
    private final void navigateToLogin() {
    }
    
    private final void confirmLogout() {
    }
}