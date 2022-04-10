package com.example.kiotapp.ui.adapters

import android.view.View
import com.example.kiotapp.R
import com.example.kiotapp.data.model.OrderInformation
import com.example.kiotapp.databinding.ItemReviewOrderBinding

class ReviewOrderAdapters : BaseRecyclerViewAdapter<OrderInformation, ItemReviewOrderBinding>() {
    override fun getLayout(): Int {
        return R.layout.item_review_order
    }

    override fun onBindViewHolder(holder: BaseViewHolder<ItemReviewOrderBinding>, position: Int) {
        holder.binding.apply {
            btnEdit.setOnClickListener { listener?.invoke(btnEdit, items[position], position) }
            order = items[position]
        }
    }

    fun setOnButtonClickListener(mListener: ((view: View, item: OrderInformation, position: Int) -> Unit)) {
        listener = mListener
    }
}