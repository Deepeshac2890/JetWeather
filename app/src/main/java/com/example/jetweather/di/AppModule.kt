package com.example.jetweather.di

import android.content.Context
import androidx.room.Room
import com.example.jetweather.data.WeatherDao
import com.example.jetweather.data.WeatherDatabase
import com.example.jetweather.network.WeatherAPI
import com.example.jetweather.utils.Constants
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    // Inside here we setup all the app modules that are needed across entire app like db, repos etc.

    // No need to call it Gets call implicitly by dagger and hilt
    @Provides
    @Singleton
    fun provideOpenWeatherApi(): WeatherAPI {
        return Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(WeatherAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideWeatherDao(weatherDatabase: WeatherDatabase): WeatherDao =
        weatherDatabase.weatherDao()

    @Provides
    @Singleton
    fun provideAppDataBase(@ApplicationContext context: Context): WeatherDatabase =
        Room.databaseBuilder(context, WeatherDatabase::class.java, "weather_database")
            .fallbackToDestructiveMigration().build()

}