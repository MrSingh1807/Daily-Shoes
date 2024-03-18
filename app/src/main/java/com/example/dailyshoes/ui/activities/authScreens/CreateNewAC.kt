package com.example.dailyshoes.ui.activities.authScreens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.dailyshoes.R
import com.example.dailyshoes.ui.activities.CartActivity.Companion.TitleBar
import com.example.dailyshoes.ui.activities.authScreens.SignIn.BottomButtons
import com.example.dailyshoes.ui.navigation.AuthNav


object CreateNewAC {

    @Composable
    fun CreateNewACScreen(navController: NavHostController) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    colorResource(id = R.color.home_background),
                ),
        ) {

            TitleBar("", backPressed = { navController.popBackStack() })
            SignIn.AuthTitle(title1 = "Create Account", title2 = "Let’s Create Account Together")
            Column(
                modifier = Modifier.padding(top = 50.dp),
            ) {
                SignIn.AboutUserItem()
                Spacer(modifier = Modifier.padding(15.dp))
                SignIn.AboutUserItem(
                    itemTitle = "Email Address",
                    itemValue = "AlissonBecker@gmail.com"
                )
                Spacer(modifier = Modifier.padding(15.dp))
                SignIn.AboutUserItem(
                    itemTitle = "Password",
                    itemValue = " ******** ",
                    passwordVisibleIcon = R.drawable.ic_password_invisible
                )
            }

            BottomButtons(
                btmButton1 = "Already have an account? ",
                btmButton2 = "Sign in",
                alreadyAccountClick = {
                    navController.navigate(AuthNav.SignIn.route)
                })
        }
    }

}

@Preview(showBackground = true)
@Composable
fun CreateNewACScreenPreview() {
    CreateNewAC.CreateNewACScreen(rememberNavController())
}
