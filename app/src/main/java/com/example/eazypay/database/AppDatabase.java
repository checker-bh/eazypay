package com.example.eazypay.database;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import com.example.eazypay.models.Photo;
import com.example.eazypay.models.User;
import com.example.eazypay.database.dao.PhotoDao;
import com.example.eazypay.database.dao.UserDao;

@Database(entities = {User.class, Photo.class}, version = 2, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase instance;

    public abstract UserDao userDao();
    public abstract PhotoDao photoDao();

    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "eazypay_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
