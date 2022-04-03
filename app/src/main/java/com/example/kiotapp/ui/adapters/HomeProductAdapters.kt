package com.example.kiotapp.ui.adapters

import com.example.kiotapp.R
import com.example.kiotapp.data.Product
import com.example.kiotapp.databinding.ItemProductHomeFragmentBinding
import com.example.kiotapp.utils.BaseRecyclerViewAdapter
import com.example.kiotapp.utils.filterColor

class HomeProductAdapters : BaseRecyclerViewAdapter<Product, ItemProductHomeFragmentBinding>() {
    override fun getLayout(): Int {
       return R.layout.item_product_home_fragment
    }

    override fun onBindViewHolder(
        holder: BaseViewHolder<ItemProductHomeFragmentBinding>,
        position: Int
    ) {
        holder.binding.apply {
            imageView.setOnHoverListener { view, motionEvent ->
                imageView.filterColor(R.color.color_77000000)
                return@setOnHoverListener true
            }
            product = items[position]
        }
    }
}