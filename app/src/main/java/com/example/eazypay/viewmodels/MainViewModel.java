package com.example.eazypay.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.eazypay.models.Photo;
import com.example.eazypay.repository.ImageRepository;
import com.example.eazypay.utils.Constants;
import java.util.ArrayList;
import java.util.List;

public class MainViewModel extends ViewModel {
    private final ImageRepository imageRepository;
    private final MutableLiveData<Boolean> isLoading = new MutableLiveData<>();
    private final MutableLiveData<String> error = new MutableLiveData<>();
    private final MutableLiveData<List<Photo>> images = new MutableLiveData<>(new ArrayList<>());
    private int currentPage = 1;
    private boolean isLastPage = false;

    public MainViewModel() {
        this.imageRepository = new ImageRepository();
    }

    public void loadImages() {
        if (isLastPage) return;
        isLoading.setValue(true);
        imageRepository.fetchImages("nature", currentPage, Constants.PAGE_SIZE, new ImageRepository.ImageCallback() {
            @Override
            public void onSuccess(List<Photo> photos) {
                isLoading.postValue(false);
                if (photos.isEmpty()) {
                    isLastPage = true;
                } else {
                    currentPage++;
                    List<Photo> currentList = images.getValue();
                    if (currentList != null) {
                        currentList.addAll(photos);
                        images.postValue(currentList);
                    } else {
                        images.postValue(photos);
                    }
                }
            }

            @Override
            public void onError(String message) {
                isLoading.postValue(false);
                error.postValue(message);
            }
        });
    }

    public LiveData<Boolean> getIsLoading() { return isLoading; }
    public LiveData<String> getError() { return error; }
    public LiveData<List<Photo>> getImages() { return images; }
}
