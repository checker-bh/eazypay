package com.example.eazypay.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.eazypay.R;
import com.example.eazypay.models.Photo;
import java.util.ArrayList;
import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> {
    private List<Photo> photos;
    private OnItemClickListener listener;

    public ImageAdapter() {
        this.photos = new ArrayList<>();
    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_image, parent, false);
        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ImageViewHolder holder, int position) {
        Photo photo = photos.get(position);
        Glide.with(holder.itemView.getContext())
                .load(photo.getSource().getMedium())
                .into(holder.imageView);
        holder.photographerText.setText(photo.getPhotographer());

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onItemClick(photo);
            }
        });
    }

    @Override
    public int getItemCount() {
        return photos.size();
    }

    public void addPhotos(List<Photo> newPhotos) {
        int startPosition = photos.size();
        photos.addAll(newPhotos);
        notifyItemRangeInserted(startPosition, newPhotos.size());
    }

    public void clearPhotos() {
        int size = photos.size();
        photos.clear();
        notifyItemRangeRemoved(0, size);
    }

    public interface OnItemClickListener {
        void onItemClick(Photo photo);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    static class ImageViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView photographerText;

        ImageViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            photographerText = itemView.findViewById(R.id.photographerText);
        }
    }
}

