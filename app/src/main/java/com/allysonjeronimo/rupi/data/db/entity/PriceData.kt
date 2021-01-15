package com.allysonjeronimo.rupi.data.db.entity


import androidx.room.ColumnInfo
import java.util.*

data class PriceData(
    var high:Double,
    var low:Double,
    @ColumnInfo(name = "pct_change")
    var pctChange:Double,
    var sell:Double,
    var buy:Double,
    var date: Date
)