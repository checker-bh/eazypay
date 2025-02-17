//package com.example.eazypay.models;
//
//import androidx.room.Entity;
//import androidx.room.PrimaryKey;
//
//@Entity(tableName = "images")
//public class Image {
//    @PrimaryKey(autoGenerate = true)
//    private int id;
//    private String url;
//    private String photographer;
//    private String description;
//
//    public Image(String url, String photographer, String description) {
//        this.url = url;
//        this.photographer = photographer;
//        this.description = description;
//    }
//
//    // Getters and Setters
//    public int getId() { return id; }
//    public void setId(int id) { this.id = id; }
//    public String getUrl() { return url; }
//    public void setUrl(String url) { this.url = url; }
//    public String getPhotographer() { return photographer; }
//    public void setPhotographer(String photographer) { this.photographer = photographer; }
//    public String getDescription() { return description; }
//    public void setDescription(String description) { this.description = description; }
//}

package com.example.eazypay.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;
import com.example.eazypay.database.Converters;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

@Entity(tableName = "images")
@TypeConverters(Converters.class)
public class Image implements Serializable {
    @PrimaryKey
    private int id;

    private String url;
    private String photographer;

    @SerializedName("src")  //  Matches API JSON field
    private ImageSrc src;

    // Constructor
    public Image(int id, String url, String photographer, ImageSrc src) {
        this.id = id;
        this.url = url;
        this.photographer = photographer;
        this.src = src;
    }

    // Getters & Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }

    public String getPhotographer() { return photographer; }
    public void setPhotographer(String photographer) { this.photographer = photographer; }

    public ImageSrc getSrc() { return src; }
    public void setSrc(ImageSrc src) { this.src = src; }

    public static class ImageSrc implements Serializable {
        private String original;
        private String large;
        private String medium;

        public String getOriginal() { return original; }
        public void setOriginal(String original) { this.original = original; }

        public String getLarge() { return large; }
        public void setLarge(String large) { this.large = large; }

        public String getMedium() { return medium; }
        public void setMedium(String medium) { this.medium = medium; }
    }
}
