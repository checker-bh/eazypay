package com.example.eazypay.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import com.example.eazypay.api.ApiService;

public class RetrofitClient {
    private static Retrofit retrofit = null;

    public static ApiService getPexelsService() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl("https://api.pexels.com/v1/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(ApiService.class);  // ✅ Ensure this matches ApiService.java
    }
}
