package com.example.dailyshoes.ui.firstDesign

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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
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
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.dailyshoes.R
import com.example.dailyshoes.ui.theme.Poppins_MEDIUM
import com.example.dailyshoes.ui.theme.Poppins_Regular
import com.example.dailyshoes.ui.theme.Poppins_SEMI_BOLD


class ItemDetailsScreen {

    var defaultCity by mutableStateOf("EU")
    var selectedSize by mutableIntStateOf(38)

    @Composable
    fun ItemDetails() {
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
            TitleBar(modifier)
            
            Column(
                modifier = Modifier
                    .wrapContentSize()
                    .constrainAs(centerScroll) {
                        top.linkTo(titleBar.bottom)
                        bottom.linkTo(bottomBar.top)
                    }
                    .verticalScroll(rememberScrollState())
            ) {
                ItemImage(img = R.drawable.item_details_1)
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            color = Color.White,
                            shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp)
                        )
                ) {
                    AboutItem(
                        tag = "BEST SELLER",
                        shoeName = "Nike Air Jordan",
                        shoePrice = 967.800,
                        about = about
                    )

//                    Gallery(dummyList, {})
                    Size()
                }

            }

            val btnModifier = Modifier.constrainAs(bottomBar) {
                bottom.linkTo(parent.bottom)
            }
            BottomLayout(btnModifier)
        }

    }

    @Composable
    fun TitleBar(modifier: Modifier, cartClick: () -> Unit = {}) {
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
                    .clickable { },
                painter = painterResource(id = R.drawable.ic_back),
                contentDescription = "Drawer"
            )
            Text(
                modifier = Modifier.weight(1f),
                text = "Men's Shoes",
                fontFamily = Poppins_Regular,
                textAlign = TextAlign.Center,
            )

            Image(
                modifier = Modifier.clickable { cartClick.invoke() },
                painter = painterResource(id = R.drawable.ic_cart),
                contentDescription = "Drawer"
            )


        }
    }

    @Composable
    fun ItemImage(img: Int) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            val (shoes, imgBtm) = createRefs()
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .height(300.dp)
                    .constrainAs(shoes) {},
                painter = painterResource(id = img), contentDescription = " Item Img"
            )

            Image(painter = painterResource(id = R.drawable.item_details_bottom),
                contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp)
                    .constrainAs(imgBtm) {
                        bottom.linkTo(shoes.bottom)
                    })

        }
    }

    @Composable
    fun AboutItem(tag: String, shoeName: String, shoePrice: Double, about: String) {
        val modifier = Modifier.padding(horizontal = 20.dp)
        Text(
            modifier = modifier.padding(top = 18.dp),
            text = tag,
            fontFamily = Poppins_Regular,
            textAlign = TextAlign.Center,
            style = TextStyle(
                fontSize = 16.sp,
                color = colorResource(id = R.color.intro_get_started)
            )
        )

        Text(
            modifier = modifier.padding(top = 6.dp),
            text = shoeName,
            fontFamily = Poppins_MEDIUM,
            textAlign = TextAlign.Center,
            style = TextStyle(fontSize = 24.sp)
        )
        Text(
            modifier = modifier.padding(vertical = 6.dp),
            text = "$ $shoePrice",
            fontFamily = Poppins_MEDIUM,
            textAlign = TextAlign.Center,
            style = TextStyle(fontSize = 22.sp)
        )
        Text(
            modifier = modifier,
            text = about,
            fontFamily = Poppins_Regular,
            textAlign = TextAlign.Start,
            style = TextStyle(fontSize = 16.sp, color = Color.Gray)
        )
    }

    @Composable
    fun Gallery(imgList: List<Int>, itemClick: (Int) -> Unit) {
        Text(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .padding(top = 6.dp),
            text = "Gallery ",
            style = TextStyle(fontFamily = Poppins_MEDIUM, fontSize = 24.sp)
        )


        LazyRow(modifier = Modifier.padding(horizontal = 20.dp)) {
            items(imgList) {
                GalleryItem(showImg = it, itemClick = { itemClick.invoke(it) })
                Spacer(modifier = Modifier.width(8.dp))
            }
        }
    }

    @Composable
    fun GalleryItem(showImg: Int, itemClick: (Int) -> Unit) {
        Box(
            modifier = Modifier
                .width(70.dp)
                .height(70.dp)
                .background(
                    color = colorResource(id = R.color.home_background),
                    shape = RoundedCornerShape(15.dp)
                )
                .clickable { itemClick(showImg) },
            contentAlignment = Alignment.Center
        ) {
            Image(painter = painterResource(id = showImg), contentDescription = "")
        }
    }

    @Composable
    fun Size(
        euClick: () -> Unit = {},
        usClick: () -> Unit = {},
        ukClick: () -> Unit = {},
        sizeClick: (Int) -> Unit = {}
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .padding(top = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = "Size ",
                style = TextStyle(fontFamily = Poppins_MEDIUM, fontSize = 24.sp)
            )
            Spacer(
                modifier = Modifier
                    .width(0.dp)
                    .weight(1f)
            )
            Text(
                text = "EU",
                style = TextStyle(
                    color = selectedCityTextColor("EU"),
                    fontFamily = Poppins_SEMI_BOLD
                ),
                modifier = Modifier.clickable { defaultCity = "EU"; euClick() }
            )
            Text(
                text = "US",
                style = TextStyle(
                    color = selectedCityTextColor("US"),
                    fontFamily = Poppins_SEMI_BOLD
                ),
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .clickable { defaultCity = "US"; usClick() }
            )
            Text(
                text = "UK",
                style = TextStyle(
                    color = selectedCityTextColor("UK"),
                    fontFamily = Poppins_SEMI_BOLD
                ),
                modifier = Modifier.clickable { defaultCity = "UK"; ukClick() }

            )
        }

        val sizeList = (36..45).toList()
        LazyRow(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .padding(bottom = 10.dp)
        ) {
            items(sizeList) {
                Card(
                    modifier = Modifier
                        .width(50.dp)
                        .height(50.dp)
                        .padding(start = 5.dp, top = 5.dp)
                        .clickable { sizeClick.invoke(it); selectedSize = it },
                    shape = CircleShape,
                    colors = CardDefaults.cardColors(containerColor = selectedSizeBoxColor(it)),
                    elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "$it",
                            style = TextStyle(
                                fontFamily = Poppins_Regular,
                                color = selectedSizeTextColor(it)
                            )
                        )
                    }

                }
                Spacer(modifier = Modifier.width(8.dp))
            }
        }
    }


    fun selectedCityTextColor(city: String) = if (city == defaultCity) Color.Black else Color.Gray

    @Composable
    fun selectedSizeBoxColor(size: Int) =
        if (selectedSize == size) colorResource(id = R.color.intro_get_started) else colorResource(
            id = R.color.home_background
        )

    fun selectedSizeTextColor(size: Int) = if (selectedSize == size) Color.White else Color.Gray

    @Composable
    fun BottomLayout(btmModifier: Modifier, price: Double = 967.800) {
        Row(
            modifier = btmModifier
                .fillMaxWidth()
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(25.dp)
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .padding(vertical = 10.dp, horizontal = 20.dp)
            ) {
                Text(
                    text = "Price",
                    style = TextStyle(
                        fontFamily = Poppins_Regular,
                        fontSize = 18.sp,
                        color = Color.Gray
                    )
                )
                Text(
                    text = "$ $price",
                    style = TextStyle(fontFamily = Poppins_SEMI_BOLD, fontSize = 22.sp)
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Card(
                modifier = Modifier
                    .width(150.dp)
                    .height(50.dp)
                    .padding(end = 20.dp)
                    .clickable { },
                shape = RoundedCornerShape(25.dp),
                colors = CardDefaults.cardColors(containerColor = colorResource(id = R.color.intro_get_started))
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Add To Cart",
                        style = TextStyle(
                            fontFamily = Poppins_SEMI_BOLD,
                            color = Color.White
                        )
                    )
                }
            }

        }
    }


    @Preview(showBackground = true)
    @Composable
    fun PreviewItemDetails() {
        ItemDetails()
    }

}


val dummyList = listOf(
    R.drawable.ic_shoe_1, R.drawable.ic_shoe_2,
    R.drawable.ic_shoe_1, R.drawable.ic_shoe_2,
    R.drawable.ic_shoe_1, R.drawable.ic_shoe_2,
)
val about =
    "Air Jordan is an American brand of basketball shoes athletic, casual, and style clothing produced by Nike...."