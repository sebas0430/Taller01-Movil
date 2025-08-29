package com.javeriana.taller01.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import androidx.core.graphics.toColorInt

fun hexToArgbInt(hex: String, alpha: String = "FF"): Int {
    val argbHex = "0x$alpha$hex"
    return argbHex.toLong(16).toInt()
}

@Composable
fun pilotoScreen(headshot_url: String, full_name:String, team_name: String, team_colour: String, name_acronym: String, country_code: String){

    val colorHex = team_colour.replace("#", "")
    val teamColor = Color(android.graphics.Color.parseColor("#$colorHex"))
    Scaffold() { padding ->
    Box(
        modifier = Modifier
            .padding(padding)
            .size(400.dp),
        contentAlignment = Alignment.Center
    ){
        Column(
            modifier = Modifier
                .padding(padding)
                .background(Color(0xFFFFF2F7))  // rosado muy suave
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(40.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ){

            AsyncImage(
                model = headshot_url,
                contentDescription = "Foto del piloto",
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(110.dp),
                alignment = Alignment.Center
            )

            Text(
                text = full_name
            )
            Text(
                text = team_name,
                color = teamColor

            )
            Text(
                text = name_acronym
            )



        }
    }


    }
}


