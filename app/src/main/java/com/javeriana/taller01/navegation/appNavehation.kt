package com.javeriana.taller01.navegation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.javeriana.taller01.ui.HomeScreen
import com.javeriana.taller01.ui.TicTacToeScreen
import com.javeriana.taller01.ui.formula1Screen
import com.javeriana.taller01.ui.pilotoScreen


@Composable
fun AppNavegation(
  navController: NavHostController,
  modifier: Modifier = Modifier
){
    NavHost(
        navController = navController,
        startDestination = "start",
        modifier = modifier
    ){
        composable(route = "start"){
            HomeScreen(navController = navController)
        }
        composable(route = "tictactoe"){
            TicTacToeScreen()
        }

        composable(route = "formula1"){
            formula1Screen(navController = navController)
        }

        composable(
            route = "piloto/{headshot_url}/{full_name}/{team_name}/{team_colour}/{name_acronym}/{country_code}",
            arguments = listOf(
                navArgument("headshot_url") { type = NavType.StringType },
                navArgument("full_name") { type = NavType.StringType },
                navArgument("team_name") { type = NavType.StringType },
                navArgument("team_colour") { type = NavType.StringType },
                navArgument("name_acronym") { type = NavType.StringType },
                navArgument("country_code") { type = NavType.StringType }

            )
        ){

            val imageUrl = it.arguments?.getString("headshot_url") ?: ""
            val fullName = it.arguments?.getString("full_name") ?: ""
            val teamName = it.arguments?.getString("team_name") ?: ""
            val teamColour = it.arguments?.getString("team_colour") ?: ""
            val nameAcronym = it.arguments?.getString("name_acronym") ?: ""
            val countryCode = it.arguments?.getString("country_code") ?: ""


            pilotoScreen(headshot_url = imageUrl, full_name = fullName, team_name = teamName, team_colour = teamColour, name_acronym = nameAcronym, country_code = countryCode)
        }

    }

}