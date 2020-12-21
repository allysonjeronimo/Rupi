package com.allysonjeronimo.rupi.model.remote

import android.os.Build
import com.allysonjeronimo.rupi.extensions.toDate
import com.allysonjeronimo.rupi.model.entity.Currency
import java.util.*

data class CurrencyItemResponse(
    var code:String? = null,
    var name:String? = null,
    var bid:Double = 0.0, // compra
    var ask:Double = 0.0, // venda
    var high:Double = 0.0, // máximo
    var low:Double = 0.0, // mínimo
    var varBid:Double = 0.0, // variação
    var pctChange:Double = 0.0, // porcentagem de variação
    var create_date:String? = null // data de criação (atualização)
){

    fun toCurrency() : Currency{
        return Currency(
            code = code,
            name = name,
            buy = bid,
            sell = ask,
            high = high,
            low = low,
            date = create_date!!.toDate())
    }
}