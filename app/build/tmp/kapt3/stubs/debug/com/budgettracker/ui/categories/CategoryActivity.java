package com.budgettracker.ui.categories;

/**
 * CategoryActivity — lists all categories.
 * Now supports upgrading categories to Premium using Multiplier Points.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0002J\u0010\u0010\u000f\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0002J\b\u0010\u0010\u001a\u00020\fH\u0002J\u0012\u0010\u0011\u001a\u00020\f2\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0014J\b\u0010\u0014\u001a\u00020\u0015H\u0016J\u0018\u0010\u0016\u001a\u00020\f2\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u0018H\u0002J\b\u0010\u001a\u001a\u00020\fH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001b"}, d2 = {"Lcom/budgettracker/ui/categories/CategoryActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "TAG", "", "adapter", "Lcom/budgettracker/ui/adapters/CategoryAdapter;", "binding", "Lcom/budgettracker/databinding/ActivityCategoryBinding;", "sessionManager", "Lcom/budgettracker/utils/SessionManager;", "checkAndUpgrade", "", "category", "Lcom/budgettracker/data/entities/Category;", "confirmDelete", "observeCategories", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onSupportNavigateUp", "", "performUpgrade", "categoryId", "", "cost", "setupRecyclerView", "app_debug"})
public final class CategoryActivity extends androidx.appcompat.app.AppCompatActivity {
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String TAG = "CategoryActivity";
    private com.budgettracker.databinding.ActivityCategoryBinding binding;
    private com.budgettracker.utils.SessionManager sessionManager;
    private com.budgettracker.ui.adapters.CategoryAdapter adapter;
    
    public CategoryActivity() {
        super();
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void setupRecyclerView() {
    }
    
    private final void observeCategories() {
    }
    
    /**
     * Check if user has enough Multiplier Points (e.g., 20) to upgrade to Premium.
     */
    private final void checkAndUpgrade(com.budgettracker.data.entities.Category category) {
    }
    
    private final void performUpgrade(int categoryId, int cost) {
    }
    
    private final void confirmDelete(com.budgettracker.data.entities.Category category) {
    }
    
    @java.lang.Override()
    public boolean onSupportNavigateUp() {
        return false;
    }
}