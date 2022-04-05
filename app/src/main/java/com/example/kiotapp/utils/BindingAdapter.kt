package com.example.kiotapp.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter

@BindingAdapter("app:src")
fun updateSrc(imageView: ImageView, imageId : Int){
    imageView.setBackgroundResource(imageId)
}