package com.example.dailyshoes.ui.firstDesign

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.dailyshoes.R
import com.example.dailyshoes.ui.theme.Poppins_MEDIUM
import com.example.dailyshoes.ui.theme.Poppins_Regular

class NotificationScreen {

    @Composable
    fun NotificationDesign() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    colorResource(id = R.color.home_background),
                    shape = RoundedCornerShape(2.5.dp)
                )
        ) {

            TitleBar({ }) {}
            NotificationItem()
        }
    }

    @Composable
    fun TitleBar(backPressed: () -> Unit, clearAll: () -> Unit) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp)
                .wrapContentHeight()
                .padding(horizontal = 20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Image(
                modifier = Modifier
                    .width(50.dp)
                    .height(50.dp)
                    .clickable { backPressed.invoke() },
                painter = painterResource(id = R.drawable.ic_back),
                contentDescription = "Drawer"
            )
            Text(
                modifier = Modifier.weight(1f),
                text = "Notifications",
                fontFamily = Poppins_MEDIUM,
                textAlign = TextAlign.Center,
            )
            Text(
                modifier = Modifier
                    .wrapContentWidth()
                    .clickable { clearAll.invoke() },
                text = "Clear All",
                fontFamily = Poppins_MEDIUM,
                textAlign = TextAlign.Center,
                style = TextStyle(color = colorResource(id = R.color.intro_get_started))
            )


        }
    }


    @Composable
    fun NotificationItem() {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp)
        ) {
            Card(
                modifier = Modifier
                    .width(100.dp)
                    .height(100.dp)
                    .padding(8.dp)
                    .clip(shape = RoundedCornerShape(15.dp)),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
            ) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_shoe_4),
                        contentDescription = ""
                    )

                    Text(text = "We Have New Products With Offers")
                }
            }
        }
    }


    @Preview(showBackground = true)
    @Composable
    fun ShowPreview() {
        NotificationDesign()
    }


}