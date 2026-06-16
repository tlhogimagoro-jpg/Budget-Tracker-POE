package com.budgettracker.ui.adapters;

/**
 * CategoryTotalAdapter — shows per-category spending totals on the Reports screen.
 * Each row includes a color dot, category name, amount, progress bar, and percentage.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 \u000e2\u0012\u0012\u0004\u0012\u00020\u0002\u0012\b\u0012\u00060\u0003R\u00020\u00000\u0001:\u0003\u000e\u000f\u0010B\u0005\u00a2\u0006\u0002\u0010\u0004J\u001c\u0010\u0005\u001a\u00020\u00062\n\u0010\u0007\u001a\u00060\u0003R\u00020\u00002\u0006\u0010\b\u001a\u00020\tH\u0016J\u001c\u0010\n\u001a\u00060\u0003R\u00020\u00002\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\tH\u0016\u00a8\u0006\u0011"}, d2 = {"Lcom/budgettracker/ui/adapters/CategoryTotalAdapter;", "Landroidx/recyclerview/widget/ListAdapter;", "Lcom/budgettracker/ui/adapters/CategoryTotalAdapter$Row;", "Lcom/budgettracker/ui/adapters/CategoryTotalAdapter$ViewHolder;", "()V", "onBindViewHolder", "", "holder", "position", "", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "Companion", "Row", "ViewHolder", "app_debug"})
public final class CategoryTotalAdapter extends androidx.recyclerview.widget.ListAdapter<com.budgettracker.ui.adapters.CategoryTotalAdapter.Row, com.budgettracker.ui.adapters.CategoryTotalAdapter.ViewHolder> {
    @org.jetbrains.annotations.NotNull()
    private static final androidx.recyclerview.widget.DiffUtil.ItemCallback<com.budgettracker.ui.adapters.CategoryTotalAdapter.Row> DIFF_CALLBACK = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.budgettracker.ui.adapters.CategoryTotalAdapter.Companion Companion = null;
    
    public CategoryTotalAdapter() {
        super(null);
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public com.budgettracker.ui.adapters.CategoryTotalAdapter.ViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override()
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
    com.budgettracker.ui.adapters.CategoryTotalAdapter.ViewHolder holder, int position) {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0006"}, d2 = {"Lcom/budgettracker/ui/adapters/CategoryTotalAdapter$Companion;", "", "()V", "DIFF_CALLBACK", "Landroidx/recyclerview/widget/DiffUtil$ItemCallback;", "Lcom/budgettracker/ui/adapters/CategoryTotalAdapter$Row;", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
    
    /**
     * Data class representing one row in the report.
     */
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\b\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u00a2\u0006\u0002\u0010\tJ\t\u0010\u0011\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0012\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0013\u001a\u00020\u0006H\u00c6\u0003J\t\u0010\u0014\u001a\u00020\bH\u00c6\u0003J1\u0010\u0015\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\bH\u00c6\u0001J\u0013\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0019\u001a\u00020\bH\u00d6\u0001J\t\u0010\u001a\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0011\u0010\u0007\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010\u00a8\u0006\u001b"}, d2 = {"Lcom/budgettracker/ui/adapters/CategoryTotalAdapter$Row;", "", "categoryName", "", "colorHex", "total", "", "sharePercent", "", "(Ljava/lang/String;Ljava/lang/String;DI)V", "getCategoryName", "()Ljava/lang/String;", "getColorHex", "getSharePercent", "()I", "getTotal", "()D", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "toString", "app_debug"})
    public static final class Row {
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String categoryName = null;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String colorHex = null;
        private final double total = 0.0;
        private final int sharePercent = 0;
        
        public Row(@org.jetbrains.annotations.NotNull()
        java.lang.String categoryName, @org.jetbrains.annotations.NotNull()
        java.lang.String colorHex, double total, int sharePercent) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getCategoryName() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getColorHex() {
            return null;
        }
        
        public final double getTotal() {
            return 0.0;
        }
        
        public final int getSharePercent() {
            return 0;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component2() {
            return null;
        }
        
        public final double component3() {
            return 0.0;
        }
        
        public final int component4() {
            return 0;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.budgettracker.ui.adapters.CategoryTotalAdapter.Row copy(@org.jetbrains.annotations.NotNull()
        java.lang.String categoryName, @org.jetbrains.annotations.NotNull()
        java.lang.String colorHex, double total, int sharePercent) {
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
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\t"}, d2 = {"Lcom/budgettracker/ui/adapters/CategoryTotalAdapter$ViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "Lcom/budgettracker/databinding/ItemCategoryTotalBinding;", "(Lcom/budgettracker/ui/adapters/CategoryTotalAdapter;Lcom/budgettracker/databinding/ItemCategoryTotalBinding;)V", "bind", "", "row", "Lcom/budgettracker/ui/adapters/CategoryTotalAdapter$Row;", "app_debug"})
    public final class ViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        @org.jetbrains.annotations.NotNull()
        private final com.budgettracker.databinding.ItemCategoryTotalBinding binding = null;
        
        public ViewHolder(@org.jetbrains.annotations.NotNull()
        com.budgettracker.databinding.ItemCategoryTotalBinding binding) {
            super(null);
        }
        
        public final void bind(@org.jetbrains.annotations.NotNull()
        com.budgettracker.ui.adapters.CategoryTotalAdapter.Row row) {
        }
    }
}