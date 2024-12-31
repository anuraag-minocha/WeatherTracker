package com.example.weathertracker.di

import WeatherViewModel
import com.example.weathertracker.data.RetrofitInstance
import com.example.weathertracker.data.WeatherRepository
import com.example.weathertracker.domain.WeatherRepositoryImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<WeatherRepository> { WeatherRepositoryImpl(RetrofitInstance.api) }
    viewModel { WeatherViewModel(get()) }
}

