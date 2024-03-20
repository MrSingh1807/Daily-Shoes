package com.example.dailyshoes.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.dailyshoes.ui.activities.authScreens.CreateNewAC
import com.example.dailyshoes.ui.activities.authScreens.RecoveryPassword
import com.example.dailyshoes.ui.activities.authScreens.SignIn
import com.example.dailyshoes.ui.activities.homeScreens.FavouriteScreen
import com.example.dailyshoes.ui.activities.homeScreens.HomeScreen
import com.example.dailyshoes.ui.activities.homeScreens.NotificationScreen
import com.example.dailyshoes.ui.activities.homeScreens.ProfileScreen
import com.example.dailyshoes.ui.viewModel.AuthViewModel


@Composable
fun HomeBottomNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = HomeBottomNav.Home.route
    ) {
        composable(route = HomeBottomNav.Home.route) { HomeScreen.Home() }
        composable(route = HomeBottomNav.Favourite.route) { FavouriteScreen.Favourite() }
        composable(route = HomeBottomNav.Notification.route) { NotificationScreen.Notification() }
        composable(route = HomeBottomNav.Profile.route) { ProfileScreen.Profile() }
    }
}

@Composable
fun AuthNavGraph(authViewModel: AuthViewModel, navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = AuthNav.SignIn.route
    ) {
        composable(route = AuthNav.SignIn.route) { SignIn.SignInScreen(navController) }
        composable(route = AuthNav.SignUp.route) {
            CreateNewAC.CreateNewACScreen(
//                authViewModel,
                navController
            )
        }
        composable(route = AuthNav.PasswordRecovery.route) {
            RecoveryPassword.RecoveryPassScreen(
                navController
            )
        }
    }
}

