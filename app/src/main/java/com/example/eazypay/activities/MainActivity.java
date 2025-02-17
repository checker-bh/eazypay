package com.example.eazypay.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.example.eazypay.R;
import com.example.eazypay.adapters.ImageAdapter;
import com.example.eazypay.models.Photo;
import com.example.eazypay.repository.ImageRepository;
import com.example.eazypay.network.RetrofitClient;
import com.example.eazypay.utils.SessionManager;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ImageAdapter adapter;
    private ImageRepository imageRepository;
    private SwipeRefreshLayout swipeRefresh;
    private SessionManager sessionManager;
    private int currentPage = 1;
    private boolean isLoading = false;
    private static final String API_KEY = "2DfpZvFmF5WgqG98kl1SAqGxFsWhmApjnEjQOyZWq1wqQRBUW2umzTJy";  // ✅ Ensure API key is included

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize SessionManager
        sessionManager = new SessionManager(this);

        // Check if the user is logged in
        if (!sessionManager.isLoggedIn()) {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
            return;
        }

        // Initialize views and repository
        setupViews();
        setupRepository();

        // Load images
        loadImages();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            startActivity(new Intent(this, SettingsActivity.class));
            return true;
        } else if (id == R.id.action_logout) {
            logout();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void logout() {
        sessionManager.logout();
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

    private void setupViews() {
        swipeRefresh = findViewById(R.id.swipeRefresh);
        swipeRefresh.setOnRefreshListener(this::refreshImages);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        adapter = new ImageAdapter();
        recyclerView.setAdapter(adapter);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (!isLoading && !recyclerView.canScrollVertically(1)) {
                    loadImages();
                }
            }
        });
    }

    private void setupRepository() {
        imageRepository = new ImageRepository();
    }

    private void refreshImages() {
        currentPage = 1;
        adapter.clearPhotos();
        loadImages();
    }

    private void loadImages() {
        if (isLoading) return;
        isLoading = true;
        swipeRefresh.setRefreshing(true);

        imageRepository.fetchImages("nature", currentPage, 10, new ImageRepository.ImageCallback() {
            @Override
            public void onSuccess(List<Photo> photos) {
                runOnUiThread(() -> {
                    adapter.addPhotos(photos);
                    currentPage++;
                    isLoading = false;
                    swipeRefresh.setRefreshing(false);
                });
            }

            @Override
            public void onError(String message) {
                runOnUiThread(() -> {
                    Toast.makeText(MainActivity.this, "Error: " + message, Toast.LENGTH_SHORT).show();
                    isLoading = false;
                    swipeRefresh.setRefreshing(false);
                });
            }
        });
    }
}
