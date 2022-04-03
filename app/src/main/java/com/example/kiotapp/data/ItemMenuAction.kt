package com.example.kiotapp.data

interface ItemMenuAction<T : Product> {
    fun onClickTab(idTab : Int){

    }

    fun onClickItem(data : T){

    }
}