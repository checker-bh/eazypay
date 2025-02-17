package com.example.eazypay.models;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class PixabayResponse {
    @SerializedName("total")
    private int total;
    
    @SerializedName("totalHits")
    private int totalHits;
    
    @SerializedName("hits")
    private List<PixabayImage> images;

    public List<PixabayImage> getImages() {
        return images;
    }
}
