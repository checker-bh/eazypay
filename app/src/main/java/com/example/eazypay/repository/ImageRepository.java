//package com.example.eazypay.repository;
//
//import com.example.eazypay.api.PexelsApiService;
//import com.example.eazypay.models.Photo;
//import com.example.eazypay.models.PexelsResponse;
//import com.example.eazypay.utils.Constants;
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//import java.util.List;
//
//public class ImageRepository {
//    private final PexelsApiService pexelsService;
//
//    public ImageRepository(PexelsApiService pexelsService) {
//        this.pexelsService = pexelsService;
//    }
//
//    public void getImages(int page, final ImageCallback callback) {
//        pexelsService.getCuratedPhotos(Constants.PEXELS_API_KEY, page, Constants.PAGE_SIZE)
//            .enqueue(new Callback<PexelsResponse>() {
//                @Override
//                public void onResponse(Call<PexelsResponse> call, Response<PexelsResponse> response) {
//                    if (response.isSuccessful() && response.body() != null) {
//                        callback.onSuccess(response.body().getPhotos());
//                    } else {
//                        callback.onError("Failed to load images");
//                    }
//                }
//
//                @Override
//                public void onFailure(Call<PexelsResponse> call, Throwable t) {
//                    callback.onError("Network error: " + t.getMessage());
//                }
//            });
//    }
//
//    public interface ImageCallback {
//        void onSuccess(List<Photo> photos);
//        void onError(String message);
//    }
//}
package com.example.eazypay.repository;

import android.util.Log;
import com.example.eazypay.models.PexelsResponse;
import com.example.eazypay.models.Photo;
import com.example.eazypay.api.ApiService;  // ✅ Ensure correct import
import com.example.eazypay.network.RetrofitClient;
import com.example.eazypay.utils.Constants;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import java.util.List;

public class ImageRepository {
    private final ApiService apiService;

    public interface ImageCallback {
        void onSuccess(List<Photo> photos);
        void onError(String errorMessage);
    }

    public ImageRepository() {
        this.apiService = RetrofitClient.getPexelsService();
    }

    public void fetchImages(String query, int page, int perPage, ImageCallback callback) {
        String apiKey = Constants.PEXELS_API_KEY;

        Log.d("ImageRepository", "Fetching images with query: " + query + ", page: " + page + ", perPage: " + perPage);

        apiService.searchPhotos(apiKey, query, page, perPage).enqueue(new Callback<PexelsResponse>() {
            @Override
            public void onResponse(Call<PexelsResponse> call, Response<PexelsResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body().getPhotos());
                } else {
                    callback.onError("Failed to fetch images. Response code: " + response.code());
                    Log.e("ImageRepository", "Response Code: " + response.code() + ", Error: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<PexelsResponse> call, Throwable t) {
                Log.e("ImageRepository", "API Error: " + t.getMessage());
                callback.onError("Error fetching images: " + t.getMessage());
            }
        });
    }
}
