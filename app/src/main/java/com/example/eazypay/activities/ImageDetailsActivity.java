//package com.example.eazypay.activities;
//
//import android.os.Bundle;
//import android.widget.ImageView;
//import android.widget.TextView;
//import android.widget.Toast;
//import androidx.appcompat.app.AppCompatActivity;
//import com.bumptech.glide.Glide;
//import com.example.eazypay.R;
//import com.example.eazypay.models.Photo;
//
//public class ImageDetailsActivity extends AppCompatActivity {
//    public static final String EXTRA_PHOTO = "extra_photo";
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_image_details);
//
//        // Enable back button in action bar
//        if (getSupportActionBar() != null) {
//            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        }
//
//        ImageView imageView = findViewById(R.id.fullImageView);
//        TextView photographerText = findViewById(R.id.photographerText);
//
//        Photo photo = getIntent().getParcelableExtra(EXTRA_PHOTO);
//        if (photo != null && photo.getSource() != null) {
//            Glide.with(this)
//                .load(photo.getSource().getLarge())
//                .into(imageView);
//            photographerText.setText(getString(R.string.photographer_credit, photo.getPhotographer()));
//        } else {
//            Toast.makeText(this, "Error loading image details", Toast.LENGTH_SHORT).show();
//            finish();
//        }
//    }
//
//    @Override
//    public boolean onSupportNavigateUp() {
//        onBackPressed();
//        return true;
//    }
//}

package com.example.eazypay.activities;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;
import com.example.eazypay.R;
import com.example.eazypay.models.Photo;

public class ImageDetailsActivity extends AppCompatActivity {
    public static final String EXTRA_PHOTO = "extra_photo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_details);

        // Enable back button in action bar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        ImageView imageView = findViewById(R.id.fullImageView);
        TextView photographerText = findViewById(R.id.photographerText);

        // 🔹 Fix: Retrieve Parcelable Photo object
        Photo photo = getIntent().getParcelableExtra(EXTRA_PHOTO);
        if (photo != null && photo.getSource() != null) {
            String imageUrl = photo.getSource().getLarge();
            if (imageUrl == null || imageUrl.isEmpty()) {
                imageUrl = photo.getSource().getMedium();
            }

            Glide.with(this)
                    .load(imageUrl)
                    .into(imageView);

            photographerText.setText(getString(R.string.photographer_credit, photo.getPhotographer()));
        } else {
            Toast.makeText(this, "Error loading image details", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
