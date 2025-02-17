package com.example.eazypay.activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.eazypay.R;
import com.example.eazypay.database.AppDatabase;
import com.example.eazypay.models.User;
import com.example.eazypay.security.SecurityUtils;
import com.google.android.material.textfield.TextInputLayout;

public class RegisterActivity extends AppCompatActivity {
    private TextInputLayout emailLayout;
    private TextInputLayout passwordLayout;
    private TextInputLayout nameLayout;
    private EditText emailInput;
    private EditText passwordInput;
    private EditText nameInput;
    private Button registerButton;
    private AppDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        
        database = AppDatabase.getInstance(this);
        initializeViews();
        setupClickListeners();
    }

    private void initializeViews() {
        emailLayout = findViewById(R.id.emailLayout);
        passwordLayout = findViewById(R.id.passwordLayout);
        nameLayout = findViewById(R.id.nameLayout);
        emailInput = findViewById(R.id.emailInput);
        passwordInput = findViewById(R.id.passwordInput);
        nameInput = findViewById(R.id.nameInput);
        registerButton = findViewById(R.id.registerButton);
    }

    private void setupClickListeners() {
        registerButton.setOnClickListener(v -> attemptRegistration());
    }

    private void attemptRegistration() {
        String email = emailInput.getText().toString().trim();
        String password = passwordInput.getText().toString().trim();
        String name = nameInput.getText().toString().trim();

        if (!validateInput(email, password, name)) {
            return;
        }

        try {
            String encryptedPassword = SecurityUtils.encrypt(password);
            new Thread(() -> {
                User existingUser = database.userDao().getUserByEmail(email);
                if (existingUser != null) {
                    runOnUiThread(() -> showError("Email already registered"));
                    return;
                }

                User user = new User(email, encryptedPassword, name);
                database.userDao().insert(user);  // Changed from registerUser to insert
                runOnUiThread(() -> {
                    Toast.makeText(this, "Registration successful!", Toast.LENGTH_SHORT).show();
                    finish();
                });
            }).start();
        } catch (Exception e) {
            showError("Error during registration: " + e.getMessage());
        }
    }

    private boolean validateInput(String email, String password, String name) {
        boolean isValid = true;

        if (!SecurityUtils.validateEmail(email)) {
            emailLayout.setError("Invalid email format");
            isValid = false;
        } else {
            emailLayout.setError(null);
        }

        if (!SecurityUtils.validatePassword(password)) {
            passwordLayout.setError("Password must be at least 6 characters");
            isValid = false;
        } else {
            passwordLayout.setError(null);
        }

        if (name.isEmpty()) {
            nameLayout.setError("Name is required");
            isValid = false;
        } else {
            nameLayout.setError(null);
        }

        return isValid;
    }

    private void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
