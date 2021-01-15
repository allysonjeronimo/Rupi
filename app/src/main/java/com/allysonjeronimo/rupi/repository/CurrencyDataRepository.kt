package com.allysonjeronimo.rupi.repository

import com.allysonjeronimo.rupi.data.db.dao.CurrencyDAO
import com.allysonjeronimo.rupi.data.db.entity.Currency
import com.allysonjeronimo.rupi.data.network.CurrencyService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CurrencyDataRepository(
    private val currencyCacheService: CurrencyDAO,
    private val currencyRemoteService: CurrencyService
) : CurrencyRepository{

    override suspend fun allCurrencies(): List<Currency> =
        withContext(Dispatchers.IO) {
            try {
                val currencies = currencyRemoteService.getAll().toCurrencyList()
                currencyCacheService.insertAll(currencies)
                currencies
            } catch (ex: Exception) {
                ex.printStackTrace()
                currencyCacheService.findAll()
            }
        }
}