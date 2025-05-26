package com.example.expensetracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
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
    val  currentBackStackEntry = navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry.value?.destination?.route?: "home"
    val showBottomBar = currentRoute != Screen.Add.route
    Scaffold(
        contentWindowInsets = WindowInsets(0, 0, 0, 0),

        bottomBar = {
            AnimatedVisibility(
                visible = showBottomBar

            )
            { BottomBar(
                onHomeClick = { navController.navigate(Screen.Home.route) },
                onGraphClick = {
                    navController.navigate(Screen.Stats.route)
                },
                currentRoute = currentRoute
            )}
        }, floatingActionButton = {
            AnimatedVisibility(visible = showBottomBar) {
                FabInBottomBar {
                    navController.navigate(Screen.Add.route)
                }
            }

        },
        floatingActionButtonPosition = FabPosition.Center
    ) {
        AppNavHost(navHostController = navController, modifier = Modifier.padding(it))
    }


}

@Composable
fun BottomBar(
    currentRoute: String,
    onGraphClick: () -> Unit,
    onHomeClick: () -> Unit
) {
    BottomAppBar(tonalElevation = 12.dp, containerColor = MaterialTheme.colorScheme.surface) {
        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp)) {
            IconButton(onClick = onHomeClick, modifier = Modifier.size(56.dp)) {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = "Home",
                    tint = if (currentRoute == Screen.Home.route) Color(0xFF438883) else Color.White
                    , modifier = Modifier.size(56.dp)
                )
            }
            IconButton(onClick = onGraphClick, modifier = Modifier.size(56.dp)) {
                Icon(
                    painter = painterResource(R.drawable.ic_stat),
                    contentDescription = "Stats",
                    tint = if (currentRoute == Screen.Stats.route) Color(0xFF438883) else Color.White,
                    modifier = Modifier.size(56.dp)
                )
            }
        }
    }

}


@Composable
fun FabInBottomBar(modifier: Modifier = Modifier, onClick: () -> Unit) {
    FloatingActionButton(
        onClick = onClick,
        modifier = modifier
            .size(80.dp)
            .offset(
                y = (44).dp
            ),
        containerColor = Color(0xFF438883),
        contentColor = contentColorFor(Color.White),
        shape = CircleShape,
        elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation(
            defaultElevation = 4.dp
        ),
    ) {
        Icon(
            imageVector = Icons.Default.Add,
            tint = Color.White,
            contentDescription = "Add Button",
            modifier = Modifier.size(32.dp)
        )

    }
}


