package com.budgettracker.ui.adapters;

/**
 * ExpenseAdapter — RecyclerView adapter for the expense list.
 * Displays description, date/time, category, amount, and a camera icon if a photo is attached.
 *
 * [categoryMap] maps categoryId → Category for name and color lookup.
 * Updated via [updateCategoryMap] once categories load from DB.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 \u00152\u0012\u0012\u0004\u0012\u00020\u0002\u0012\b\u0012\u00060\u0003R\u00020\u00000\u0001:\u0002\u0015\u0016B-\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005\u0012\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\n0\t\u00a2\u0006\u0002\u0010\u000bJ\u001c\u0010\f\u001a\u00020\n2\n\u0010\r\u001a\u00060\u0003R\u00020\u00002\u0006\u0010\u000e\u001a\u00020\u0006H\u0016J\u001c\u0010\u000f\u001a\u00060\u0003R\u00020\u00002\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0006H\u0016J\u001a\u0010\u0013\u001a\u00020\n2\u0012\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005R\u001a\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001a\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\n0\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0017"}, d2 = {"Lcom/budgettracker/ui/adapters/ExpenseAdapter;", "Landroidx/recyclerview/widget/ListAdapter;", "Lcom/budgettracker/data/entities/ExpenseEntry;", "Lcom/budgettracker/ui/adapters/ExpenseAdapter$ViewHolder;", "categoryMap", "", "", "Lcom/budgettracker/data/entities/Category;", "onItemClick", "Lkotlin/Function1;", "", "(Ljava/util/Map;Lkotlin/jvm/functions/Function1;)V", "onBindViewHolder", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "updateCategoryMap", "map", "Companion", "ViewHolder", "app_debug"})
public final class ExpenseAdapter extends androidx.recyclerview.widget.ListAdapter<com.budgettracker.data.entities.ExpenseEntry, com.budgettracker.ui.adapters.ExpenseAdapter.ViewHolder> {
    @org.jetbrains.annotations.NotNull()
    private java.util.Map<java.lang.Integer, com.budgettracker.data.entities.Category> categoryMap;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.jvm.functions.Function1<com.budgettracker.data.entities.ExpenseEntry, kotlin.Unit> onItemClick = null;
    @org.jetbrains.annotations.NotNull()
    private static final androidx.recyclerview.widget.DiffUtil.ItemCallback<com.budgettracker.data.entities.ExpenseEntry> DIFF_CALLBACK = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.budgettracker.ui.adapters.ExpenseAdapter.Companion Companion = null;
    
    public ExpenseAdapter(@org.jetbrains.annotations.NotNull()
    java.util.Map<java.lang.Integer, com.budgettracker.data.entities.Category> categoryMap, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super com.budgettracker.data.entities.ExpenseEntry, kotlin.Unit> onItemClick) {
        super(null);
    }
    
    public final void updateCategoryMap(@org.jetbrains.annotations.NotNull()
    java.util.Map<java.lang.Integer, com.budgettracker.data.entities.Category> map) {
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public com.budgettracker.ui.adapters.ExpenseAdapter.ViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override()
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
    com.budgettracker.ui.adapters.ExpenseAdapter.ViewHolder holder, int position) {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0006"}, d2 = {"Lcom/budgettracker/ui/adapters/ExpenseAdapter$Companion;", "", "()V", "DIFF_CALLBACK", "Landroidx/recyclerview/widget/DiffUtil$ItemCallback;", "Lcom/budgettracker/data/entities/ExpenseEntry;", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\t"}, d2 = {"Lcom/budgettracker/ui/adapters/ExpenseAdapter$ViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "Lcom/budgettracker/databinding/ItemExpenseBinding;", "(Lcom/budgettracker/ui/adapters/ExpenseAdapter;Lcom/budgettracker/databinding/ItemExpenseBinding;)V", "bind", "", "expense", "Lcom/budgettracker/data/entities/ExpenseEntry;", "app_debug"})
    public final class ViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        @org.jetbrains.annotations.NotNull()
        private final com.budgettracker.databinding.ItemExpenseBinding binding = null;
        
        public ViewHolder(@org.jetbrains.annotations.NotNull()
        com.budgettracker.databinding.ItemExpenseBinding binding) {
            super(null);
        }
        
        public final void bind(@org.jetbrains.annotations.NotNull()
        com.budgettracker.data.entities.ExpenseEntry expense) {
        }
    }
}