package com.javeriana.taller01.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.javeriana.taller01.R


@Composable
fun LogoPUJ(){
    Image(
        painter = painterResource(R.drawable.logo_puj),
        contentDescription = "Logo de la PUJ",
        modifier = Modifier.padding(5.dp).padding(),
        contentScale = ContentScale.FillWidth

    )
}


@Preview
@Composable
fun TicTacToeScreen() {
    
   // tablero 3x3 representado como una lista de listas
    val board = remember {
        mutableStateListOf(
            mutableStateListOf("", "", ""),
            mutableStateListOf("", "", ""),
            mutableStateListOf("", "", "")
        )
    }
    // jugador actual ("X" o "O")
    var player by remember { mutableStateOf("X") }

    // funcion para cambiar de turno 
    fun changePlayer(p: String) = if (p == "X") "O" else "X"

    // funcion para verificar si hay un ganador, entra una lista de listas (tablero)
    fun checkWinner(b: List<List<String>>): Boolean {
        // filas
        for (i in 0..2) 
            if (b[i][0].isNotEmpty() && b[i][0] == b[i][1] && b[i][1] == b[i][2]) 
                return true
        
        // columnas
        for (j in 0..2) 
            if (b[0][j].isNotEmpty() && b[0][j] == b[1][j] && b[1][j] == b[2][j])
                return true
        // diagonales
        if (b[0][0].isNotEmpty() && b[0][0] == b[1][1] && b[1][1] == b[2][2]) 
            return true
        if (b[0][2].isNotEmpty() && b[0][2] == b[1][1] && b[1][1] == b[2][0]) 
            return true
        
        // si aun no hay ganador
        return false
    }

    fun winnerLine(b: List<List<String>>): List<Pair<Int, Int>>?{
        // filas
        for (i in 0..2)
            if (b[i][0].isNotEmpty() && b[i][0] == b[i][1] && b[i][1] == b[i][2])
                return listOf(i to 0, i to 1, i to 2)

        // columnas
        for (j in 0..2)
            if (b[0][j].isNotEmpty() && b[0][j] == b[1][j] && b[1][j] == b[2][j])
                return listOf(0 to j, 1 to j, 2 to j)
        // diagonales
        if (b[0][0].isNotEmpty() && b[0][0] == b[1][1] && b[1][1] == b[2][2])
            return listOf(0 to 0, 1 to 1, 2 to 2)
        if (b[0][2].isNotEmpty() && b[0][2] == b[1][1] && b[1][1] == b[2][0])
            return listOf(0 to 2, 1 to 1, 2 to 0)

        return null
    }
    
    // para marcar una jugada en la casilla (i, j)
    fun markCell(i: Int, j: Int) {
        
        // verificar si la casilla ya esta ocupada o si ya hay ganador o empate
        val hasWinner = checkWinner(board)
        val isDraw = !board.flatten().contains("") && !hasWinner
        
        // si la casilla no esta vacia o ya hay ganador o empate, no hacer nada
        if (board[i][j].isNotEmpty() || hasWinner || isDraw) 
            return
        
        // marcar la casilla con el simbolo del jugador actual
        board[i][j] = player
        
        // cambiar de jugador si no hay ganador aun
        if (!checkWinner(board)) {
            player = changePlayer(player)
        }
    }

    fun resetGame() {
        // limpiar el tablero
        for (i in 0..2) 
            for (j in 0..2) 
                board[i][j] = ""
        
        // inicializar con el jugador "X"
        player = "X"
    }


    // banderas para ganador y empate para modificar el tablero y los botones
    val hasWinner = checkWinner(board)
    val isDraw = !board.flatten().contains("") && !hasWinner

    // Scaffold para la estructura de la pantalla
    Scaffold(
        containerColor = Color(0xFF7980CB),
        modifier = Modifier
            .padding(8.dp)
            .fillMaxSize()
    ) { padding ->
        // Box para centrar el contenido
        Box(
            modifier = Modifier
                .padding(15.dp)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {


            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                LogoPUJ()
                // titulo del juego
                Text(
                    text = "Tic Tac Toe",
                    color = Color.Red,
                    modifier = Modifier.padding(16.dp))

                // tablero 3x3
                for (i in 0..2) {
                    Row(horizontalArrangement = Arrangement.Center) {
                        for (j in 0..2) {
                            // si hay ganador, colorear de verde la casilla del ganador y gris las demas
                            val color = if(hasWinner) {
                                val ganadoras = winnerLine(board)
                                if (ganadoras != null && ganadoras.contains(i to j)) {
                                    Color.Green
                                } else {
                                    Color.LightGray
                                }}else if (isDraw){
                                    Color.Gray
                                }
                                else
                                Color.Yellow


                            Button(
                                onClick = { markCell(i, j) },
                                colors = ButtonDefaults.buttonColors(
                                    // variable del color de la casilla
                                    containerColor = color,
                                    contentColor = Color.Black,
                                ),
                                modifier = Modifier
                                    .padding(4.dp)
                                    .size(80.dp)
                            ) { // contenido del boton (X, O o vacio)
                                Text(board[i][j])
                            }
                        }
                    }
                }

                Spacer(Modifier.height(8.dp))

                when {
                    // cuando hay ganador, mostrar mensaje y boton de reinicio
                    hasWinner -> {
                        Text("Player ${player} wins!", color = Color.Green)
                        Button(
                            onClick = { resetGame() },
                            modifier = Modifier.padding(top = 8.dp)
                        ) { Text("Reset Game") }
                    }
                    // cuando hay empate, mostrar mensaje y boton de reinicio
                    isDraw -> {
                        Text("It's a draw!", color = Color.Gray)
                        Button(
                            onClick = { resetGame() },
                            modifier = Modifier.padding(top = 8.dp)
                        ) { Text("Reset Game") }
                    }
                    // mostrar el turno del jugador actual
                    else -> {
                        Text("Current Player: $player", color = Color.Blue)
                    }
                }
            }
        }
    }
}
