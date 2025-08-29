package com.javeriana.taller01.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import androidx.core.graphics.toColorInt
import com.javeriana.taller01.data.RestCountriesFactory



@Composable
fun pilotoScreen(headshot_url: String, full_name:String, team_name: String, team_colour: String, name_acronym: String, country_code: String){

    // dar formato al color para poder aplicarlo
    val colorHex = team_colour.replace("#", "")
    val teamColor = Color(android.graphics.Color.parseColor("#$colorHex"))

    // mapear algunos paises para que el api las encuentre
    fun toIso3(code: String) = when (code.uppercase()) {
        "NED" -> "NLD"
        "GER" -> "DEU"
        "MON" -> "MCO"
        else -> code.uppercase()
    }
    // gurdar el url de la bandera
    var flagUrl by remember { mutableStateOf<String?>(null) }

    //ejecutar una vez para buscar el codigo del pais en el api
    LaunchedEffect(country_code) {
        val code = toIso3(country_code)
        val service =RestCountriesFactory.makeService()
        val country = service.getCountry(code)
        flagUrl = country[0].flags?.png
    }

// pantalla
    Scaffold(
    ) { padding ->

    Box(
        modifier = Modifier
            .padding(padding)
            .fillMaxSize()
            .background(Color(0xFFEEBBBB)),
        contentAlignment = Alignment.Center
    ){
        Column(
            modifier = Modifier
                .wrapContentSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(80.dp)
        ) {
        Row(
            modifier = Modifier
                .padding()
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = headshot_url,
                contentDescription = "Foto del piloto",
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(180.dp),
                alignment = Alignment.Center
            )

            AsyncImage(
                model = flagUrl,
                contentDescription = "Bandera del pais",
                contentScale = ContentScale.Fit,
                modifier = Modifier.size(190.dp),
                alignment = Alignment.Center
            )
        }
            Column(
                verticalArrangement = Arrangement.spacedBy(60.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {


            Text(
                text = "Name: $full_name",
                textAlign = TextAlign.Left,
            )
            Text(
                text = "team: $team_name",
                color = teamColor,
                textAlign = TextAlign.Left

            )
            Text(
                text = "Acronym: $name_acronym",
                textAlign = TextAlign.Left
            )

            }
        }}
    }



    }



