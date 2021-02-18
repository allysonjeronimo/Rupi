package com.allysonjeronimo.rupi.extensions

import java.text.SimpleDateFormat
import java.util.*

fun String.toDate(format: String = "yyyy-MM-dd hh:mm:ss") : Date {
    val formatter = SimpleDateFormat(format)
    return formatter.parse(this)
}

fun String.currencySymbol() : String{
    val currency = Currency.getInstance(this)
    return currency.symbol
}