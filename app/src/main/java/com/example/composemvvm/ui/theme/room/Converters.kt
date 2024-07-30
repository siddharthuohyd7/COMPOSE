package com.example.composemvvm.ui.theme.room

import androidx.room.TypeConverter
import java.util.Date

class Converters {
    @TypeConverter
    fun toDate(timeStamp: Long): Date = Date(timeStamp)


    @TypeConverter
    fun fromDate(date: Date): Long = date.time

}