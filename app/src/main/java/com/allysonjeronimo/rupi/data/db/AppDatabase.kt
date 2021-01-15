package com.allysonjeronimo.rupi.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.allysonjeronimo.rupi.data.db.dao.CurrencyDAO
import com.allysonjeronimo.rupi.data.db.entity.Currency
import com.allysonjeronimo.rupi.data.db.entity.Price

@Database(
    entities = [Currency::class, Price::class],
    version = DB_VERSION
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase(){

    abstract fun currencyDAO() : CurrencyDAO

    companion object{

        private var INSTANCE:AppDatabase? = null

        fun getInstance(context: Context) : AppDatabase{
            synchronized(this){
                var instance = INSTANCE
                if(instance == null){
                    instance = Room.databaseBuilder(
                        context,
                        AppDatabase::class.java,
                        DB_NAME
                    ).build()
                }
                return instance
            }
        }


    }
}