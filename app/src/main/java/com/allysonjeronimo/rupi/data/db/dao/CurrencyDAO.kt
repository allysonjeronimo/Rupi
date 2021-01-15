package com.allysonjeronimo.rupi.data.db.dao

import androidx.room.*
import com.allysonjeronimo.rupi.data.db.entity.Currency
import com.allysonjeronimo.rupi.data.db.entity.Price
import com.allysonjeronimo.rupi.data.db.entity.PriceData

@Dao
abstract class CurrencyDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertAll(currencies:List<Currency>)

    @Query("select * from Currency")
    abstract suspend fun findAll() : List<Currency>

    @Query("select count(code) from Currency")
    abstract suspend fun count() : Int

    @Insert
    abstract suspend fun insertPrices(prices:List<Price>)

    @Query("delete from Price where currencyId = :currencyId")
    abstract suspend fun deletePrices(currencyId:String)

    @Query("select * from Price where currencyId = :currencyId")
    abstract suspend fun findPrices(currencyId:String) : List<PriceData>

    suspend fun insertWithPrices(currencies:List<Currency>){
        val prices = mutableListOf<Price>()
        currencies.forEach { currency ->
            prices.addAll(currency.prices)
            deletePrices(currency.id)
        }
        insertAll(currencies)
        insertPrices(prices)
    }

}