package com.sport.matchesinfo.utils

import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide.with

/**
 * Custom binding adapter
 */
@BindingAdapter("app:image_url")
fun loadImage(imageView: AppCompatImageView, url: String) {
    with(imageView.context).load(url).circleCrop().into(imageView)
}