package com.example.dailyshoes.ui.activities

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.dailyshoes.ui.navigation.AuthNavGraph
import com.example.dailyshoes.ui.navigation.HomeBottomNavGraph
import com.example.dailyshoes.ui.theme.DailyShoesTheme
import com.example.dailyshoes.ui.viewModel.AuthViewModel


class AuthActivity : ComponentActivity() {

    private val authViewModel: AuthViewModel by viewModels()

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DailyShoesTheme {
                val navController = rememberNavController()

                Scaffold {
                    AuthNavGraph(authViewModel, navController = navController)
                }
            }
        }
    }
}