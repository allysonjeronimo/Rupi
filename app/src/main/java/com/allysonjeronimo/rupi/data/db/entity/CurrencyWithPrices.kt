package com.allysonjeronimo.rupi.data.db.entity

import androidx.room.Embedded
import androidx.room.Relation

data class CurrencyWithPrices(
    //@Embedded
    var currency:Currency,
    /*@Relation(
        parentColumn = "code",
        entityColumn = "currencyCode"
    )*/
    var prices:List<Price>
)