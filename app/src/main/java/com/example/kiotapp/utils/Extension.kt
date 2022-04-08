package com.example.kiotapp.utils

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.core.graphics.BlendModeColorFilterCompat
import androidx.core.graphics.BlendModeCompat
import androidx.databinding.BindingAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.lifecycle.lifecycleScope
import com.example.kiotapp.R
import com.example.kiotapp.data.repository.IRepository
import com.example.kiotapp.data.repository.Repository
import com.example.kiotapp.ui.MainActivity
import com.google.firebase.storage.FirebaseStorage
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@SuppressLint("ClickableViewAccessibility")
fun ImageView.filterColorWhenTouch(color: Int) {
    this.setOnTouchListener { view, motionEvent ->
        val imageView = (view as? ImageView) ?: return@setOnTouchListener false
        when (motionEvent?.action) {
            MotionEvent.ACTION_DOWN -> {
                imageView.colorFilter =
                    BlendModeColorFilterCompat.createBlendModeColorFilterCompat(
                        ContextCompat.getColor(context, color),
                        BlendModeCompat.SRC_ATOP
                    )
                imageView.invalidate()
            }
            MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                imageView.clearColorFilter()
                imageView.invalidate()
            }
        }
        return@setOnTouchListener false
    }
}

@SuppressLint("ClickableViewAccessibility")
fun ImageView.startAnimationWhenTouch(anim: Animation? = null) {
    val mAnim = anim ?: AnimationUtils.loadAnimation(context, R.anim.image_anim)
    this.setOnTouchListener { view, motionEvent ->
        val image = (view as? ImageView) ?: return@setOnTouchListener false
        when (motionEvent?.action) {
            MotionEvent.ACTION_DOWN -> {
                image.startAnimation(mAnim)
            }
            MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                image.clearAnimation()
            }
        }
        return@setOnTouchListener false
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
    val fireStorage = FirebaseStorage.getInstance(FireBaseConst.FIRE_STORAGE_BUCKETS)
     val storageRef = fireStorage.reference

    storageRef.child(url).downloadUrl.addOnSuccessListener {
        Picasso.get().isLoggingEnabled = true
        Picasso.get()
            .load(it)
            .placeholder(R.drawable.ic_menu)
            .into(this)
    }.addOnFailureListener {
        Log.d("ImageAdapter", "loadImage: $it")
    }
}

fun Fragment.checkMotionEvent(motionEvent: MotionEvent): Boolean {
    return when (motionEvent?.action) {
        MotionEvent.ACTION_DOWN -> {
            true
        }
        else -> false
    }
}

fun Fragment.onNavigate(actionId: Int, bundle: Bundle? = null) {
    (activity as? MainActivity)?.onNavigate(actionId, bundle)
}

fun Fragment.onBackScreen() {
    (activity as? MainActivity)?.onBackStack()
}
