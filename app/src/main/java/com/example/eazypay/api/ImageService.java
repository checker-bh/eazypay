package com.example.eazypay.api;

import com.example.eazypay.models.PixabayResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ImageService {
    @GET("?")
    Call<PixabayResponse> getImages(
        @Query("key") String apiKey,
        @Query("page") int page,
        @Query("per_page") int perPage,
        @Query("safesearch") boolean safeSearch
    );
}
