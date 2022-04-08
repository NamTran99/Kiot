package com.example.kiotapp.data.model

import com.example.kiotapp.utils.Const

data class Product(
    var productId: Int? = 0,
    var name: String? = "",
    var price: Float? = 0f,
    var image : String? = "https://i0.wp.com/codewithvarun.com/wp-content/uploads/2022/01/clean-architecture-1.png?fit=856%2C385&ssl=1",
    var calo: Int? = 0,
    var type : Int? = Const.TAB1
)