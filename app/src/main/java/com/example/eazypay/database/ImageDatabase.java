package com.example.eazypay.database;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import com.example.eazypay.models.Image;

@Database(entities = {Image.class}, version = 1, exportSchema = false)
@TypeConverters(Converters.class)
public abstract class ImageDatabase extends RoomDatabase {
    private static ImageDatabase instance;
    public abstract ImageDao imageDao();

    public static synchronized ImageDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            ImageDatabase.class, "image_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
