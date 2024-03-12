package com.example.dailyshoes.ui.firstDesign

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dailyshoes.R
import com.example.dailyshoes.ui.theme.Poppins_BOLD
import com.example.dailyshoes.ui.theme.Poppins_Regular
import com.example.dailyshoes.ui.theme.Poppins_SEMI_BOLD

class SideMenu {

    @Composable
    fun SideMenuScreen() {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    colorResource(id = R.color.side_menu_background),
                ),
        ) {
            val modifier = Modifier
                .fillMaxHeight()
            Column(
                modifier.weight(2f),
                verticalArrangement = Arrangement.SpaceAround
            ) {
                About()
                SideFunctions()
            }
            Column(
                modifier.weight(1f),
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.side_menu_img),
                    contentDescription = ""
                )
            }
        }
    }

    @Composable
    fun About(
        profileImage: Bitmap? = null,
        name: String = "Mr Singh",
        quote: String = "I am a developer"
    ) {
        Column(modifier = Modifier.padding(top = 50.dp, start = 10.dp)) {
            Image(
                painter = painterResource(id = R.drawable.order_purchased),
                contentDescription = "Profile Image"
            )
            Text(
                text = quote, style = TextStyle(
                    fontFamily = Poppins_Regular, color = colorResource(
                        id = R.color.side_menu_text
                    )
                )
            )
            Text(
                text = name, style = TextStyle(
                    fontFamily = Poppins_BOLD, color = colorResource(
                        id = R.color.white
                    ), fontSize = 28.sp
                )
            )
        }
    }

    @Composable
    fun SideFunctions() {
        Column(modifier = Modifier.padding(vertical = 20.dp, horizontal = 10.dp)) {
            val sideItemList = listOf(
                R.drawable.ic_side_profile to "Profile",
                R.drawable.ic_side_home to "Home Page",
                R.drawable.ic_side_my_cart to "My Cart",
                R.drawable.ic_side_favourites to "Favourites",
                R.drawable.ic_side_order to "Orders",
                R.drawable.ic_side_notifications to "Notifications",
            )

            sideItemList.forEach {
                Function(image = it.first, title = it.second)
            }

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 20.dp)
                    .padding(top = 20.dp)
                    .height(2.dp)
                    .background(
                        color = colorResource(
                            id = R.color.side_menu_text
                        )
                    ),
            )
            Function(image = R.drawable.ic_side_sign_out, title = "Sign Out")
        }
    }


    @Composable
    fun Function(
        image: Int, title: String, onItemClicked: () -> Unit = {}
    ) {
        Row(
            modifier = Modifier
                .padding(top = 20.dp, end = 20.dp)
                .clickable { onItemClicked.invoke() },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(painter = painterResource(id = image), contentDescription = "")
            Text(
                text = title,
                style = TextStyle(
                    fontFamily = Poppins_SEMI_BOLD, fontSize = 14.sp,
                    color = colorResource(id = R.color.white)
                ),
                modifier = Modifier.padding(start = 20.dp)
            )
        }
    }


    @Preview
    @Composable
    fun SideMenuScreenPreview() {
        SideMenuScreen()
    }
}