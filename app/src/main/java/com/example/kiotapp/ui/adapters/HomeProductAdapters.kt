package com.example.kiotapp.ui.adapters

import android.annotation.SuppressLint
import com.example.kiotapp.R
import com.example.kiotapp.data.model.Product
import com.example.kiotapp.databinding.ItemProductHomeFragmentBinding
import com.example.kiotapp.utils.BaseRecyclerViewAdapter
import com.example.kiotapp.utils.filterColorWhenTouch
import com.example.kiotapp.utils.startAnimationWhenTouch

class HomeProductAdapters : BaseRecyclerViewAdapter<Product, ItemProductHomeFragmentBinding>() {
    override fun getLayout(): Int {
        return R.layout.item_product_home_fragment
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onBindViewHolder(
        holder: BaseViewHolder<ItemProductHomeFragmentBinding>,
        position: Int
    ) {
        holder.binding.apply {
            imageView.filterColorWhenTouch(R.color.color_77000000)
            imageView.startAnimationWhenTouch()
            product = items[position]
        }
    }
}