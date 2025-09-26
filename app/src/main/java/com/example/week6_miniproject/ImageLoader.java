package com.example.week6_miniproject;

import android.widget.ImageView;

// Interface for loading images from URL to ImageView
public interface ImageLoader {
    void loadImage(String imageUrl, ImageView imageView);
}
