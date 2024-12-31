package com.example.weathertracker.data


interface WeatherRepository {
    suspend fun getWeather(city: String): WeatherResponse
}
