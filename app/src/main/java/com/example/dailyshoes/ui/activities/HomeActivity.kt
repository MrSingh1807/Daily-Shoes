package com.example.dailyshoes.ui.activities

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.dailyshoes.R
import com.example.dailyshoes.ui.navigation.HomeBottomNav
import com.example.dailyshoes.ui.navigation.HomeBottomNavGraph
import com.example.dailyshoes.ui.theme.DailyShoesTheme


class HomeActivity : ComponentActivity() {

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            DailyShoesTheme {
                val navController = rememberNavController()
                Scaffold(
                    bottomBar = { BottomHomeBar(navController = navController) }
                ) {
                    HomeBottomNavGraph(navController = navController)
                }
            }
        }
    }

    @Composable
    fun BottomHomeBar(navController: NavController) {

        val screenLists = listOf(
            HomeBottomNav.Home,
            HomeBottomNav.Favourite,
            HomeBottomNav.Notification,
            HomeBottomNav.Profile
        )

        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination?.route

        NavigationBar(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .clip(shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)),
            containerColor = Color.White,
        ) {
            screenLists.forEachIndexed { index, screen ->
                NavigationBarItem(
                    colors = NavigationBarItemDefaults.colors(
                        indicatorColor = colorResource(id = R.color.intro_get_started),
                        unselectedIconColor = Color.Black,
                        selectedIconColor = Color.Black,
                        unselectedTextColor = Color.Black,
                        selectedTextColor = colorResource(id = R.color.intro_get_started)
                    ),
                    label = { Text(text = screen.title) },
                    selected = currentDestination == screen.route,
                    onClick = {
                        navController.navigate(screen.route) {
                            popUpTo(route = HomeBottomNav.Home.route) {
                                inclusive = false
                                saveState = true
                            }

                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    icon = {
                        Icon(
                            painter = painterResource(id = screen.icon),
                            contentDescription = "Navigation Icon"
                        )
                    })

            }
        }
    }


    @Preview(showBackground = true)
    @Composable
    fun ShowHomePreview() {
        BottomHomeBar(navController = rememberNavController())
    }
}
