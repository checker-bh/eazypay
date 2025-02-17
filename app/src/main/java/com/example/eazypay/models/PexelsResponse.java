package com.example.eazypay.models;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class PexelsResponse {
    @SerializedName("photos")
    private List<Photo> photos;  // ✅ Ensure this is List<Photo>

    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }
}

