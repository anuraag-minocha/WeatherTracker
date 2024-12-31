package com.example.weathertracker.domain

import com.example.weathertracker.data.WeatherApiService
import com.example.weathertracker.data.WeatherRepository
import com.example.weathertracker.data.WeatherResponse

class WeatherRepositoryImpl(
    private val apiService: WeatherApiService
): WeatherRepository {
    private val apiKey = "fbb1978b2d7a455ca18161909242712"

    override suspend fun getWeather(city: String): WeatherResponse {
        return apiService.getWeather(apiKey, city)
    }
}
