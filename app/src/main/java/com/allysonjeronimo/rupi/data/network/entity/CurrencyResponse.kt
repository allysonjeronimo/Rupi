package com.allysonjeronimo.rupi.data.network.entity

import com.allysonjeronimo.rupi.data.db.entity.Currency

data class CurrencyResponse(
    var USD: CurrencyItemResponse,
    var USDT: CurrencyItemResponse,
    var BTC: CurrencyItemResponse,
    var EUR: CurrencyItemResponse
){

    fun toCurrencyList() : List<Currency>{
        return listOf(
            USD.toCurrency(),
            USDT.toCurrency(),
            BTC.toCurrency(),
            EUR.toCurrency()
        )
    }
}

