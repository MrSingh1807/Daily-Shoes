package com.example.dailyshoes.ui.activities

import androidx.activity.viewModels
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
import com.example.dailyshoes.R
import com.example.dailyshoes.ui.baseClasses.BaseComposeActivity
import com.example.dailyshoes.ui.modals.AboutOrder
import com.example.dailyshoes.ui.theme.Poppins_MEDIUM
import com.example.dailyshoes.ui.theme.Poppins_SEMI_BOLD
import com.example.dailyshoes.ui.viewModel.CartVM
import dagger.hilt.android.AndroidEntryPoint
import java.math.RoundingMode
import java.text.DecimalFormat

@AndroidEntryPoint
class CartActivity : BaseComposeActivity() {

    val mViewModel: CartVM by viewModels()

    @Composable
    override fun InitScreen() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    colorResource(id = R.color.home_background),
                )
        ) {

            TitleBar("My Cart", backPressed = { finish() })

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp)
                    .weight(1f)
            ) {
                items(mViewModel.cartList.value) {
                    CartItem(
                        it,
                        removeQuantity = { mViewModel.updateQuantity(it.id, it.quantity - 1) },
                        addQuantity = { mViewModel.updateQuantity(it.id, it.quantity + 1) },
                        deleteItem = { mViewModel.deleteShoe(it.id) }
                    )
                }
            }

            CheckoutBottomBar(prices = mViewModel.prices.value)
        }
    }


    @Composable
    fun CartItem(
        aboutOrder: AboutOrder,
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
                        painter = painterResource(id = R.drawable.ic_shoe_4),
                        contentDescription = ""
                    )
                }
            }

            Column(modifier = Modifier.padding(horizontal = 10.dp)) {
                Row(modifier = Modifier.padding(vertical = 7.dp)) {
                    Text(text = aboutOrder.orderName, fontFamily = Poppins_MEDIUM)
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        text = aboutOrder.shoeSize.toString(),
                        fontFamily = Poppins_MEDIUM,
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )
                }

                Text(text = "$ ${aboutOrder.price}", fontFamily = Poppins_MEDIUM)

                Row(modifier = Modifier.padding(vertical = 7.dp)) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_remove_item),
                        contentDescription = "RemoveItem",
                        modifier = Modifier.clickable {
                            removeQuantity.invoke()
                        }
                    )
                    Text(
                        text = aboutOrder.quantity.toString(),
                        fontFamily = Poppins_MEDIUM,
                        modifier = Modifier.padding(horizontal = 15.dp)
                    )
                    Image(
                        painter = painterResource(id = R.drawable.ic_add_item),
                        contentDescription = "RemoveItem",
                        modifier = Modifier.clickable {
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
            img2: Int? = null,
            backPressed: () -> Unit = {},
            second: () -> Unit = {},
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

                val height = if (img2 != null) 50.dp else 0.dp
                val secImg = img2 ?: R.drawable.ic_back
                Image(
                    modifier = Modifier
                        .width(50.dp)
                        .height(height)
                        .padding(10.dp)
                        .clickable { second.invoke() },
                    painter = painterResource(id = secImg),
                    contentDescription = "Drawer"
                )
            }
        }

        @Composable
        fun CheckoutBottomBar(
            modifier: Modifier = Modifier,
            prices: Pair<Double, Double> = 1240.34 to 40.34,
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
                        text = "$${roundOffDecimal(prices.first)}",
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
                        text = "$${roundOffDecimal(prices.second)}",
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
                        text = "$${roundOffDecimal(prices.first + prices.second)}",
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

        fun roundOffDecimal(number: Double): Double {
            val df = DecimalFormat("#.##")
            df.roundingMode = RoundingMode.CEILING
            return df.format(number).toDouble()
        }

    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        InitScreen()
    }

}