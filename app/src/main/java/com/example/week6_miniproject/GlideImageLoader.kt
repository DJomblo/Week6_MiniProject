package com.example.week6_miniproject

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.week6_miniproject.R

// Implementation of ImageLoader using Glide for image loading
class GlideImageLoader(private val context: Context) : ImageLoader {
    override fun loadImage(imageUrl: String, imageView: ImageView) {
        Glide.with(context)
            .load(imageUrl)
            .placeholder(R.drawable.ic_image_placeholder) // shown while loading
            .error(R.drawable.ic_broken_image)            // shown if failed
            .centerCrop()
            .diskCacheStrategy(DiskCacheStrategy.ALL)     // caches for performance
            .into(imageView)
    }
}
