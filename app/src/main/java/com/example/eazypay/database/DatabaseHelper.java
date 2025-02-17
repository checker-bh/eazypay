package com.example.eazypay.database;

import android.content.Context;
import android.os.AsyncTask;
import com.example.eazypay.models.User;

public class DatabaseHelper {
    private AppDatabase database;

    public DatabaseHelper(Context context) {
        database = AppDatabase.getInstance(context);
    }

    public interface DatabaseCallback {
        void onSuccess();
        void onError(String error);
    }

    // Register new user
    public void registerUser(final User user, final DatabaseCallback callback) {
        new AsyncTask<Void, Void, Boolean>() {
            @Override
            protected Boolean doInBackground(Void... voids) {
                try {
                    // Check if user already exists
                    User existingUser = database.userDao().getUserByEmail(user.getEmail());
                    if (existingUser != null) {
                        return false;
                    }
                    database.userDao().insert(user);
                    return true;
                } catch (Exception e) {
                    e.printStackTrace();
                    return false;
                }
            }

            @Override
            protected void onPostExecute(Boolean success) {
                if (success) {
                    callback.onSuccess();
                } else {
                    callback.onError("Registration failed. User might already exist.");
                }
            }
        }.execute();
    }

    // Login user
    public void loginUser(final String email, final String password, final DatabaseCallback callback) {
        new AsyncTask<Void, Void, User>() {
            @Override
            protected User doInBackground(Void... voids) {
                return database.userDao().login(email, password);
            }

            @Override
            protected void onPostExecute(User user) {
                if (user != null) {
                    callback.onSuccess();
                } else {
                    callback.onError("Invalid credentials");
                }
            }
        }.execute();
    }

    // Reset password
    public void resetPassword(final String email, final DatabaseCallback callback) {
        new AsyncTask<Void, Void, User>() {
            @Override
            protected User doInBackground(Void... voids) {
                return database.userDao().getUserByEmail(email);
            }

            @Override
            protected void onPostExecute(User user) {
                if (user != null) {
                    // In a real app, you would send a reset email here
                    callback.onSuccess();
                } else {
                    callback.onError("Email not found");
                }
            }
        }.execute();
    }
}