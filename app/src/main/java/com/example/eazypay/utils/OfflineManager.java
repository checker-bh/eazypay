package com.example.eazypay.utils;

import android.content.Context;
import com.example.eazypay.database.AppDatabase;
import com.example.eazypay.models.Photo;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class OfflineManager {
    private final AppDatabase database;
    private final ExecutorService executor = Executors.newSingleThreadExecutor();

    public OfflineManager(Context context) {
        this.database = AppDatabase.getInstance(context);
    }

    public void savePhotos(List<Photo> photos) {
        executor.execute(() -> database.photoDao().insertAll(photos));
    }

    public List<Photo> getSavedPhotos() {
        return database.photoDao().getAllPhotos();
    }

    public void clearPhotos() {
        executor.execute(() -> database.photoDao().deleteAll());
    }
}
