package com.allysonjeronimo.rupi.data.db.dao

import androidx.room.*
import com.allysonjeronimo.rupi.data.db.entity.Currency
import com.allysonjeronimo.rupi.data.db.entity.CurrencyWithPrices
import com.allysonjeronimo.rupi.data.db.entity.Price

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

    @Query("delete from Price where currencyId = :id")
    abstract suspend fun deletePrices(id:String)

    @Query("select * from Price where currencyId = :id")
    abstract suspend fun findPrices(id:String) : List<Price>

    suspend fun insertWithPrices(currencies:List<Currency>){
        val prices = mutableListOf<Price>()
        currencies.forEach { currency ->
            prices.addAll(currency.prices)
        }
        insertAll(currencies)
        insertPrices(prices)
    }

    suspend fun findAllWithPrices() : List<Currency>{
        val currencies = findAll()
        currencies.forEach { currency ->
            currency.prices = findPrices(currency.id)
        }
        return currencies
    }

}