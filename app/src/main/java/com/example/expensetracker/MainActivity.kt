package com.example.expensetracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
    val currentRoute = navController.currentBackStackEntry?.destination?.route ?: "home"
    Scaffold(
        contentWindowInsets = WindowInsets(0, 0, 0, 0),
        bottomBar = {
            BottomAppBar() {

            }
        }, floatingActionButton = {
            FabInBottomBar {
                navController.navigate(Screen.Add.route)
            }

        }


        ,
        floatingActionButtonPosition = FabPosition.Center
    ) {
        AppNavHost(navHostController = navController, modifier = Modifier.padding(it))
    }


}

@Composable
fun BottomBar(
    modifier: Modifier = Modifier,
    currentRoute: String,
    onAddClick: () -> Unit,
    onGraphClick: () -> Unit,
    onHomeClick: () -> Unit
) {
BottomAppBar() {
    IconButton(onClick = {}) {
        Icon(
            modifier = Modifier.size(48.dp),
            imageVector = Icons.Default.Home,
            contentDescription = "Home",
            tint = if (currentRoute == Screen.Home.route) MaterialTheme.colorScheme.primary else Color.Gray
        )
    }
    Spacer(Modifier.weight(1f, true))
    IconButton(onClick = {  }) {
        Icon(modifier = Modifier.size(48.dp),
            imageVector = Icons.AutoMirrored.Filled.List,
            contentDescription = "Stats",
            tint = if (currentRoute == Screen.Stats.route) MaterialTheme.colorScheme.primary else Color.Gray
        )
    }
}

}


@Composable
fun FabInBottomBar(modifier: Modifier = Modifier, onClick: () -> Unit) {
    FloatingActionButton(
        onClick = onClick,
        modifier = modifier,
        containerColor = Color.Transparent,
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


