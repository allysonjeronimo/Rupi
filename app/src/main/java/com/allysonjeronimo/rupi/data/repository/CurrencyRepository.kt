package com.allysonjeronimo.rupi.data.repository

import com.allysonjeronimo.rupi.data.entity.Currency

interface CurrencyRepository {

    fun getAll(success:(List<Currency>) -> Unit, failure: () -> Unit)

}