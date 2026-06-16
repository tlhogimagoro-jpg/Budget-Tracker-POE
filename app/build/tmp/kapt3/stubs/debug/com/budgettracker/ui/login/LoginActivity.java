package com.budgettracker.ui.login;

/**
 * LoginActivity — entry point of the app.
 * Validates credentials against Room DB and starts a session on success.
 *
 * Uses ViewBinding to avoid boilerplate findViewById calls.
 * Reference: https://developer.android.com/topic/libraries/view-binding
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\t\u001a\u00020\nH\u0002J\b\u0010\u000b\u001a\u00020\nH\u0002J\u0012\u0010\f\u001a\u00020\n2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0014J\u0010\u0010\u000f\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\u0011H\u0002J\b\u0010\u0012\u001a\u00020\nH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0013"}, d2 = {"Lcom/budgettracker/ui/login/LoginActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "TAG", "", "binding", "Lcom/budgettracker/databinding/ActivityLoginBinding;", "sessionManager", "Lcom/budgettracker/utils/SessionManager;", "attemptLogin", "", "navigateToDashboard", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "setLoading", "loading", "", "setupClickListeners", "app_debug"})
public final class LoginActivity extends androidx.appcompat.app.AppCompatActivity {
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String TAG = "LoginActivity";
    private com.budgettracker.databinding.ActivityLoginBinding binding;
    private com.budgettracker.utils.SessionManager sessionManager;
    
    public LoginActivity() {
        super();
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void setupClickListeners() {
    }
    
    /**
     * Reads form inputs, validates them, queries the DB, and either
     * starts a session or shows an error message.
     */
    private final void attemptLogin() {
    }
    
    private final void setLoading(boolean loading) {
    }
    
    /**
     * Navigate to Dashboard and clear back stack so user can't go back to Login.
     */
    private final void navigateToDashboard() {
    }
}