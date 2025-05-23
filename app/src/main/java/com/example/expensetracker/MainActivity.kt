package com.example.expensetracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.expensetracker.ui.AppNavHost
import com.example.expensetracker.ui.Screen
import com.example.expensetracker.ui.theme.ExpenseTrackerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ExpenseTrackerTheme {
                MainApp()
            }
        }
    }
}

@Composable
fun MainApp(navController: NavHostController = rememberNavController()) {
    val currentRoute = navController.currentBackStackEntry?.destination?.route ?: "Home"
    Scaffold(
        contentWindowInsets = WindowInsets(0, 0, 0, 0)
    ) {
        AppNavHost(navHostController = navController, modifier = Modifier.padding(it))
    }


}

@Composable
fun BottomBar(modifier: Modifier = Modifier, currentRoute: String) {
    BottomAppBar(
        actions = {
            Icon(
                imageVector = Icons.Default.Home,
                contentDescription = null,
                tint = if (currentRoute == Screen.Home.route) {
                    Color(0xFF009688)
                } else {
                    Color.White

                }
            )

        }
    )
}


@Composable
fun FabInBottomBar(modifier: Modifier = Modifier, onClick: () -> Unit) {
    FloatingActionButton(
        onClick = onClick,
        modifier = modifier,
        shape = CircleShape,
        elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation(
            defaultElevation = 4.dp
        ),
    ) {
        Icon(
            imageVector = Icons.Default.Add,
            tint = Color.White,
            contentDescription = "Add Button"
        )

    }
}


