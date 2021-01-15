package com.allysonjeronimo.rupi.data.db.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Price(
    @PrimaryKey(autoGenerate = true)
    var id:Long = 0L,
    @Embedded
    var price:PriceData,
    var currencyId:String? = null
)