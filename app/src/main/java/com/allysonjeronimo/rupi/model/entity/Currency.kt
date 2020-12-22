package com.allysonjeronimo.rupi.model.entity

import com.allysonjeronimo.rupi.extensions.currencyFormat
import java.util.*

data class Currency (
    var id:Long = 0,
    var code:String? = null,
    var name:String? = null,
    var high:Double = 0.0,
    var low:Double = 0.0,
    var sell:Double = 0.0,
    var buy:Double = 0.0,
    var date: Date = Date(),
    var locale:Locale = Locale.getDefault()
){

    fun defaulValue() : String{
        return 1.0.currencyFormat(this.locale)
    }

    fun quotation() : String{
        return "${sell.currencyFormat(Locale("pt", "BR"))}"
    }
}