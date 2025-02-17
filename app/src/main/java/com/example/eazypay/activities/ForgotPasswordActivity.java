// ForgotPasswordActivity.java
package com.example.eazypay.activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.eazypay.R;
import com.example.eazypay.database.AppDatabase;
import com.example.eazypay.models.User;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ForgotPasswordActivity extends AppCompatActivity {
    private EditText emailInput;
    private Button resetButton;
    private TextView backToLoginLink;
    private AppDatabase database;
    private ExecutorService executor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        // Initialize database and executor
        database = AppDatabase.getInstance(this);
        executor = Executors.newSingleThreadExecutor();

        // Initialize views
        emailInput = findViewById(R.id.emailInput);
        resetButton = findViewById(R.id.resetButton);
        backToLoginLink = findViewById(R.id.backToLoginLink);

        // Set click listeners
        resetButton.setOnClickListener(v -> attemptReset());
        backToLoginLink.setOnClickListener(v -> finish());
    }

    private void attemptReset() {
        String email = emailInput.getText().toString().trim();

        if (email.isEmpty()) {
            Toast.makeText(this, "Please enter your email", Toast.LENGTH_SHORT).show();
            return;
        }

        executor.execute(() -> {
            // Check if user exists in database
            User user = database.userDao().getUserByEmail(email);

            runOnUiThread(() -> {
                if (user != null) {
                    // In a real app, you would:
                    // 1. Generate a reset token
                    // 2. Send an email with reset instructions
                    // 3. Store the reset token in the database

                    Toast.makeText(ForgotPasswordActivity.this,
                            "Password reset instructions sent to your email",
                            Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(ForgotPasswordActivity.this,
                            "No account found with this email",
                            Toast.LENGTH_SHORT).show();
                }
            });
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        executor.shutdown();
    }
}