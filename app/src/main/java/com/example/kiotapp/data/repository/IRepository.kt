package com.example.kiotapp.data.repository

import androidx.lifecycle.LiveData
import com.example.kiotapp.data.model.Bill
import com.example.kiotapp.data.model.Order
import com.example.kiotapp.data.model.Product
import kotlinx.coroutines.flow.Flow

interface IRepository {
    fun getAllProduct()
    fun getAllUrlImage()
    fun saveOrderToRemote(userInformation: List<Order>)
    fun saveBillToRemote(bill: Bill)
    val allProductLive:LiveData<List<Product>>
    val allProduct: List<Product>
    val mapLiveImage:LiveData<Map<Int, String>>
    suspend fun getUrlImage(image:String): Flow<String>
}