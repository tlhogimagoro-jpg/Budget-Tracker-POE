package com.budgettracker.ui.reports;

/**
 * ReportActivity — shows total amount spent per category over a user-selected period.
 * Includes a BarChart visualization with min/max budget goal lines.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0000\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u000e\u001a\u00020\u000fH\u0002J\u0012\u0010\u0010\u001a\u00020\u000f2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0014J\b\u0010\u0013\u001a\u00020\u0014H\u0016J\b\u0010\u0015\u001a\u00020\u000fH\u0002J\b\u0010\u0016\u001a\u00020\u000fH\u0002J\b\u0010\u0017\u001a\u00020\u000fH\u0002J \u0010\u0018\u001a\u00020\u000f2\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001a2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0002J(\u0010\u001e\u001a\u00020\u000f2\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001a2\u0006\u0010\u001f\u001a\u00020 2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006!"}, d2 = {"Lcom/budgettracker/ui/reports/ReportActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "TAG", "", "adapter", "Lcom/budgettracker/ui/adapters/CategoryTotalAdapter;", "binding", "Lcom/budgettracker/databinding/ActivityReportBinding;", "fromDateMs", "", "sessionManager", "Lcom/budgettracker/utils/SessionManager;", "toDateMs", "generateReport", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onSupportNavigateUp", "", "setupChart", "setupDatePickers", "setupRecyclerView", "updateChart", "rows", "", "Lcom/budgettracker/ui/adapters/CategoryTotalAdapter$Row;", "goal", "Lcom/budgettracker/data/entities/MonthlyGoal;", "updateUI", "grandTotal", "", "app_debug"})
public final class ReportActivity extends androidx.appcompat.app.AppCompatActivity {
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String TAG = "ReportActivity";
    private com.budgettracker.databinding.ActivityReportBinding binding;
    private com.budgettracker.utils.SessionManager sessionManager;
    private com.budgettracker.ui.adapters.CategoryTotalAdapter adapter;
    private long fromDateMs;
    private long toDateMs;
    
    public ReportActivity() {
        super();
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void setupRecyclerView() {
    }
    
    private final void setupChart() {
    }
    
    private final void setupDatePickers() {
    }
    
    private final void generateReport() {
    }
    
    private final void updateUI(java.util.List<com.budgettracker.ui.adapters.CategoryTotalAdapter.Row> rows, double grandTotal, com.budgettracker.data.entities.MonthlyGoal goal) {
    }
    
    private final void updateChart(java.util.List<com.budgettracker.ui.adapters.CategoryTotalAdapter.Row> rows, com.budgettracker.data.entities.MonthlyGoal goal) {
    }
    
    @java.lang.Override()
    public boolean onSupportNavigateUp() {
        return false;
    }
}