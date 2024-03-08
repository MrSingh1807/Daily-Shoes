package com.example.dailyshoes.ui.firstDesign.orderScreens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dailyshoes.R
import com.example.dailyshoes.ui.enums.OrderStatus
import com.example.dailyshoes.ui.theme.Poppins_MEDIUM

class MyOrdersScreen {

    var orderStatus by mutableIntStateOf(0)


    @Composable
    fun MyOrdersDesign() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    colorResource(id = R.color.home_background),
                ),
        ) {
            TitleBar("My Orders")

            OrderStatus()

            val dummyList = listOf(1, 2, 3, 4, 5)
            LazyColumn {
                items(dummyList) {
                    OrderItem()
                }
            }

        }
    }

    @Composable
    fun TitleBar(
        title: String,
        modifier: Modifier = Modifier,
        options: () -> Unit = {},
        notification: () -> Unit = {},
    ) {
        Row(
            modifier = modifier
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
                    .clickable { options.invoke() },
                painter = painterResource(id = R.drawable.ic_home_drawer),
                contentDescription = "Drawer"
            )
            Text(
                modifier = Modifier.weight(1f),
                text = title,
                fontFamily = Poppins_MEDIUM,
                textAlign = TextAlign.Center,
            )
            Image(
                modifier = Modifier
                    .width(50.dp)
                    .height(50.dp)
                    .clickable { notification.invoke() },
                painter = painterResource(id = R.drawable.ic_notification),
                contentDescription = "Drawer"
            )
        }
    }

    @Composable
    fun OrderStatus(
        pendingClick: () -> Unit = { orderStatus = 0 },
        deliveredClick: () -> Unit = { orderStatus = 1 },
        cancelledClick: () -> Unit = { orderStatus = 2 },
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 20.dp)
        ) {

            val modifier = Modifier
                .weight(1f)
                .height(35.dp)

            Button(
                onClick = { pendingClick.invoke() }, modifier = modifier,
                colors = ButtonDefaults.buttonColors(orderStatus backgroundColor 0)
            ) {
                Text(
                    text = "Pending", style = TextStyle(
                        fontFamily = Poppins_MEDIUM, fontSize = 12.sp,
                        color = orderStatus selectedTextColor 0
                    )
                )
            }
            Spacer(modifier = Modifier.width(10.dp))

            Button(
                onClick = { deliveredClick.invoke() }, modifier = modifier,
                colors = ButtonDefaults.buttonColors(orderStatus backgroundColor 1)
            ) {
                Text(
                    text = "Delivered",
                    style = TextStyle(
                        fontFamily = Poppins_MEDIUM, fontSize = 12.sp,
                        color = orderStatus selectedTextColor 1
                    )
                )
            }

            Spacer(modifier = Modifier.width(10.dp))

            Button(
                onClick = { cancelledClick.invoke() }, modifier = modifier,
                colors = ButtonDefaults.buttonColors(orderStatus backgroundColor 2)
            ) {
                Text(
                    text = "Cancelled", style = TextStyle(
                        fontFamily = Poppins_MEDIUM, fontSize = 12.sp,
                        color = orderStatus selectedTextColor 2
                    )
                )
            }

        }
    }

    @Composable
    fun OrderItem(
        orderNumber: String = "1524",
        date: String = "12/03/2023",
        trackingNumber: String = "IK287368838",
        quantity: Int = 2,
        price: Int = 100,
        status: OrderStatus = OrderStatus.PENDING,
        showDetails: () -> Unit = {},
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp, horizontal = 20.dp)
                .clickable { },
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(15.dp)
            ) {
                Row(verticalAlignment = Alignment.Bottom) {
                    Text(
                        text = "Order #$orderNumber",
                        style = TextStyle(fontFamily = Poppins_MEDIUM, fontSize = 16.sp)
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        text = "$date",
                        style = TextStyle(
                            fontFamily = Poppins_MEDIUM,
                            fontSize = 13.sp,
                            color = Color.Gray
                        )
                    )
                }

                val topPadding = Modifier.padding(top = 10.dp)
                Row(
                    verticalAlignment = Alignment.Bottom, modifier = topPadding
                ) {
                    Text(
                        text = "Tracking Number :",
                        style = TextStyle(
                            fontFamily = Poppins_MEDIUM,
                            fontSize = 13.sp,
                            color = Color.Gray
                        ),
                        modifier = Modifier.padding(end = 15.dp)
                    )
                    Text(
                        text = "$trackingNumber",
                        style = TextStyle(
                            fontFamily = Poppins_MEDIUM,
                            fontSize = 13.sp,
                        )
                    )
                }

                Row(
                    verticalAlignment = Alignment.Bottom, modifier = topPadding
                ) {
                    Text(
                        text = "Quantity: ",
                        style = TextStyle(
                            fontFamily = Poppins_MEDIUM,
                            fontSize = 13.sp,
                            color = Color.Gray
                        ),
                        modifier = Modifier.padding(end = 6.dp)
                    )
                    Text(
                        text = "$quantity",
                        style = TextStyle(
                            fontFamily = Poppins_MEDIUM,
                            fontSize = 13.sp,
                        )
                    )
                    Spacer(
                        modifier = Modifier.weight(1f)
                    )
                    Text(
                        text = "Subtotal: ",
                        style = TextStyle(
                            fontFamily = Poppins_MEDIUM,
                            fontSize = 13.sp,
                            color = Color.Gray
                        ),
                        modifier = Modifier.padding(end = 6.dp)
                    )
                    Text(
                        text = "$ ${price * quantity}",
                        style = TextStyle(
                            fontFamily = Poppins_MEDIUM,
                            fontSize = 13.sp,
                        )
                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = topPadding
                ) {
                    val statusColor = when (status) {
                        OrderStatus.PENDING -> colorResource(id = R.color.order_pending)
                        OrderStatus.DELIVERED -> colorResource(id = R.color.order_delivered)
                        OrderStatus.CANCELLED -> colorResource(id = R.color.order_cancelled)
                    }
                    Text(
                        text = "$status",
                        style = TextStyle(
                            fontFamily = Poppins_MEDIUM,
                            fontSize = 13.sp,
                            color = statusColor
                        ),
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Button(
                        onClick = { showDetails.invoke() },
                        border = BorderStroke(1.dp, Color.Black),
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                    ) {
                        Text(
                            text = "Details", modifier = Modifier.padding(horizontal = 10.dp),
                            style = TextStyle(
                                fontFamily = Poppins_MEDIUM,
                                fontSize = 13.sp, color = Color.Black
                            )
                        )
                    }
                }

            }
        }
    }

    @Composable
    infix fun Int.backgroundColor(n: Int) =
        if (this == n) colorResource(id = R.color.intro_get_started) else Color.Transparent

    @Composable
    infix fun Int.selectedTextColor(n: Int) =
        if (this == n) Color.White else Color.Black

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        MyOrdersDesign()
    }
}