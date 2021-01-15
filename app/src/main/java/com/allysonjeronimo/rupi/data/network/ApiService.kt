package com.allysonjeronimo.rupi.data.network

import android.content.Context
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiService {

    companion object{

        private var instance:CurrencyService? = null

        fun getInstance(context: Context) : CurrencyService{
            if(instance == null){

                instance = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(CurrencyService::class.java)
            }

            return instance!!
        }
    }
}