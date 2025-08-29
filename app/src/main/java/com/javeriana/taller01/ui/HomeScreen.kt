package com.javeriana.taller01.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.javeriana.taller01.R
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController

//funcion composable para el logo de tic tac toe
@Composable
fun LogoTicTacToe(onClick: () -> Unit) {
    Image(
        //referencia del logo
        painter = painterResource(R.drawable.logo_tic_tac_toe),
        //decripcion del logo
        contentDescription = "Tic Tac Toe",
        //modificador del logo para que sea clickable
        modifier = Modifier.padding(18.dp).clickable{ onClick() }

    )
}

//funcion composable para el logo de formula 1
@Composable
fun LogoFormula1(onClick: () -> Unit){
    Image(
        //referencia del logo
        painter = painterResource(R.drawable.logo_formula_1),
        //decripcion del logo
        contentDescription = "Formula 1 info",
        //modificador del logo para que sea clickable
        modifier = Modifier.padding(18.dp).clickable{ onClick() }
    )
}

//funcion composable para el cuerpo de la pantalla principal
@Composable
fun HomeScreen(navController: NavController){


    // Scaffold para la estructura de la pantalla
    Scaffold(
        containerColor = Color(0xFF7980CB),
        modifier = Modifier.fillMaxSize()
    ){ padding ->
        // Modificaciones de la columna
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
            .padding(16.dp)
    ) // contenido de la columna
        {
        //Titulo del tic tac toe
        Text(
            text = "Tic Tac Toe",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            modifier = Modifier.padding(10.dp)
        )

        //logo de tic tac toe
        LogoTicTacToe(
            onClick = { navController.navigate("tictactoe") }
        )

        //Titulo del formula 1
        Text(
            text = "Formula 1 ",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            modifier = Modifier.padding(10.dp)
        )

        //logo de formula 1
        LogoFormula1(
            onClick = {navController.navigate("formula1")}
        )
    }
}
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview(){
    val navController = androidx.navigation.compose.rememberNavController()
    HomeScreen(navController = navController)
}