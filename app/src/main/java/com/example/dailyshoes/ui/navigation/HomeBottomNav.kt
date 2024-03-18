package com.example.dailyshoes.ui.navigation

import com.example.dailyshoes.R


sealed class HomeBottomNav(
    val route: String,
    val title: String,
    val icon: Int
) {
    data object Home : HomeBottomNav("home", "Home", R.drawable.ic_home_btm_nav)
    data object Favourite : HomeBottomNav("favourite", "Favourite", R.drawable.ic_fav_btm_nav)
    data object Notification : HomeBottomNav("notification", "Notification", R.drawable.ic_notification_btm_nav)
    data object Profile : HomeBottomNav("profile", "Profile", R.drawable.ic_profile_btm_nav)
}