package com.javeriana.taller01.ui

import android.R
import android.graphics.Color.red
import android.provider.CalendarContract
import android.util.Log
import android.util.Log.i
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

val Board = mutableStateListOf(
    mutableStateListOf("", "", ""),
    mutableStateListOf("", "", ""),
    mutableStateListOf("", "", "")
)
var player = "X"

fun ChangePlayer(current: String): String{
    return if (current == "X") "O" else "X"
}

fun checkWinner():Boolean{
    //check rows
    for(i in 0..2){
        if(Board[i][0] == Board[i][1] && Board[i][1] == Board[i][2] && Board[i][0] != ""){
            return true
        }
    }
    //check columns
    for(i in 0..2){
        if(Board[0][i] == Board[1][i] && Board[1][i] == Board[2][i] && Board[0][i] != ""){
            return true
        }
    }
    //check diagonals
    if(Board[0][0] == Board[1][1] && Board[1][1] == Board[2][2] && Board[0][0] != ""){
        return true
    }
    if(Board[0][2] == Board[1][1] && Board[1][1] == Board[2][0] && Board[0][2] != ""){
        return true
    }

    return false
}


fun MarkButton(row: Int, column: Int, current: String){

    if(Board[row][column] == ""){
        Board[row][column] = current
    }
    if(checkWinner()){
        player = ""
        return
    }
    player = ChangePlayer(current)

}

fun ResetGame() {
    for (i in 0..2) {
        for (j in 0..2) {
            Board[i][j] = ""
        }
    }
    player = "X"
}
@Preview
@Composable
fun TicTacToeScreen() {
    Scaffold(
        modifier = Modifier.padding(8.dp).fillMaxSize()
    ) { padding ->
        Box(
            modifier = Modifier.padding(15.dp).fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            Column(){
                Text(
                    text = "Tic Tac Toe",
                    modifier = Modifier.padding(16.dp).align(Alignment.CenterHorizontally),
                    color = Color.Red
                )
                for(i in 0..2){
                    Row(
                        //modifier = Modifier.weight(1f),
                        horizontalArrangement = Arrangement.Center
                    ){
                        for(j in 0..2){
                        Button(
                            onClick = {MarkButton(i,j, player)},
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.Yellow,
                                contentColor = Color.Black
                            )
                            ){
                            Text(text = Board[i][j])
                        }}
                    }
                }

                val hasWinner = checkWinner()
                val isdraw = !Board.flatten().contains("") && !hasWinner

                if(!isdraw){
                    Text(
                        text = "Current Player: $player",
                        modifier = Modifier.padding(16.dp).align(Alignment.CenterHorizontally),
                        color = Color.Blue
                    )
                }

                if(hasWinner){
                    Text(
                        text = "Player ${ChangePlayer(player)} wins!",
                        modifier = Modifier.padding(16.dp).align(Alignment.CenterHorizontally),
                        color = Color.Green
                    )
                    Button(
                        onClick = {ResetGame()},
                        colors = ButtonDefaults.buttonColors(
                            contentColor = Color.Blue,
                            containerColor = Color.White
                        ),
                        modifier = Modifier.align(Alignment.CenterHorizontally).padding(8.dp)
                    ){
                        Text(
                            text = "Reset Game"
                        )
                    }
                } else if (isdraw){
                    Text(
                        text = "It's a draw!",
                        modifier = Modifier.padding(16.dp).align(Alignment.CenterHorizontally),
                        color = Color.Gray
                    )
                    Button(
                        onClick = {ResetGame()},
                        colors = ButtonDefaults.buttonColors(
                            contentColor = Color.Blue,
                            containerColor = Color.White
                        ),
                        modifier = Modifier.align(Alignment.CenterHorizontally).padding(8.dp)
                    ){
                        Text(
                            text = "Reset Game"
                        )
                    }
                }
            }
        }
    }
    }

