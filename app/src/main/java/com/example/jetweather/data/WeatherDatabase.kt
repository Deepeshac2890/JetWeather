package com.example.jetweather.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.jetweather.model.Favorite
import com.example.jetweather.model.UserPreferences

@Database(entities = [Favorite::class, UserPreferences::class], version = 2, exportSchema = false)
abstract class WeatherDatabase : RoomDatabase(){
    abstract fun weatherDao() : WeatherDao
}