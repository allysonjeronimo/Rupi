package com.allysonjeronimo.rupi.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Price(
    @PrimaryKey(autoGenerate = true)
    var id:Long = 0L,
    var high:Double,
    var low:Double,
    @ColumnInfo(name="pct_change")
    var pctChange:Double,
    var sell:Double,
    var buy:Double,
    var date: Date,
    var currencyId:String? = null
)