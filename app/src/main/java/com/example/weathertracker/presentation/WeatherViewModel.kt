import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weathertracker.data.CurrentWeather
import com.example.weathertracker.data.DataStoreManager
import com.example.weathertracker.data.Location
import com.example.weathertracker.data.WeatherCondition
import com.example.weathertracker.data.WeatherRepository
import com.example.weathertracker.data.WeatherResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

sealed class WeatherUIState {
    data object Loading : WeatherUIState()
    data class Success(val weather: WeatherResponse) : WeatherUIState()
    data class Error(val message: String) : WeatherUIState()
    data object Empty : WeatherUIState() // No city selected
}


class WeatherViewModel(
    private val weatherRepository: WeatherRepository
) :
    ViewModel() {
    private val emptyWeather = WeatherResponse(
        Location(""),
        CurrentWeather(0f, WeatherCondition("", ""), 0, 0f, 0f)
    )
    private val _uiState = MutableStateFlow<WeatherUIState>(WeatherUIState.Empty)
    val uiState: StateFlow<WeatherUIState> get() = _uiState
    private val _cityDetail = MutableStateFlow(
        emptyWeather
    )
    val cityDetail: StateFlow<WeatherResponse> get() = _cityDetail

    fun loadCity(context: Context) {
        viewModelScope.launch {
            val savedCity = DataStoreManager.getCity(context).first()
            _cityDetail.value = savedCity ?: return@launch
        }
    }

    fun updateCity(newCityDetail: WeatherResponse, context: Context) {
        viewModelScope.launch {
            DataStoreManager.saveCity(context, newCityDetail)
            _cityDetail.value = newCityDetail
        }
    }

    fun fetchWeather(cityName: String) {
        _cityDetail.value = emptyWeather
        if (cityName.isBlank()) {
            _uiState.value = WeatherUIState.Error("City name cannot be empty.")
            return
        }

        _uiState.value = WeatherUIState.Loading

        viewModelScope.launch {
            try {
                val response = weatherRepository.getWeather(cityName)
                _uiState.value = WeatherUIState.Success(response)
            } catch (e: Exception) {
                // Handle specific errors like network or parsing issues
                _uiState.value =
                    WeatherUIState.Error("Failed to fetch weather: ${e.localizedMessage}")
            }
        }
    }
}
