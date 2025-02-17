package com.example.eazypay.repository;

import com.example.eazypay.api.AuthApiService;
import com.example.eazypay.database.AppDatabase;
import com.example.eazypay.models.LoginRequest;
import com.example.eazypay.models.LoginResponse;
import com.example.eazypay.models.User;
import com.example.eazypay.utils.SessionManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthRepository {
    private final AuthApiService authApiService;
    private final AppDatabase database;
    private final SessionManager sessionManager;

    public AuthRepository(AuthApiService authApiService, AppDatabase database, SessionManager sessionManager) {
        this.authApiService = authApiService;
        this.database = database;
        this.sessionManager = sessionManager;
    }

    public void login(String email, String password, AuthCallback callback) {
        authApiService.login(new LoginRequest(email, password))
            .enqueue(new Callback<LoginResponse>() {
                @Override
                public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        LoginResponse loginResponse = response.body();
                        sessionManager.saveAuthToken(loginResponse.getToken());
                        saveUserToLocalDb(loginResponse.getUser());
                        callback.onSuccess(loginResponse);
                    } else {
                        tryLocalLogin(email, password, callback);
                    }
                }

                @Override
                public void onFailure(Call<LoginResponse> call, Throwable t) {
                    tryLocalLogin(email, password, callback);
                }
            });
    }

    private void tryLocalLogin(String email, String password, AuthCallback callback) {
        new Thread(() -> {
            User user = database.userDao().login(email, password);
            if (user != null) {
                callback.onSuccess(new LoginResponse());
            } else {
                callback.onError("Invalid credentials");
            }
        }).start();
    }

    private void saveUserToLocalDb(User user) {
        new Thread(() -> database.userDao().insert(user)).start();
    }

    public interface AuthCallback {
        void onSuccess(LoginResponse response);
        void onError(String message);
    }
}
