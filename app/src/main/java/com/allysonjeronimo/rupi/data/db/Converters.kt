package com.allysonjeronimo.rupi.data.db

import androidx.room.TypeConverter
import com.allysonjeronimo.rupi.extensions.toDate
import java.util.*

class Converters{

    @TypeConverter
    fun timestampToDate(value:String?) : Date?{
        return value?.toDate()
    }

    @TypeConverter
    fun dateToTimestamp(date:Date?) : String?{
        return date?.toString()
    }
}