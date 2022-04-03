package com.example.kiotapp.utils

import android.annotation.SuppressLint
import android.util.Log
import android.view.MotionEvent
import android.view.animation.AlphaAnimation
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.core.graphics.BlendModeColorFilterCompat
import androidx.core.graphics.BlendModeCompat
import androidx.databinding.BindingAdapter
import com.example.kiotapp.R
import com.squareup.picasso.Picasso

@SuppressLint("ClickableViewAccessibility")
fun ImageView.filterColor(color: Int) {
    this.setOnTouchListener { view, motionEvent ->
        val imageView = (view as? ImageView) ?: return@setOnTouchListener false
        when (motionEvent?.action) {
            MotionEvent.ACTION_DOWN -> {
                imageView.drawable.colorFilter =
                    BlendModeColorFilterCompat.createBlendModeColorFilterCompat(
                        ContextCompat.getColor(context, color),
                        BlendModeCompat.SRC_ATOP
                    )
                imageView.invalidate()
            }
            MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                imageView.drawable.clearColorFilter()
                imageView.invalidate()
            }
        }
        return@setOnTouchListener true
    }
}

@SuppressLint("ClickableViewAccessibility")
fun LinearLayout.opacityView() {
    val anim = AlphaAnimation(0.5f, 1.0f).apply {
        duration = 1000
    }
    this.setOnTouchListener { view, motionEvent ->
        when (motionEvent.action) {
            MotionEvent.ACTION_DOWN -> {
                view.startAnimation(anim)
            }
        }
        false
    }
}

@BindingAdapter("app:url")
fun ImageView.loadImage(url: String?) {
    if (url.isNullOrEmpty()) return
    Log.e("ImageView", "loadImage: $url")
    Picasso.get().isLoggingEnabled = true
    Picasso.get()
        .load(url)
        .placeholder(R.drawable.ic_menu)
        .into(this)
}