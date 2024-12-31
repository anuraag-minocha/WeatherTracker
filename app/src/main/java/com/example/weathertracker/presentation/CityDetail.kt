package com.example.weathertracker.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.weathertracker.R
import com.example.weathertracker.data.WeatherResponse
import com.example.weathertracker.ui.theme.Grey9A
import com.example.weathertracker.ui.theme.GreyC4
import com.example.weathertracker.ui.theme.GreyF2

@Composable
fun CityDetail(
    weather: WeatherResponse
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp, bottom = 16.dp, top = 30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        AsyncImage(
            model = "https:${weather.current.condition.icon}",
            contentDescription = weather.current.condition.text,
            modifier = Modifier.size(120.dp)
        )
        Spacer(Modifier.height(5.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(weather.location.name, fontSize = 30.sp, fontWeight = FontWeight.Bold)
            Spacer(Modifier.width(10.dp))
            Icon(painter = painterResource(R.drawable.ic_arrow), contentDescription = null)
        }
        Spacer(Modifier.height(10.dp))
        Text("${weather.current.temp_c}°", fontSize = 70.sp, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(15.dp))
        Surface(
            color = GreyF2, shape = RoundedCornerShape(16.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 40.dp)
        ) {
            Row(
                modifier = Modifier.padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        "Humidity",
                        color = GreyC4,
                        fontSize = 12.sp
                    )
                    Text(
                        "${weather.current.humidity}%",
                        color = Grey9A,
                        fontSize = 14.sp
                    )
                }
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        "UV",
                        color = GreyC4,
                        fontSize = 12.sp
                    )
                    Text(
                        "${weather.current.uv}",
                        color = Grey9A,
                        fontSize = 14.sp
                    )
                }
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        "Feels Like",
                        color = GreyC4,
                        fontSize = 12.sp
                    )
                    Text(
                        "${weather.current.feelslike_c}°",
                        color = Grey9A,
                        fontSize = 14.sp
                    )
                }
            }
        }
    }
}


