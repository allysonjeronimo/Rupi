package com.allysonjeronimo.rupi.model.remote

import retrofit2.Call
import retrofit2.http.GET

interface AwesomeApi {

    @GET("json/all")
    fun getAll() : Call<CurrencyResponse>

}