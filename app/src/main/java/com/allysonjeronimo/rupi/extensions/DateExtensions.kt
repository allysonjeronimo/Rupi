package com.allysonjeronimo.rupi.extensions

import java.text.SimpleDateFormat
import java.util.*

const val FORMAT_TIME_HOURS_MINUTES = "HH:mm"

fun Date.toFormattedString(format:String = "yyyy-MM-dd hh:mm:ss") : String{
    val formatter = SimpleDateFormat(format)
    return formatter.format(this)
}