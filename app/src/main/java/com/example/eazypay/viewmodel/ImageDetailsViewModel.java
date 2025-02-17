package com.example.eazypay.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.eazypay.models.Photo;
import com.example.eazypay.utils.LoadingState;

public class ImageDetailsViewModel extends ViewModel {
    private final MutableLiveData<LoadingState<Photo>> photoState = new MutableLiveData<>();

    public void setPhoto(Photo photo) {
        photoState.setValue(LoadingState.success(photo));
    }

    public LiveData<LoadingState<Photo>> getPhotoState() {
        return photoState;
    }
}
