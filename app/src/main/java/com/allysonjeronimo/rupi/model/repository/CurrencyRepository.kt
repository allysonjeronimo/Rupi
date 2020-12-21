package com.allysonjeronimo.rupi.model.repository

import com.allysonjeronimo.rupi.model.entity.Currency

interface CurrencyRepository {

    fun getAll(success:(List<Currency>) -> Unit, failure: () -> Unit)

}