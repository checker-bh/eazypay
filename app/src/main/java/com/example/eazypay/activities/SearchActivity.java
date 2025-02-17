package com.example.eazypay.activities;

import android.os.Bundle;
import android.view.Menu;
import android.widget.SearchView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.eazypay.R;
import com.example.eazypay.adapters.ImageAdapter;
import com.example.eazypay.repository.ImageRepository;
import com.example.eazypay.network.RetrofitClient;

public class SearchActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ImageAdapter adapter;
    private ImageRepository imageRepository;
    private String currentQuery = "";
    private int currentPage = 1;
    private boolean isLoading = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        setupViews();
        setupRepository();
    }

    private void setupViews() {
        recyclerView = findViewById(R.id.searchRecyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        adapter = new ImageAdapter();
        recyclerView.setAdapter(adapter);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (!isLoading && !recyclerView.canScrollVertically(1)) {
                    searchImages();
                }
            }
        });
    }

    private void setupRepository() {
        imageRepository = new ImageRepository();  // ✅ Fixed constructor call
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu, menu);
        SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                currentQuery = query;
                currentPage = 1;
                adapter.clearPhotos();
                searchImages();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return true;
    }

    private void searchImages() {
        if (isLoading || currentQuery.isEmpty()) return;
        isLoading = true;
    }
}
