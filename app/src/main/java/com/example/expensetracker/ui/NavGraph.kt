package com.example.expensetracker.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Add : Screen("add")
    object Stats : Screen("stats")
}


@Composable
fun AppNavHost(navHostController: NavHostController, modifier: Modifier = Modifier) {

    NavHost(
        navController = navHostController,
        startDestination = Screen.Home.route,
        modifier = modifier
    ) {
        composable(Screen.Home.route) {
            HomeScreen(
            )
        }

        composable(route = Screen.Add.route) {
            AddExpenseScreen(
                navigateUp = { navHostController.navigateUp() },
                navigateBack = {
                    navHostController.navigate(Screen.Home.route)
                    {
                        popUpTo(Screen.Home.route) {
                            inclusive = true
                        }
                    }
                },
                navigatePopBackStack = {
                    navHostController.popBackStack()
                }
            )
        }
        composable(route = Screen.Stats.route) {
            StatsScreen()
        }

    }


}