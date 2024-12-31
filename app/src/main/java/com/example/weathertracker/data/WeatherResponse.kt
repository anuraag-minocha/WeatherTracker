package com.example.weathertracker.data

data class WeatherResponse(
    val location: Location,
    val current: CurrentWeather
)

data class Location(val name: String)
data class CurrentWeather(
    val temp_c: Float,
    val condition: WeatherCondition,
    val humidity: Int,
    val uv: Float,
    val feelslike_c: Float
)

data class WeatherCondition(val text: String, val icon: String)
