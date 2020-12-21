package com.allysonjeronimo.rupi.model.remote

import com.allysonjeronimo.rupi.model.entity.Currency

data class CurrencyResponse(
    var USD:CurrencyItemResponse,
    var USDT:CurrencyItemResponse,
    var BTC:CurrencyItemResponse,
    var EUR:CurrencyItemResponse
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

