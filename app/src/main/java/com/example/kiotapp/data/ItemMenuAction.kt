package com.example.kiotapp.data

interface ItemMenuAction<T : KiotEntity> {
    fun onClickTab(idTab : Int){

    }

    fun onClickItem(data : T){

    }
}