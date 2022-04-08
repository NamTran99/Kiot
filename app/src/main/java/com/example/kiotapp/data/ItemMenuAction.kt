package com.example.kiotapp.data

import com.example.kiotapp.data.model.Product

interface ItemMenuAction<T : Product> {
    fun onClickTab(idTab : Int){

    }

    fun onClickItem(data : T){

    }
}