package com.example.weathertracker.data

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore by preferencesDataStore(name = "settings")

object DataStoreManager {
    private val NAME = stringPreferencesKey("name")
    private val TEMP = floatPreferencesKey("temp")
    private val UV = floatPreferencesKey("uv")
    private val FEELS_LIKE = floatPreferencesKey("feels_like")
    private val HUMIDITY = intPreferencesKey("humidity")
    private val CONDITION_ICON = stringPreferencesKey("condition_icon")
    private val CONDITION_TEXT = stringPreferencesKey("condition_text")

    suspend fun saveCity(context: Context, weather: WeatherResponse) {
        context.dataStore.edit { preferences ->
            preferences[NAME] = weather.location.name
            preferences[TEMP] = weather.current.temp_c
            preferences[UV] = weather.current.uv
            preferences[FEELS_LIKE] = weather.current.feelslike_c
            preferences[HUMIDITY] = weather.current.humidity
            preferences[CONDITION_ICON] = weather.current.condition.icon
            preferences[CONDITION_TEXT] = weather.current.condition.text
        }
    }

    fun getCity(context: Context): Flow<WeatherResponse?> {
        return context.dataStore.data.map { preferences ->
            WeatherResponse(
                Location(preferences[NAME] ?: ""),
                CurrentWeather(
                    preferences[TEMP] ?: 0f,
                    WeatherCondition(
                        preferences[CONDITION_TEXT] ?: "",
                        preferences[CONDITION_ICON] ?: ""
                    ),
                    preferences[HUMIDITY] ?: 0,
                    preferences[UV] ?: 0f,
                    preferences[FEELS_LIKE] ?: 0f
                )
            )
        }
    }
}
