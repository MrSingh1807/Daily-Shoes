package com.example.dailyshoes.ui.activities.homeScreens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.dailyshoes.R
import com.example.dailyshoes.ui.activities.CartActivity.Companion.TitleBar
import com.example.dailyshoes.ui.activities.authScreens.SignIn.AboutUserItem
import com.example.dailyshoes.ui.theme.Poppins_MEDIUM

object ProfileScreen {

    var profileName by mutableStateOf("Mr Singh")

    @Composable
    fun Profile() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    colorResource(id = R.color.home_background),
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            TitleBar("Checkout", img2 = R.drawable.ic_profile_edit, backPressed = { })
            ConstraintLayout(modifier = Modifier.fillMaxWidth()) {
                val (profileImage, imgBox) = createRefs()
                Image(
                    painter = painterResource(id = R.drawable.ic_shoe_4),
                    contentDescription = "",
                    modifier = Modifier
                        .size(120.dp)
                        .clip(CircleShape)
                        .clickable {  /*todo: Show Dialog For Pick Image From Camera & Gallery*/ }
                        .constrainAs(profileImage) {
                            top.linkTo(parent.top)
                            bottom.linkTo(parent.bottom)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        }
                        .background(color = Color.LightGray)
                )

                Box(
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(color = colorResource(id = R.color.intro_get_started))
                        .constrainAs(imgBox) {
                            top.linkTo(profileImage.bottom)
                            bottom.linkTo(profileImage.bottom)
                            start.linkTo(profileImage.start)
                            end.linkTo(profileImage.end)
                        }
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_pick_prfile_camera),
                        contentDescription = "", modifier = Modifier.padding(5.dp)
                    )
                }
            }

            Text(
                text = profileName,
                style = TextStyle(fontFamily = Poppins_MEDIUM, fontSize = 24.sp),
                modifier = Modifier.padding(vertical = 15.dp)
            )

            AboutUserItem()
            AboutUserItem(itemTitle = "Email", itemValue = "john.tyler@examplepetstore.com")
            AboutUserItem(itemTitle = "Password", itemValue = "**********")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfilePreview() {
    ProfileScreen.Profile()
}
