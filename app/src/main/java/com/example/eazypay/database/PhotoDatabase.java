package com.example.eazypay.database;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import com.example.eazypay.models.Photo;
import com.example.eazypay.database.dao.PhotoDao;

@Database(entities = {Photo.class}, version = 2, exportSchema = false)
public abstract class PhotoDatabase extends RoomDatabase {
    private static PhotoDatabase instance;

    public abstract PhotoDao photoDao();

    public static synchronized PhotoDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            PhotoDatabase.class, "photo_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
