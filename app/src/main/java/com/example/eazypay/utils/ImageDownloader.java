package com.example.eazypay.utils;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Environment;
import androidx.annotation.NonNull;

public class ImageDownloader {
    private final Context context;

    public ImageDownloader(@NonNull Context context) {
        this.context = context;
    }

    public long downloadImage(String url, String title) {
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url))
            .setTitle(title)
            .setDescription("Downloading image...")
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            .setDestinationInExternalPublicDir(Environment.DIRECTORY_PICTURES, title + ".jpg");

        DownloadManager manager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
        return manager.enqueue(request);
    }
}
