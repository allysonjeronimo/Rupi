package com.allysonjeronimo.rupi.model.repository

import com.allysonjeronimo.rupi.model.entity.Currency
import com.allysonjeronimo.rupi.model.remote.AwesomeApi
import com.allysonjeronimo.rupi.model.remote.CurrencyResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CurrencyDataRepository(
    private val api:AwesomeApi
) : CurrencyRepository{

    override fun getAll(success: (List<Currency>) -> Unit, failure: () -> Unit) {

        try{

            val response = api.getAll()

            response.enqueue(object: Callback<CurrencyResponse>{

                override fun onResponse(
                    call: Call<CurrencyResponse>,
                    response: Response<CurrencyResponse>
                ) {
                    if(response.isSuccessful) {
                        success(response.body()?.toCurrencyList() ?: listOf<Currency>())
                    }else{
                        failure()
                    }
                }
                override fun onFailure(call: Call<CurrencyResponse>, t: Throwable) {
                    failure()
                }
            })

        }catch(e:Exception){
            e.printStackTrace()
        }

    }

}