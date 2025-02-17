package com.example.eazypay.utils;

import android.content.Context;
import android.content.Intent;
import androidx.core.content.FileProvider;
import java.io.File;

public class ImageSharer {
    public static void shareImage(Context context, File imageFile) {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("image/*");
        shareIntent.putExtra(Intent.EXTRA_STREAM, FileProvider.getUriForFile(context, context.getPackageName() + ".provider", imageFile));
        shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        context.startActivity(Intent.createChooser(shareIntent, "Share Image"));
    }}
