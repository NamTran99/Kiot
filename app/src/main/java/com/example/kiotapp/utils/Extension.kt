package com.example.kiotapp.utils

import android.annotation.SuppressLint
import android.graphics.PorterDuff
import android.view.MotionEvent
import android.view.animation.AlphaAnimation
import android.widget.ImageView
import android.widget.LinearLayout

@SuppressLint("ClickableViewAccessibility")
fun ImageView.filterColor() {
    this.setOnTouchListener { view, motionEvent ->
        val imageView = (view as? ImageView) ?: return@setOnTouchListener false
        when (motionEvent?.action) {
            MotionEvent.ACTION_DOWN -> {
                imageView.drawable.setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP)
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
fun LinearLayout.opacityView(){
    val anim = AlphaAnimation(0.5f, 1.0f).apply {
        duration = 1000
    }
    this.setOnTouchListener{view,motionEvent->
        when(motionEvent.action){
            MotionEvent.ACTION_DOWN ->{
                view.startAnimation(anim)
            }
        }
        false
    }
}
