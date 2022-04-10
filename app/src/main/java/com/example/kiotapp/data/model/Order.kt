package com.example.kiotapp.data.model

import com.example.kiotapp.utils.getTimeZoneToFloat

data class Order(
    var idBill: Int = 0,
    var idProduct: Int = 0,
    var count: Int = 0,
    var time: Long = getTimeZoneToFloat()
)