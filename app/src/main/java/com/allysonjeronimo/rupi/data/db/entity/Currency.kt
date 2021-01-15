package com.allysonjeronimo.rupi.data.db.entity

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Currency(
    @PrimaryKey
    var id:String,
    var code: String,
    var name: String
) {

    @Ignore
    lateinit var prices:List<Price>

    /*
    fun defaultValue() : String{
        return 1.0.currencyFormat(this.locale, Currency.getInstance(code))
    }

    fun quotation() : String{
        return sell.currencyFormat(Locale("pt", "BR"), Currency.getInstance("BRL"))
    }*/

    fun icon() : String{
        return "ic_${code?.toLowerCase(Locale.ROOT)}"
    }

    /*
    fun variation() : String{
        return "${pctChange}%"
    }*/
}