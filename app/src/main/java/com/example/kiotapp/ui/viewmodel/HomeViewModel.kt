package com.example.kiotapp.ui.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.kiotapp.data.ItemMenuAction
import com.example.kiotapp.data.Product
import com.example.kiotapp.data.Repository
import com.example.kiotapp.utils.Const

class HomeViewModel(application: Application) : AndroidViewModel(application),
    ItemMenuAction<Product> {
    private val repository = Repository.getInstance()

    init {
        repository.getAllProduct()
    }

    val allProduct = repository.allProduct

    companion object {
        private const val TAG = "HomeViewModel"
    }

    private var _typeTab = MutableLiveData(Const.TAB1)
    private val _listItem = MutableLiveData(
        listOf(
            Product(name = "SangTB"),
            Product(name = "SangTB"),
            Product(name = "SangTB"),
            Product(name = "SangTB"),
            Product(name = "SangTB",type = Const.TAB2),
            Product(name = "SangTB",type = Const.TAB2),
            Product(name = "SangTB",type = Const.TAB2),
            Product(name = "SangTB",type = Const.TAB2),
            Product(name = "SangTB",type = Const.TAB3),
            Product(name = "SangTB",type = Const.TAB3),
            Product(name = "SangTB",type = Const.TAB3),
            Product(name = "SangTB",type = Const.TAB3),
            Product(name = "SangTB",type = Const.TAB4),
            Product(name = "SangTB",type = Const.TAB4),
            Product(name = "SangTB",type = Const.TAB4),
            Product(name = "SangTB",type = Const.TAB4),
        )
    )

    override fun onClickItem(data: Product) {
        super.onClickItem(data)
        Log.d(TAG, "onClickItem: $data")
    }

    override fun onClickTab(idTab: Int) {
        Log.d(TAG, "onClickTab: $idTab")
        _typeTab.postValue(idTab)
    }
}