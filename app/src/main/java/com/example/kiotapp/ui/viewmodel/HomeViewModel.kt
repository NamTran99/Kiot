package com.example.kiotapp.ui.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.kiotapp.data.ItemMenuAction
import com.example.kiotapp.data.model.Order
import com.example.kiotapp.data.model.OrderGeneral
import com.example.kiotapp.data.model.Product
import com.example.kiotapp.data.repository.IRepository
import com.example.kiotapp.data.repository.Repository
import com.example.kiotapp.utils.Const
import com.example.kiotapp.utils.combine
import com.sangtb.androidlibrary.base.data.DataDialog

class HomeViewModel(application: Application) : AndroidViewModel(application),
    ItemMenuAction<Product> {
    private val repository: IRepository = Repository.getInstance()

    private var _typeTab = MutableLiveData(Const.TAB1)
    var myOrderGeneral = MutableLiveData(OrderGeneral())

    val currentOrderSelected = MutableLiveData(Order())

    private var idBill = -1
    val listOrder = MutableLiveData(listOf<Order>())

    private val _currentBill = OrderGeneral()
    val currentBillLive = MutableLiveData(_currentBill)

    val abc = MutableLiveData(DataDialog())

    val allProduct = repository.allProduct.combine(_typeTab) { allProduct, typeTab ->
        if (typeTab == Const.TAB1) {
            allProduct
        } else {
            allProduct.filter {
                it.type == typeTab
            }
        }
    }

    fun onClickProduct(item: Product) {
        currentOrderSelected.value = listOrder.value?.find {
            it.idProduct == item.productId
        }?: Order(
            idBill = checkBillCreated(),
            idProduct = item.productId,
            count = 0
        )
    }

    fun onPlus(){
        currentOrderSelected.value?.apply {
            count = ++count
        }
    }

    fun onMinus(){
        currentOrderSelected.value?.apply {
            count = --count
        }
    }

    fun onAddToBill(){
//        listOrder.value?.let{
//            it.
//        }
    }

    private fun checkBillCreated(): Int {
        if (idBill == -1) {
            idBill = (0..1000).random()
        }
        return idBill
    }

    override fun onClickItem(data: Product) {
        super.onClickItem(data)
        Log.d(TAG, "onClickItem: $data")
    }

    override fun onClickTab(idTab: Int) {
        Log.d(TAG, "onClickTab: $idTab")
        _typeTab.postValue(idTab)
    }

    companion object {
        private const val TAG = "HomeViewModel"
    }
}