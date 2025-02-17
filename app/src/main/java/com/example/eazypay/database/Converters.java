package com.example.eazypay.database;

import androidx.room.TypeConverter;
import com.example.eazypay.models.PhotoSource;
import com.example.eazypay.models.Image;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;

public class Converters {
    private static final Gson gson = new Gson();

    // Convert PhotoSource object to JSON string
    @TypeConverter
    public static String fromPhotoSource(PhotoSource source) {
        return source == null ? null : gson.toJson(source);
    }

    // Convert JSON string back to PhotoSource object
    @TypeConverter
    public static PhotoSource toPhotoSource(String json) {
        if (json == null) {
            return null;
        }
        Type type = new TypeToken<PhotoSource>() {}.getType();
        return gson.fromJson(json, type);
    }

    //  Convert ImageSrc object to JSON string
    @TypeConverter
    public static String fromImageSrc(Image.ImageSrc src) {
        return src == null ? null : gson.toJson(src);
    }

    //  Convert JSON string back to ImageSrc object
    @TypeConverter
    public static Image.ImageSrc toImageSrc(String json) {
        if (json == null) {
            return null;
        }
        Type type = new TypeToken<Image.ImageSrc>() {}.getType();
        return gson.fromJson(json, type);
    }
}
