package com.allysonjeronimo.rupi.extensions

import java.text.NumberFormat
import java.util.*

fun Double.currencyFormat(locale: Locale = Locale("pt", "BR")) : String {
    val formatter = NumberFormat.getCurrencyInstance(locale)
    return formatter.format(this)
}