import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.weathertracker.data.WeatherResponse
import com.example.weathertracker.ui.theme.GreyF2
import com.example.weathertracker.presentation.CityDetail

@Composable
fun HomeScreen(
    uiState: WeatherUIState, cityDetail: WeatherResponse, onCityClick: (WeatherResponse) -> Unit
) {
    if (cityDetail.location.name.isNotEmpty()) {
        CityDetail(cityDetail)
    } else {
        when (uiState) {
            is WeatherUIState.Empty -> {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text("No city selected.", fontSize = 30.sp, fontWeight = FontWeight.Bold)
                    Text(
                        "Please search for a city.", fontSize = 15.sp, fontWeight = FontWeight.Bold
                    )
                }
            }

            is WeatherUIState.Loading -> {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            is WeatherUIState.Success -> {
                val weather = uiState.weather
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    Surface(color = GreyF2, shape = RoundedCornerShape(16.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                            .clickable { onCityClick(weather) }) {
                        Row(modifier = Modifier.padding(16.dp)) {
                            Column {
                                Text(
                                    weather.location.name,
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    "${weather.current.temp_c}°",
                                    fontSize = 60.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                            Spacer(modifier = Modifier.weight(1f))
                            AsyncImage(
                                model = "https:${weather.current.condition.icon}",
                                contentDescription = weather.current.condition.text,
                                modifier = Modifier.size(80.dp)
                            )
                        }
                    }
                }

            }

            is WeatherUIState.Error -> {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Error: ${uiState.message}",
                        color = MaterialTheme.colorScheme.error,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            else -> {}
        }
    }
}
