package com.allysonjeronimo.rupi.data.network

import com.allysonjeronimo.rupi.data.network.entity.CurrencyResponse
import retrofit2.http.GET

interface CurrencyService {

    @GET("json/all")
    suspend fun getAll() : CurrencyResponse

}