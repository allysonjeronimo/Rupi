package com.allysonjeronimo.rupi.repository

import com.allysonjeronimo.rupi.data.entity.Currency

interface CurrencyRepository {

    fun getAll(success:(List<Currency>) -> Unit, failure: () -> Unit)

}