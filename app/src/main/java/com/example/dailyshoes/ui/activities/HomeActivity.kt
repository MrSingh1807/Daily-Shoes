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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
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
//        val navBackStackEntry = navController.currentBackStackEntry
//    val currentDestination = navBackStackEntry?.destination

        var selectedItemIndex by rememberSaveable { mutableStateOf(0) }

        NavigationBar(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .clip(shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)),
            containerColor = Color.White,
        ) {
            screenLists.forEachIndexed { index, screen ->
                NavigationBarItem(
                    label = { Text(text = screen.title) },
                    selected = selectedItemIndex == index,
                    onClick = {
                        selectedItemIndex = index
                        navController.navigate(screen.route)
                    },
                    icon = {
                        Icon(
                            painter = painterResource(id = screen.icon),
                            contentDescription = "Navigation Icon"
                        )
                    })

            }
        }

//    selected = currentDestination?.hierarchy?.any {
//        it.route == screen.route
//    } == true,

    }

}
