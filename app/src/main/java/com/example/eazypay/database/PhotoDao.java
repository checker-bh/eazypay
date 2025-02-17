package com.example.eazypay.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Delete;
import com.example.eazypay.models.Photo;
import java.util.List;

@Dao
public interface PhotoDao {
    @Insert
    void insertAll(List<Photo> photos);

    @Query("SELECT * FROM photos")
    List<Photo> getAllPhotos();

    @Query("DELETE FROM photos")
    void deleteAll();
}
