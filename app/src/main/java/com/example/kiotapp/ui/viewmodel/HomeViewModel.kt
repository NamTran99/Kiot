package com.example.kiotapp.ui.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.kiotapp.data.ItemMenuAction
import com.example.kiotapp.data.model.Product
import com.example.kiotapp.data.repository.IRepository
import com.example.kiotapp.data.repository.Repository
import com.example.kiotapp.utils.Const
import com.example.kiotapp.utils.combine

class HomeViewModel(application: Application) : AndroidViewModel(application),
    ItemMenuAction<Product> {
    private val repository: IRepository = Repository.getInstance()

    private var _typeTab = MutableLiveData(Const.TAB1)

    val allProduct = repository.allProduct.combine(_typeTab) { allProduct, typeTab ->
        if (typeTab == Const.TAB1) {
            allProduct
        } else {
            allProduct.filter {
                it.type == typeTab
            }
        }
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