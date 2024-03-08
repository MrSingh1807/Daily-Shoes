package com.example.dailyshoes.ui.firstDesign.orderScreens

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dailyshoes.R
import com.example.dailyshoes.ui.firstDesign.CartScreen.Companion.TitleBar
import com.example.dailyshoes.ui.modals.AboutOrder
import com.example.dailyshoes.ui.theme.Poppins_MEDIUM
import com.example.dailyshoes.ui.theme.Poppins_Regular
import com.example.dailyshoes.ui.theme.Poppins_SEMI_BOLD

class OrderDetails {

    @Composable
    fun OrderDetail() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    colorResource(id = R.color.home_background),
                ),
        ) {
            val orderID = "123456"
            TitleBar("Order #$orderID")

            TrackOrderStatus()
            OrderAddress()

            val dummyList = listOf(
                AboutOrder("Sportswear Set", 80, 1),
                AboutOrder("Cotton T-shirt", 30, 3),
                AboutOrder("Jeans Pant", 80, 2),
            )
            AboutOrder(dummyList)

            Spacer(modifier = Modifier.weight(1f))
            Button(
                onClick = { /*TODO*/ }, modifier = Modifier
                    .padding(20.dp)
                    .fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.intro_get_started))
            ) {
                Text(text = "Continue Shopping", style = TextStyle(fontFamily = Poppins_MEDIUM))
            }
        }
    }

    @Composable
    fun TrackOrderStatus(trackOrder: () -> Unit = {}) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp, horizontal = 20.dp)
                .clickable { },
            colors = CardDefaults.cardColors(containerColor = colorResource(id = R.color.intro_get_started)),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
            shape = RoundedCornerShape(30.dp)
        ) {
            Row(
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Column {
                    Text(
                        text = "Your order is on the way",
                        style = TextStyle(
                            fontFamily = Poppins_MEDIUM,
                            fontSize = 17.sp,
                            color = Color.White
                        )
                    )
                    Text(
                        text = "Click here to track your order",
                        style = TextStyle(
                            fontFamily = Poppins_MEDIUM,
                            fontSize = 12.sp,
                            color = Color.White
                        )
                    )
                }
                Image(
                    painter = painterResource(id = R.drawable.order_on_way),
                    contentDescription = "On the way"
                )
            }
        }
    }

    @Composable
    fun OrderAddress(
        orderNumber: String = "123456",
        trackingNumber: String = "IK287368838",
        deliveryAddress: String = "SBI Building, Software Park",
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
                    .padding(15.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.Bottom,
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
                    Spacer(
                        modifier = Modifier.weight(1f)
                    )
                    Text(
                        text = "# $orderNumber",
                        style = TextStyle(
                            fontFamily = Poppins_Regular,
                            fontSize = 13.sp,
                        )
                    )
                }

                Row(
                    modifier = Modifier.padding(top = 10.dp),
                    verticalAlignment = Alignment.Bottom,
                ) {
                    Text(
                        text = "Tracking Number ",
                        style = TextStyle(
                            fontFamily = Poppins_MEDIUM,
                            fontSize = 13.sp,
                            color = Color.Gray
                        ),
                        modifier = Modifier.padding(end = 6.dp)
                    )
                    Spacer(
                        modifier = Modifier.weight(1f)
                    )
                    Text(
                        text = "$trackingNumber",
                        style = TextStyle(
                            fontFamily = Poppins_Regular,
                            fontSize = 13.sp,
                        )
                    )
                }

                Row(
                    modifier = Modifier.padding(top = 10.dp),
                    verticalAlignment = Alignment.Bottom,
                ) {
                    Text(
                        text = "Delivery Address ",
                        style = TextStyle(
                            fontFamily = Poppins_MEDIUM,
                            fontSize = 13.sp,
                            color = Color.Gray
                        ),
                        modifier = Modifier.padding(end = 6.dp)
                    )
                    Spacer(
                        modifier = Modifier.weight(1f)
                    )
                    Text(
                        text = "$deliveryAddress",
                        style = TextStyle(
                            fontFamily = Poppins_Regular,
                            fontSize = 13.sp,
                        )
                    )
                }
            }
        }
    }

    @Composable
    fun AboutOrder(
        orderItems: List<AboutOrder>,
        shippingCost: Int = 0,
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp, horizontal = 20.dp)
                .clickable { },
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(15.dp)
            ) {

                orderItems.forEach {
                    Row(
                        modifier = Modifier.padding(top = 5.dp),
                        verticalAlignment = Alignment.Bottom,
                    ) {
                        Text(
                            text = "${it.orderName}",
                            style = TextStyle(
                                fontFamily = Poppins_Regular,
                                fontSize = 13.sp,
                                color = Color.Gray
                            ),
                            modifier = Modifier.padding(end = 6.dp)
                        )
                        Spacer(
                            modifier = Modifier.weight(1f)
                        )
                        Text(
                            modifier = Modifier.padding(end = 20.dp),
                            text = "x ${it.quantity} ",
                            style = TextStyle(
                                fontFamily = Poppins_Regular,
                                fontSize = 13.sp,
                            )
                        )
                        Text(
                            text = "$ ${it.quantity * it.price}.00",
                            style = TextStyle(
                                fontFamily = Poppins_MEDIUM,
                                fontSize = 13.sp,
                            )
                        )
                    }
                }

                var subtotal = 0
                orderItems.map { it.quantity to it.price }.forEach {
                    subtotal += it.first * it.second
                }

                Spacer(modifier = Modifier.padding(top = 25.dp))
                listOf("Subtotal", "Shipping").forEachIndexed { index, s ->
                    Row(
                        modifier = Modifier.padding(top = 5.dp),
                        verticalAlignment = Alignment.Bottom,
                    ) {
                        Text(
                            text = "$s",
                            style = TextStyle(
                                fontFamily = Poppins_Regular,
                                fontSize = 13.sp,
                                color = Color.Gray
                            ),
                            modifier = Modifier.padding(end = 6.dp)
                        )
                        Spacer(
                            modifier = Modifier.weight(1f)
                        )
                        val price = if (index == 0) subtotal else shippingCost
                        Text(
                            text = "$ ${price}.00",
                            style = TextStyle(
                                fontFamily = Poppins_SEMI_BOLD,
                                fontSize = 13.sp,
                            )
                        )
                    }
                }

                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp)
                        .height(1.dp)
                        .background(color = Color.LightGray)
                )

                Row(
                    modifier = Modifier.padding(top = 5.dp),
                    verticalAlignment = Alignment.Bottom,
                ) {
                    Text(
                        text = "Total",
                        style = TextStyle(
                            fontFamily = Poppins_Regular,
                            fontSize = 13.sp,
                            color = Color.Gray
                        ),
                        modifier = Modifier.padding(end = 6.dp)
                    )
                    Spacer(
                        modifier = Modifier.weight(1f)
                    )

                    Text(
                        text = "$ ${subtotal + shippingCost}.00",
                        style = TextStyle(
                            fontFamily = Poppins_SEMI_BOLD,
                            fontSize = 13.sp,
                        )
                    )
                }

            }
        }
    }


    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        OrderDetail()
    }

}