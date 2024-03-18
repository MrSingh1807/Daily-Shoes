package com.example.dailyshoes.ui.navigation



sealed class AuthNav(
    val route: String,
    val title: String,
) {
    data object SignIn : AuthNav("sign_in", "Sign In")
    data object SignUp : AuthNav("sign_up", "Sign Up")
    data object PasswordRecovery : AuthNav("recovery", "Password Recovery")
}