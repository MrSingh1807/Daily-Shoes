package com.example.dailyshoes.ui.firstDesign

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import com.example.dailyshoes.R
import com.example.dailyshoes.ui.activities.CartActivity.Companion.CheckoutBottomBar
import com.example.dailyshoes.ui.activities.CartActivity.Companion.TitleBar
import com.example.dailyshoes.ui.theme.Poppins_MEDIUM
import com.example.dailyshoes.ui.theme.Poppins_Regular

class CheckOutScreen {

    var location by mutableStateOf("Newahall St 36, London, 12908 - UK")

    @Composable
    fun CheckOutDesign() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    colorResource(id = R.color.home_background),
                )
        ) {
            TitleBar("Checkout")
            ContactInfo()

            Spacer(modifier = Modifier.weight(1f))

            CheckoutBottomBar(modifier = Modifier)

        }
    }

    @Composable
    fun ContactInfo(
        locationExpend: () -> Unit = {},
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp, horizontal = 20.dp)
                .clip(shape = RoundedCornerShape(15.dp)),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(500.dp)
                    .padding(horizontal = 20.dp, vertical = 15.dp)
            ) {
                Text(text = "Contact Information", style = TextStyle(fontFamily = Poppins_MEDIUM))

                Info(icon = R.drawable.ic_email, "examle@gmail.com", "Email")
                Info(icon = R.drawable.ic_email, "+91-8899992290", "Email")

                Text(
                    text = "Address",
                    style = TextStyle(fontFamily = Poppins_MEDIUM),
                    modifier = Modifier.padding(top = 20.dp)
                )

                Row(
                    modifier = Modifier.padding(top = 10.dp)
                ) {
                    Text(
                        text = location,
                        style = TextStyle(
                            fontFamily = Poppins_Regular,
                            color = Color.Gray,
                            fontSize = 12.sp
                        )
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Image(
                        painter = painterResource(id = R.drawable.ic_show_expend),
                        contentDescription = "",
                        modifier = Modifier.clickable { }
                    )
                }

                MapView()

                Text(
                    text = "Payment Method",
                    style = TextStyle(fontFamily = Poppins_MEDIUM),
                    modifier = Modifier.padding(top = 20.dp)
                )

                Info(
                    icon = R.drawable.ic_paypal,
                    "Paypal Card",
                    "**** **** 0696 4629",
                    editIcon = R.drawable.ic_show_expend
                )

            }
        }
    }

    @Composable
    fun Info(
        icon: Int, text1: String, text2: String,
        editIcon: Int = R.drawable.ic_edit,
        editListener: () -> Unit = {}
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(painter = painterResource(id = icon), contentDescription = " ")

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 10.dp)
            ) {
                Text(
                    text = text1,
                    style = TextStyle(fontFamily = Poppins_Regular, fontSize = 12.sp)
                )
                Text(
                    text = text2,
                    style = TextStyle(
                        fontFamily = Poppins_Regular,
                        fontSize = 10.sp,
                        color = Color.Gray
                    )
                )
            }

            Image(
                painter = painterResource(id = editIcon),
                contentDescription = " ",
                modifier = Modifier.clickable { editListener.invoke() })
        }
    }

    @Composable
    fun MapView(viewGone: Boolean = false) {
        val modifier = if (viewGone) Modifier
            .width(0.dp)
            .height(0.dp)
        else Modifier
            .fillMaxWidth()
            .height(130.dp)


        Card(
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 10.dp)
                .clip(shape = RoundedCornerShape(15.dp))
        ) {

        }
    }


    @Preview(showBackground = true)
    @Composable
    fun CheckOutDesignPreview() {
        CheckOutDesign()
    }

}