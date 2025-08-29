package com.javeriana.taller01.ui

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialogDefaults.containerColor
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.javeriana.taller01.data.RetrofitServiceFactory
import com.javeriana.taller01.model.remoteResultPiloto
import com.javeriana.taller01.model.remoteResultPilotoItem
import java.util.concurrent.CancellationException


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun topbarfun(){
    TopAppBar(
        title = {
            Text(
                text = "Pilotos Formula 1",
                modifier = Modifier.padding(10.dp),
                fontStyle = FontStyle.Italic
            )
        },
        modifier = Modifier.height(50.dp),
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFFFC9999))
    )
}
@Composable
fun formula1Screen(navController: NavController){

    val service = remember { RetrofitServiceFactory.makeRetrofitService() }

    var pilotos by remember { mutableStateOf(remoteResultPiloto()) }


   LaunchedEffect (Unit) {
       pilotos = service.listPilotos(session_key = "9159")
       //9159 o 9684
   }




    Scaffold(
        topBar = { topbarfun() }
    ) { padding ->
        LazyColumn(
            modifier = Modifier.padding(padding).clipToBounds().background(Color(0xFFE9EEE9)),
            userScrollEnabled = true,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(30.dp)
        ) {
            items(pilotos) { piloto ->

                val route = "piloto/" + "${Uri.encode(piloto.headshot_url)}/" +
                        "${Uri.encode(piloto.full_name)}/" +
                        "${Uri.encode(piloto.team_name)}/" +
                        "${piloto.team_colour}/" +
                        "${piloto.name_acronym}/" +
                        "${piloto.country_code}"

                    Text(
                    text =" ${piloto.driver_number}. ${piloto.full_name.toString()}",
                    textAlign = TextAlign.Left,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        // piloto/{headshot_url}/{full_name}/{team_name}/{team_colour}/{name_acronym}
                        .clickable{navController.navigate(route)}

                )
                Divider(
                    color = Color.Black,
                    thickness = 1.dp
                )


            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun Formula1Preview() {
    val navController = rememberNavController()
    formula1Screen(navController)
}