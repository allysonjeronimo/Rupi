package com.allysonjeronimo.rupi.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.allysonjeronimo.rupi.data.entity.Currency

@Dao
interface CurrencyDAO {

    @Insert
    fun insert(currency: Currency): Long

    @Update
    fun update(currency:Currency)

    @Query("SELECT * FROM currency")
    fun getAll() : List<Currency>

}