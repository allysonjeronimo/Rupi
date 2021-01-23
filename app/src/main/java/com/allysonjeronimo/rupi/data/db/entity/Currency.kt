package com.allysonjeronimo.rupi.data.db.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.allysonjeronimo.rupi.extensions.currencyFormat
import com.allysonjeronimo.rupi.extensions.currencyFormatWithoutSymbol
import java.util.*

@Entity
data class Currency(
    @PrimaryKey
    var id:String,
    var code: String,
    var name: String,
    @Embedded(prefix = "last_")
    var lastPrice:PriceData
) {

    @Ignore
    lateinit var prices:List<Price>

    fun defaultValue() : String{
        return 1.0.currencyFormatWithoutSymbol()
    }

    fun quotation() : String{
        return lastPrice.sell.currencyFormat(Locale("pt", "BR"), java.util.Currency.getInstance("BRL"))
    }

    fun icon() : String{
        return "ic_${code?.toLowerCase(Locale.ROOT)}"
    }

    fun variation() : String{
        return "${lastPrice.pctChange}%"
    }
}