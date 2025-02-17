package com.example.eazypay.models;

import com.google.gson.annotations.SerializedName;

public class PixabayImage {
    @SerializedName("id")
    private int id;
    
    @SerializedName("webformatURL")
    private String imageUrl;
    
    @SerializedName("user")
    private String photographer;
    
    @SerializedName("largeImageURL")
    private String largeImageUrl;

    public int getId() { return id; }
    public String getImageUrl() { return imageUrl; }
    public String getPhotographer() { return photographer; }
    public String getLargeImageUrl() { return largeImageUrl; }
}
