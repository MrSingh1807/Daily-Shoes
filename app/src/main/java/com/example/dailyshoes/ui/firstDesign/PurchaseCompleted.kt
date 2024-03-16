package com.example.dailyshoes.ui.firstDesign

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dailyshoes.R
import com.example.dailyshoes.ui.activities.CartActivity.Companion.TitleBar
import com.example.dailyshoes.ui.theme.Poppins_Regular

class PurchaseCompleted {

    @Composable
    fun PurchaseCompletedScreen() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    colorResource(id = R.color.home_background),
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            TitleBar("Checkout")

            Image(
                painter = painterResource(id = R.drawable.purched_top),
                contentDescription = " ",
                modifier = Modifier.padding(vertical = 20.dp)
            )

            Text(
                text = "Order Completed", modifier = Modifier.padding(vertical = 20.dp),
                style = TextStyle(fontFamily = Poppins_Regular, fontSize = 24.sp)
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.order_purchased),
                    contentDescription = " "
                )

                Text(
                    text = "Thank you for your purchase.\n" +
                            "You can view your order in ‘My Orders’ section.",
                    style = TextStyle(
                        fontFamily = Poppins_Regular,
                        fontSize = 13.sp,
                        textAlign = TextAlign.Center
                    ),
                    modifier = Modifier.padding(vertical = 25.dp)
                )
            }

            Button(
                onClick = { },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                colors = ButtonDefaults.buttonColors(containerColor = colorResource(R.color.intro_get_started))
            ) {
                Text(
                    text = "Continue Shopping",
                    style = TextStyle(
                        fontFamily = Poppins_Regular,
                        fontSize = 13.sp,
                        textAlign = TextAlign.Center
                    )
                )
            }

        }
    }


    @Preview(showBackground = true)
    @Composable
    fun PurchaseCompletedScreenPreview() {
        PurchaseCompletedScreen()
    }

}