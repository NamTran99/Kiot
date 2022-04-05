package com.example.kiotapp.data.repository

import androidx.lifecycle.LiveData
import com.example.kiotapp.data.Product

interface IRepository {
    fun getAllProduct()
    val allProduct:LiveData<List<Product>>
}