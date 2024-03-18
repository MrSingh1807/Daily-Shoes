package com.example.dailyshoes.ui.activities.authScreens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.dailyshoes.R
import com.example.dailyshoes.ui.activities.CartActivity.Companion.TitleBar
import com.example.dailyshoes.ui.activities.authScreens.SignIn.AboutUserItem
import com.example.dailyshoes.ui.theme.Poppins_MEDIUM
import com.example.dailyshoes.ui.theme.Poppins_Regular

object RecoveryPassword {

    @Composable
    fun RecoveryPassScreen(navController: NavHostController) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    colorResource(id = R.color.home_background),
                ),
        ) {

            TitleBar("", backPressed = {
                navController.popBackStack()
            })

            val title1 = "Recovery Password"
            val title2 = "Please Enter Your Email Address To \nReceive a Verification Code"

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 25.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = title1,
                    style = TextStyle(fontFamily = Poppins_MEDIUM, fontSize = 25.sp),
                )
                Text(
                    text = title2,
                    style = TextStyle(
                        fontFamily = Poppins_Regular,
                        color = Color.Gray,
                        fontSize = 16.sp
                    ),
                    textAlign = TextAlign.Center
                )

            }

            Spacer(modifier = Modifier.padding(vertical = 30.dp))
            AboutUserItem(itemTitle = "Email Address", itemValue = "AlissonBecker@gmail.com")

            Button(
                onClick = { /*TODO*/ }, modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.intro_get_started))
            ) {
                Text(text = "Continue", style = TextStyle(fontFamily = Poppins_MEDIUM))
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun RecoveryPasswordPreview() {
    RecoveryPassword.RecoveryPassScreen(rememberNavController())
}