package com.allysonjeronimo.rupi.model.entity

import java.util.*

data class Currency (
    var id:Long = 0,
    var code:String? = null,
    var name:String? = null,
    var high:Double = 0.0,
    var low:Double = 0.0,
    var sell:Double = 0.0,
    var buy:Double = 0.0,
    var date: Date = Date()
)