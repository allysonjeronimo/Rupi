package com.allysonjeronimo.rupi.repository

import com.allysonjeronimo.rupi.data.db.entity.Currency

interface CurrencyRepository {
    suspend fun allCurrencies() : List<Currency>
}