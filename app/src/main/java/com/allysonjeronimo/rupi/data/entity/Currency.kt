package com.allysonjeronimo.rupi.data.entity

import com.allysonjeronimo.rupi.extensions.currencyFormat
import java.util.*
import java.util.Currency

data class Currency (
    var id:Long = 0,
    var code:String? = null,
    var name:String? = null,
    var high:Double = 0.0,
    var low:Double = 0.0,
    var pctChange:Double = 0.0,
    var sell:Double = 0.0,
    var buy:Double = 0.0,
    var date: Date = Date(),
    var locale:Locale = Locale.getDefault()
){

    fun defaultValue() : String{
        return 1.0.currencyFormat(this.locale, Currency.getInstance(code))
    }

    fun quotation() : String{
        return sell.currencyFormat(Locale("pt", "BR"), Currency.getInstance("BRL"))
    }

    fun icon() : String{
        return "ic_${code?.toLowerCase(Locale.ROOT)}"
    }

    fun variation() : String{
        return "${pctChange}%"
    }
}