package com.budgettracker.ui.expenses;

/**
 * ExpenseListActivity — shows expenses filtered by a user-selected date range.
 * Tapping an expense opens ExpenseDetailActivity (via explicit Intent with expense ID).
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0012\u001a\u00020\u0013H\u0002J\b\u0010\u0014\u001a\u00020\u0013H\u0002J\u0012\u0010\u0015\u001a\u00020\u00132\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0014J\b\u0010\u0018\u001a\u00020\u0019H\u0016J\b\u0010\u001a\u001a\u00020\u0013H\u0002J\b\u0010\u001b\u001a\u00020\u0013H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.\u00a2\u0006\u0002\n\u0000R\u001a\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f0\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u000eX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001c"}, d2 = {"Lcom/budgettracker/ui/expenses/ExpenseListActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "TAG", "", "adapter", "Lcom/budgettracker/ui/adapters/ExpenseAdapter;", "binding", "Lcom/budgettracker/databinding/ActivityExpenseListBinding;", "categoryMap", "", "", "Lcom/budgettracker/data/entities/Category;", "fromDateMs", "", "sessionManager", "Lcom/budgettracker/utils/SessionManager;", "toDateMs", "applyFilter", "", "loadCategories", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onSupportNavigateUp", "", "setupDatePickers", "setupRecyclerView", "app_debug"})
public final class ExpenseListActivity extends androidx.appcompat.app.AppCompatActivity {
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String TAG = "ExpenseListActivity";
    private com.budgettracker.databinding.ActivityExpenseListBinding binding;
    private com.budgettracker.utils.SessionManager sessionManager;
    private com.budgettracker.ui.adapters.ExpenseAdapter adapter;
    private long fromDateMs;
    private long toDateMs;
    @org.jetbrains.annotations.NotNull()
    private java.util.Map<java.lang.Integer, com.budgettracker.data.entities.Category> categoryMap;
    
    public ExpenseListActivity() {
        super();
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void setupRecyclerView() {
    }
    
    private final void loadCategories() {
    }
    
    private final void setupDatePickers() {
    }
    
    private final void applyFilter() {
    }
    
    @java.lang.Override()
    public boolean onSupportNavigateUp() {
        return false;
    }
}