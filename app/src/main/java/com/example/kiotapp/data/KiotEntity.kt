package com.example.kiotapp.data

import com.example.kiotapp.utils.Const

data class KiotEntity(
    var id: Int? = 0,
    var name: String? = "",
    var price: Double? = 0.0,
    var image : String? = "https://i0.wp.com/codewithvarun.com/wp-content/uploads/2022/01/clean-architecture-1.png?fit=856%2C385&ssl=1",
    var calo: Int? = 0,
    var type : Int? = Const.TAB1
)