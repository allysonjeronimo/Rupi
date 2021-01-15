package com.allysonjeronimo.rupi.data.network.entity

import com.allysonjeronimo.rupi.data.db.entity.Currency
import com.allysonjeronimo.rupi.data.db.entity.PriceData
import com.allysonjeronimo.rupi.extensions.toDate

data class CurrencyItemResponse(
    val code:String,
    val codein:String,
    val name:String,
    val bid:Double, // compra
    val ask:Double, // venda
    val high:Double, // máximo
    val low:Double, // mínimo
    val varBid:Double, // variação
    val pctChange:Double, // porcentagem de variação
    val create_date:String // data de criação (atualização)
){

    fun toCurrency() : Currency{
        return Currency(
            id = "$code-$codein",
            code = code,
            name = name,
            lastPrice = PriceData(
                buy = bid,
                sell = ask,
                high = high,
                low = low,
                pctChange = pctChange,
                date = create_date.toDate()
            )
        )
    }
}