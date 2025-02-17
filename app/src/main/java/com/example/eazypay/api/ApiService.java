package com.example.eazypay.api;

import com.example.eazypay.models.PexelsResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface ApiService {
    @GET("search")
    Call<PexelsResponse> searchPhotos(
            @Header("Authorization") String apiKey,
            @Query("query") String query,
            @Query("page") int page,
            @Query("per_page") int perPage
    );
}
