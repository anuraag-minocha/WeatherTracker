package com.example.weathertracker.presentation

import HomeScreen
import WeatherViewModel
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.weathertracker.ui.theme.WeatherTrackerTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherTrackerTheme {
                val weatherViewModel: WeatherViewModel by viewModel()
                LaunchedEffect(Unit) {
                    weatherViewModel.loadCity(applicationContext)
                }
                val cityDetail by weatherViewModel.cityDetail.collectAsStateWithLifecycle()
                val uiState by weatherViewModel.uiState.collectAsStateWithLifecycle()

                Column(
                    modifier = Modifier
                        .background(color = Color.White)
                ) {
                    SearchBar({ weatherViewModel.fetchWeather(it) })
                    HomeScreen(
                        uiState,
                        cityDetail,
                        { weatherViewModel.updateCity(it, applicationContext) })
                }
            }
        }
    }
}
