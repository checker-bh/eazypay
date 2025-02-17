package com.example.eazypay.api;

import com.example.eazypay.models.PexelsResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface PexelsApiService {
    @GET("curated")
    Call<PexelsResponse> getCuratedPhotos(
        @Header("Authorization") String apiKey,
        @Query("page") int page,
        @Query("per_page") int perPage
    );
}
