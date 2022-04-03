package com.example.kiotapp.ui.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.kiotapp.data.ItemMenuAction
import com.example.kiotapp.data.KiotEntity
import com.example.kiotapp.utils.Const

class HomeViewModel(application: Application) : AndroidViewModel(application),
    ItemMenuAction<KiotEntity> {
    companion object {
        private const val TAG = "HomeViewModel"
    }

    private var _typeTab = MutableLiveData(Const.TAB1)
    private val _listItem = MutableLiveData(
        listOf(
            KiotEntity(name = "SangTB"),
            KiotEntity(name = "SangTB"),
            KiotEntity(name = "SangTB"),
            KiotEntity(name = "SangTB"),
            KiotEntity(name = "SangTB",type = Const.TAB2),
            KiotEntity(name = "SangTB",type = Const.TAB2),
            KiotEntity(name = "SangTB",type = Const.TAB2),
            KiotEntity(name = "SangTB",type = Const.TAB2),
            KiotEntity(name = "SangTB",type = Const.TAB3),
            KiotEntity(name = "SangTB",type = Const.TAB3),
            KiotEntity(name = "SangTB",type = Const.TAB3),
            KiotEntity(name = "SangTB",type = Const.TAB3),
            KiotEntity(name = "SangTB",type = Const.TAB4),
            KiotEntity(name = "SangTB",type = Const.TAB4),
            KiotEntity(name = "SangTB",type = Const.TAB4),
            KiotEntity(name = "SangTB",type = Const.TAB4),
        )
    )

    override fun onClickItem(data: KiotEntity) {
        super.onClickItem(data)
        Log.d(TAG, "onClickItem: $data")
    }

    override fun onClickTab(idTab: Int) {
        Log.d(TAG, "onClickTab: $idTab")
        _typeTab.postValue(idTab)
    }
}