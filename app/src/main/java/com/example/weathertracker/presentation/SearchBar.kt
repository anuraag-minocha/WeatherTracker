package com.example.weathertracker.presentation

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.weathertracker.ui.theme.GreyF2

@Composable
fun SearchBar(
    onSearchClick: (String) -> Unit
) {
    var query by remember { mutableStateOf("") }

    TextField(
        value = query,
        onValueChange = { query = it },
        placeholder = { Text("Search Location") },
        singleLine = true,
        shape = RoundedCornerShape(16.dp), // Rounded corners
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = GreyF2,
            focusedContainerColor = GreyF2, // Set custom background color
            focusedIndicatorColor = Color.Transparent, // Hide underline
            unfocusedIndicatorColor = Color.Transparent, // Hide underline
        ),
        trailingIcon = {
            IconButton(onClick = { onSearchClick(query) }) {
                Icon(
                    imageVector = Icons.Default.Search, // Use a search icon
                    contentDescription = "Search"
                )
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
    )
}


