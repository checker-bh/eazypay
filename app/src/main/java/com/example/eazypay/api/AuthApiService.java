package com.example.eazypay.api;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import com.example.eazypay.models.LoginRequest;
import com.example.eazypay.models.LoginResponse;

public interface AuthApiService {
    @POST("auth/login")
    Call<LoginResponse> login(@Body LoginRequest request);
}
