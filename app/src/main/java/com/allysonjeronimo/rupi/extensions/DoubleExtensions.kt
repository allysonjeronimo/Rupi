package com.allysonjeronimo.rupi.extensions

import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*

fun Double.currencyFormat(
    locale: Locale = Locale("pt", "BR"),
    currency:Currency = Currency.getInstance("USD")) : String {
    val formatter = NumberFormat.getCurrencyInstance(locale)
    formatter.currency = currency
    return formatter.format(this)
}

fun Double.currencyFormatWithoutSymbol(
    locale: Locale = Locale("pt", "BR")
) : String{
    val numberFormat = NumberFormat.getNumberInstance(locale)
    val decimalFormat = numberFormat as DecimalFormat
    decimalFormat.applyPattern("###,###.###")
    return numberFormat.format(this)
}
