package com.example.dailyshoes.ui.firstDesign

import android.icu.text.CaseMap.Title
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
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
import com.example.dailyshoes.ui.theme.Poppins_SEMI_BOLD

class CartScreen {


    var addedQuantity by mutableIntStateOf(1)

    @Composable
    fun MyCartScreen() {

        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    colorResource(id = R.color.home_background),
                )
        ) {
            val (titleBar, centerScroll, bottomBar) = createRefs()

            val modifier = Modifier.constrainAs(titleBar) {
                top.linkTo(parent.top)
            }
            TitleBar("My Cart", modifier)

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp)
                    .constrainAs(centerScroll) {
                        top.linkTo(titleBar.bottom)
                    }
            ) {
                items(dummyList) {
                    CartItem()
                }
            }

            CheckoutBottomBar(modifier = Modifier.constrainAs(bottomBar) {
                bottom.linkTo(parent.bottom)
            })


        }
    }


    @Composable
    fun CartItem(
        shoeName: String = "Nike Air Max 97",
        shoeSize: String = "L",
        shoeImage: Int = R.drawable.ic_shoe_4,
        shoePrice: Double = 120.34,
        removeQuantity: () -> Unit = {},
        addQuantity: () -> Unit = {},
        deleteItem: () -> Unit = {},
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp)
                .wrapContentHeight()
                .padding(horizontal = 12.dp),
        ) {
            Card(
                modifier = Modifier
                    .width(100.dp)
                    .height(100.dp)
                    .padding(6.dp)
                    .clip(shape = RoundedCornerShape(15.dp)),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)
            ) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Image(
                        painter = painterResource(id = shoeImage),
                        contentDescription = ""
                    )
                }
            }

            Column(modifier = Modifier.padding(horizontal = 10.dp)) {
                Row(modifier = Modifier.padding(vertical = 7.dp)) {
                    Text(text = shoeName, fontFamily = Poppins_MEDIUM)
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        text = shoeSize,
                        fontFamily = Poppins_MEDIUM,
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )
                }

                Text(text = "$ $shoePrice", fontFamily = Poppins_MEDIUM)

                Row(modifier = Modifier.padding(vertical = 7.dp)) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_remove_item),
                        contentDescription = "RemoveItem",
                        modifier = Modifier.clickable {
                            addedQuantity--
                            removeQuantity.invoke()
                        }
                    )
                    Text(
                        text = addedQuantity.toString(),
                        fontFamily = Poppins_MEDIUM,
                        modifier = Modifier.padding(horizontal = 15.dp)
                    )
                    Image(
                        painter = painterResource(id = R.drawable.ic_add_item),
                        contentDescription = "RemoveItem",
                        modifier = Modifier.clickable {
                            addedQuantity++
                            addQuantity.invoke()
                        }
                    )
                    Spacer(modifier = Modifier.weight(1f))

                    Image(
                        painter = painterResource(id = R.drawable.ic_detele_item),
                        contentDescription = "RemoveItem",
                        modifier = Modifier.clickable(onClick = deleteItem)
                    )
                }
            }
        }
    }


    companion object {

        @Composable
        fun TitleBar(
            title: String,
            modifier: Modifier = Modifier,
            backPressed: () -> Unit = {},
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
                        .clickable { backPressed.invoke() },
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = "Drawer"
                )
                Text(
                    modifier = Modifier.weight(1f),
                    text = title,
                    fontFamily = Poppins_MEDIUM,
                    textAlign = TextAlign.Center,
                )

                Box(
                    modifier = Modifier
                        .width(50.dp)
                        .height(50.dp)
                )
            }
        }

        @Composable
        fun CheckoutBottomBar(
            modifier: Modifier,
            subTotal: Double = 1240.34,
            shippingCost: Double = 40.34,
            totalCost: Double = 1280.34,
            btnText: String = "Checkout",
            checkout: () -> Unit = {},
        ) {
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .background(
                        color = Color.White, shape = RoundedCornerShape(30.dp)
                    )
            ) {
                Row(
                    modifier = Modifier
                        .padding(horizontal = 20.dp)
                        .padding(top = 20.dp),
                ) {
                    Text(
                        text = "Subtotal",
                        style = TextStyle(fontFamily = Poppins_MEDIUM, color = Color.Gray)
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        text = "$$subTotal",
                        style = TextStyle(fontFamily = Poppins_SEMI_BOLD, color = Color.Black)
                    )
                }
                Row(
                    modifier = Modifier
                        .padding(horizontal = 20.dp)
                ) {
                    Text(
                        text = "Shipping Cost",
                        style = TextStyle(fontFamily = Poppins_MEDIUM, color = Color.Gray)
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        text = "$$shippingCost",
                        style = TextStyle(fontFamily = Poppins_SEMI_BOLD, color = Color.Black)
                    )
                }

                Spacer(modifier = modifier.height(10.dp))

                Row(modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp)) {
                    val arr = (1..50).toList()
                    arr.forEach {
                        Image(
                            painter = painterResource(id = R.drawable.divide_line),
                            contentDescription = "",
                            modifier = Modifier.padding(horizontal = 1.5.dp)
                        )
                    }
                }


                Row(
                    modifier = Modifier
                        .padding(horizontal = 20.dp)
                ) {
                    Text(
                        text = "Total Cost",
                        style = TextStyle(fontFamily = Poppins_MEDIUM, color = Color.Black)
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        text = "$$totalCost",
                        style = TextStyle(fontFamily = Poppins_SEMI_BOLD, color = Color.Black)
                    )
                }

                Button(
                    onClick = checkout,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 15.dp)
                        .padding(horizontal = 20.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = colorResource(R.color.intro_get_started))
                ) {
                    Text(text = btnText, style = TextStyle(fontFamily = Poppins_SEMI_BOLD))
                }
            }
        }

    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        MyCartScreen()
    }

}