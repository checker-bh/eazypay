package com.example.eazypay.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;
import com.example.eazypay.database.Converters;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

@Entity(tableName = "photos")
@TypeConverters(Converters.class)  // ✅ Ensure Room can store PhotoSource as JSON
public class Photo implements Serializable {
    @PrimaryKey
    private int id;

    private int width;
    private int height;
    private String url;
    private String photographer;

    @SerializedName("photographer_url")
    private String photographerUrl;

    @SerializedName("photographer_id")
    private int photographerId;

    @SerializedName("avg_color")
    private String avgColor;

    @SerializedName("src")  // ✅ Matches API JSON field
    private PhotoSource source;

    private boolean liked;
    private String alt;

    // Constructor
    public Photo(int id, int width, int height, String url, String photographer,
                 String photographerUrl, int photographerId, String avgColor,
                 PhotoSource source, boolean liked, String alt) {
        this.id = id;
        this.width = width;
        this.height = height;
        this.url = url;
        this.photographer = photographer;
        this.photographerUrl = photographerUrl;
        this.photographerId = photographerId;
        this.avgColor = avgColor;
        this.source = source;
        this.liked = liked;
        this.alt = alt;
    }

    // Getters & Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getWidth() { return width; }
    public void setWidth(int width) { this.width = width; }

    public int getHeight() { return height; }
    public void setHeight(int height) { this.height = height; }

    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }

    public String getPhotographer() { return photographer; }
    public void setPhotographer(String photographer) { this.photographer = photographer; }

    public String getPhotographerUrl() { return photographerUrl; }
    public void setPhotographerUrl(String photographerUrl) { this.photographerUrl = photographerUrl; }

    public int getPhotographerId() { return photographerId; }
    public void setPhotographerId(int photographerId) { this.photographerId = photographerId; }

    public String getAvgColor() { return avgColor; }
    public void setAvgColor(String avgColor) { this.avgColor = avgColor; }

    public PhotoSource getSource() { return source; }
    public void setSource(PhotoSource source) { this.source = source; }

    public boolean isLiked() { return liked; }
    public void setLiked(boolean liked) { this.liked = liked; }

    public String getAlt() { return alt; }
    public void setAlt(String alt) { this.alt = alt; }
}
