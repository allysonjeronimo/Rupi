package com.allysonjeronimo.rupi.extensions

import java.text.SimpleDateFormat
import java.util.*

const val FORMAT_TIME_HOURS_MINUTES = "hh:mm"

fun Date.toString(format:String = "yyyy-MM-dd hh:mm:ss") : String{
    val formatter = SimpleDateFormat(format)
    return formatter.format(this)
}