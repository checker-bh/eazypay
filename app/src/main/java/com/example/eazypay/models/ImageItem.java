package com.example.eazypay_task.models;

import com.google.gson.annotations.SerializedName;

public class ImageItem {
    @SerializedName("id")
    private String id;

    @SerializedName("url")
    private String imageUrl;

    public ImageItem(String id, String imageUrl) {
        this.id = id;
        this.imageUrl = imageUrl;
    }

    public String getId() {
        return id;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
