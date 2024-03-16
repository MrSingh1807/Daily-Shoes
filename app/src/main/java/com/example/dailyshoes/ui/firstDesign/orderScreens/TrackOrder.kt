package com.example.dailyshoes.ui.firstDesign.orderScreens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dailyshoes.R
import com.example.dailyshoes.ui.activities.CartActivity.Companion.TitleBar
import com.example.dailyshoes.ui.theme.Poppins_Regular
import com.example.dailyshoes.ui.theme.Poppins_SEMI_BOLD


class TrackOrder {

    var rateCount = mutableIntStateOf(2)

    @Composable
    fun TrackOrderScreen() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    colorResource(id = R.color.home_background),
                ),
        ) {
            val orderID = "123456"
            TitleBar("Order #$orderID")
            About()

            val statusList = listOf(
                "Sender is preparing to ship your order" to "12 May 10:01",
                "Sender has shipped your parcel" to "12 May 14:25",
                "Parcel is in transit " to "13 May 07:00",
                "Parcel is received at delivery Branch" to "13 May 17:00",
                "Parcel is out for delivery" to "14 may 08:00",
                "Parcel is successfully delivered" to "15 May 10:20",
            )
            statusList.reversed().forEachIndexed { index, pair ->
                val updated = pair
                val hideBtm = if (index == statusList.lastIndex) false else true
                Status(
                    if (index != 0) R.drawable.ic_order_check else R.drawable.ic_fill_circle,
                    updated.first, updated.second, hideBtm
                )
            }

            Spacer(modifier = Modifier.weight(1f))
            Rate()
        }
    }

    @Composable
    fun About(
        date: String = "15.05.21",
        trackingNumber: String = "IK287368838"
    ) {
        Column(modifier = Modifier.padding(horizontal = 20.dp, vertical = 15.dp)) {
            Row {
                Text(
                    text = "Delivered on  ",
                    style = TextStyle(
                        color = Color.Gray,
                        fontFamily = Poppins_Regular,
                        fontSize = 13.sp
                    )
                )

                Text(
                    text = date,
                    style = TextStyle(
                        color = Color.Black,
                        fontFamily = Poppins_Regular,
                        fontSize = 13.sp
                    )
                )
            }
            Row(modifier = Modifier.padding(top = 8.dp)) {
                Text(
                    text = "Tracking Number  ",
                    style = TextStyle(
                        color = Color.Gray,
                        fontFamily = Poppins_Regular,
                        fontSize = 13.sp
                    )
                )

                Text(
                    text = trackingNumber,
                    style = TextStyle(
                        color = Color.Black,
                        fontFamily = Poppins_Regular,
                        fontSize = 13.sp
                    )
                )
            }
        }

    }


    @Composable
    fun Status(
        statusImg: Int = R.drawable.ic_fill_circle,
        parcelStatus: String = "Parcel is successfully delivered",
        updateStatusDate: String = "15 May 10:20",
        hideBtm: Boolean = false
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 5.dp),
        ) {
            Row {
                Card(
                    modifier = Modifier
                        .height(20.dp)
                        .width(20.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.Transparent),
                    border = BorderStroke(1.dp, colorResource(id = R.color.intro_get_started))
                ) {
                    Image(
                        painter = painterResource(id = statusImg),
                        contentDescription = "check",
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(3.dp)
                    )
                }

                Text(
                    text = parcelStatus,
                    style = TextStyle(fontFamily = Poppins_Regular, fontSize = 13.sp),
                    modifier = Modifier.padding(horizontal = 10.dp)
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = updateStatusDate,
                    style = TextStyle(
                        color = Color.Gray,
                        fontFamily = Poppins_Regular,
                        fontSize = 12.sp
                    ),
                )
            }
            val demoList = listOf(1, 2, 3)
            val modifier = if (hideBtm) Modifier else Modifier
                .width(0.dp)
                .height(0.dp)
            demoList.forEach {
                Image(
                    painter = painterResource(id = R.drawable.ic_fill_circle),
                    contentDescription = "check",
                    modifier = modifier
                        .width(15.dp)
                        .height(15.dp)
                        .padding(2.dp)
                        .padding(start = 6.dp)
                )
            }
        }

    }

    @Composable
    fun Rate() {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
                .background(color = Color.White, shape = RoundedCornerShape(10.dp))
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_rate_icon),
                contentDescription = "rate",
                modifier = Modifier
                    .padding(20.dp)
                    .height(50.dp)
                    .width(50.dp)
            )

            Column(modifier = Modifier.padding(vertical = 10.dp)) {
                Text(
                    text = "Don't forget to rate",
                    style = TextStyle(
                        fontSize = 12.sp,
                        fontFamily = Poppins_SEMI_BOLD
                    )
                )
                Text(
                    text = "Rate product to get 5 points for collect",
                    style = TextStyle(
                        color = Color.Gray,
                        fontSize = 12.sp,
                        fontFamily = Poppins_Regular
                    )
                )
                RatingBar(rateCount = rateCount.intValue)
            }
        }
    }

    companion object {

        @Composable
        fun RatingBar(
            rowModifier: Modifier = Modifier,
            starModifier: Modifier = Modifier,
            rateCount: Int = 3,
            maxRate: Int = 5,
            onRate: (Int) -> Unit = {}
        ) {
            Row(modifier = rowModifier, horizontalArrangement = Arrangement.Center) {
                (1..maxRate).forEach {
                    val painterIcon =
                        if (it <= rateCount) R.drawable.ic_selected_start else R.drawable.rating_icon
                    Image(
                        painter = painterResource(id = painterIcon),
                        contentDescription = "Rate Star",
                        modifier = starModifier
                            .padding(end = 3.dp)
                            .clickable { onRate.invoke(it) },
                    )
                }
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        TrackOrderScreen()
    }
}