package com.example.dailyshoes.ui.activities.homeScreens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.dailyshoes.R
import com.example.dailyshoes.ui.navigation.HomeBottomNav
import com.example.dailyshoes.ui.theme.Poppins_MEDIUM
import com.example.dailyshoes.ui.theme.Poppins_SEMI_BOLD

object NotificationScreen {


    @Composable
    fun Notification() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    colorResource(id = R.color.home_background),
                    shape = RoundedCornerShape(2.5.dp)
                )
        ) {

            TitleBar({ }) {}
            val dummyList = listOf(1, 2, 3)
            val dummyDays = listOf("Today", "Yesterday", "1 day ago", "2 days ago")

            dummyDays.forEach {
                DayWiseNotifications(it, notifications = dummyList)
            }

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
                color = Color.Black
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
    fun DayWiseNotifications(
        day: String = "Today",
        notifications: List<Int>
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp, bottom = 10.dp)
                .padding(horizontal = 12.dp)
        ) {
            Text(
                text = day,
                style = TextStyle(
                    fontFamily = Poppins_SEMI_BOLD,
                    fontSize = 16.sp,
                    color = Color.Black
                ),
                modifier = Modifier.padding(start = 8.dp)
            )

            LazyColumn {
                items(notifications) {
                    NotificationItem()
                }
            }
        }
    }

    @Composable
    fun NotificationItem(
        newPrice: Double = 364.95,
        defaultPrice: Double = 260.00,
        actualTime: String = "6 min ago",
        onNotificationClick: () -> Unit = {}
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(onClick = onNotificationClick)
        ) {
            val (shoe, description, btmPrices, timeStamp, freshStamp) = createRefs()
            Card(
                modifier = Modifier
                    .width(100.dp)
                    .height(100.dp)
                    .padding(8.dp)
                    .constrainAs(shoe) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                    }
                    .clip(shape = RoundedCornerShape(15.dp)),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
            ) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_shoe_4),
                        contentDescription = ""
                    )
                }
            }

            Text(text = "We Have New \nProducts With Offers",
                style = TextStyle(
                    fontFamily = Poppins_MEDIUM,
                    fontSize = 14.sp,
                    color = Color.Black
                ),
                modifier = Modifier
                    .padding(top = 10.dp)
                    .constrainAs(description) {
                        top.linkTo(parent.top)
                        bottom.linkTo(btmPrices.top)
                        start.linkTo(shoe.end)
                    }
            )
            Row(modifier = Modifier
                .constrainAs(btmPrices) {
                    top.linkTo(description.bottom)
                    start.linkTo(description.start)
                    bottom.linkTo(parent.bottom)
                }) {
                Text(
                    text = "$ $newPrice",
                    style = TextStyle(
                        fontFamily = Poppins_MEDIUM,
                        fontSize = 16.sp,
                        color = Color.Black
                    ),
                )
                Text(
                    text = "$ $defaultPrice", modifier = Modifier.padding(start = 20.dp),
                    style = TextStyle(
                        fontFamily = Poppins_MEDIUM,
                        fontSize = 16.sp,
                        color = Color.Gray
                    ),
                )
            }

            Text(
                text = actualTime,
                style = TextStyle(
                    fontFamily = Poppins_MEDIUM,
                    fontSize = 12.sp,
                    color = Color.Gray
                ),
                modifier = Modifier
                    .padding(top = 5.dp, end = 10.dp)
                    .constrainAs(timeStamp) {
                        top.linkTo(description.top)
                        end.linkTo(parent.end)
                    }
            )

            Image(painter = painterResource(id = R.drawable.ic_un_read_noti),
                contentDescription = "Un Read",
                modifier = Modifier
                    .padding(end = 10.dp)
                    .constrainAs(freshStamp) {
                        bottom.linkTo(description.bottom)
                        top.linkTo(timeStamp.bottom)
                        end.linkTo(parent.end)
                    })
        }
    }


}

@Preview(showBackground = true)
@Composable
fun ShowPreview() {
    NotificationScreen.Notification()
}

