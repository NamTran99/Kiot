package com.example.kiotapp.data.repository

import androidx.lifecycle.LiveData
import com.example.kiotapp.data.model.Product
import kotlinx.coroutines.flow.Flow

interface IRepository {
    fun getAllProduct()
    fun getAllUrlImage()
    val allProduct:LiveData<List<Product>>
    val mapLiveImage:LiveData<Map<Int, String>>
    suspend fun getUrlImage(image:String): Flow<String>
}