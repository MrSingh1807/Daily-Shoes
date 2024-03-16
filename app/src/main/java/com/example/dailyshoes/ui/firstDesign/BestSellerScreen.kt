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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.dailyshoes.R
import com.example.dailyshoes.ui.activities.AboutShoeActivity.Companion.dummyList
import com.example.dailyshoes.ui.theme.Poppins_MEDIUM
import com.example.dailyshoes.ui.theme.Poppins_Regular

class BestSellerScreen {


    @Composable
    fun BestSellerDesign() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    colorResource(id = R.color.home_background),
                    shape = RoundedCornerShape(2.5.dp)
                )
        ) {

            TitleBar()

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier
                    .fillMaxWidth().padding(top = 20.dp)
                    .padding(horizontal = 12.dp)
            ){
                items(dummyList){
                    BestItem(
                        tag = "New",
                        shoeName = "Nike Air Max",
                        shoePrice = 120.00,
                        img = R.drawable.ic_shoe_5
                    )
                }
            }


        }
    }

    @Composable
    fun TitleBar(
        backPressed: () -> Unit = {},
        filterClick: () -> Unit = {},
        searchClick: () -> Unit = {}
    ) {
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
                text = "Best Seller",
                fontFamily = Poppins_MEDIUM,
                textAlign = TextAlign.Center,
            )

            Image(
                modifier = Modifier.clickable { filterClick.invoke() },
                painter = painterResource(id = R.drawable.ic_filter),
                contentDescription = "Drawer"
            )
            Image(
                modifier = Modifier
                    .padding(start = 10.dp)
                    .clickable { searchClick.invoke() },
                painter = painterResource(id = R.drawable.ic_search_best),
                contentDescription = "Drawer"
            )


        }
    }

    @Composable
    fun BestItem(
        tag: String, shoeName: String,
        shoePrice: Double, img: Int,
        shoeGender: String = "Men",
        itemClick: () -> Unit = {},
    ) {
        val screenWid = LocalConfiguration.current.screenWidthDp / 2
        Card(
            modifier = Modifier
                .width(screenWid.dp - 20.dp)
                .height(260.dp)
                .padding(8.dp)
                .clip(shape = RoundedCornerShape(20.dp))
                .clickable(onClick = itemClick),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            ConstraintLayout(modifier = Modifier.fillMaxSize()) {
                val (tagText, shoeGen, shoeNameText, bottomPrice, shoeImage, favImg) = createRefs()

                Image(
                    painter = painterResource(id = img), contentDescription = "Shoe Image",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(135.dp)
                        .constrainAs(shoeImage) {
                            start.linkTo(parent.start)
                            top.linkTo(parent.top)
                        }
                )
                val modifier = Modifier.padding(horizontal = 15.dp)
                Text(
                    text = tag.uppercase(), style = TextStyle(
                        fontSize = 13.sp, fontFamily = Poppins_Regular,
                        color = colorResource(id = R.color.intro_get_started)
                    ),
                    modifier = modifier
                        .constrainAs(tagText) {
                            start.linkTo(shoeImage.start)
                            top.linkTo(shoeImage.bottom)
                        }
                )
                Text(
                    text = shoeName, style = TextStyle(
                        fontFamily = Poppins_MEDIUM, fontSize = 16.sp
                    ),
                    modifier = modifier.constrainAs(shoeNameText) {
                        start.linkTo(tagText.start)
                        top.linkTo(tagText.bottom)
                    }
                )

                Text(
                    text = if (shoeGender == "Men") "Men's Shoes" else "Women's Shoes",
                    style = TextStyle(
                        fontFamily = Poppins_Regular, color = Color.Gray, fontSize = 12.sp
                    ),
                    modifier = modifier
                        .padding(top = 5.dp)
                        .constrainAs(shoeGen) {
                            start.linkTo(shoeNameText.start)
                            top.linkTo(shoeNameText.bottom)
                            bottom.linkTo(shoeNameText.bottom)
                        }
                )

                Row(modifier = modifier
                    .padding(top = 10.dp, bottom = 5.dp)
                    .constrainAs(bottomPrice) {
                        start.linkTo(shoeGen.start)
                        top.linkTo(shoeGen.bottom)
                        bottom.linkTo(parent.bottom)
                    }) {
                    Text(
                        text = "$$shoePrice", style = TextStyle(
                            fontFamily = Poppins_MEDIUM, fontSize = 16.sp
                        )
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    ShoeColor(R.color.light_yellow)
                    ShoeColor(R.color.shadow_green)
                }

            }
        }
    }

    @Composable
    fun ShoeColor(color: Int) {
        Card(
            modifier = Modifier
                .width(20.dp)
                .height(20.dp)
                .padding(start = 5.dp, top = 5.dp)
                .clickable { },
            shape = CircleShape,
            colors = CardDefaults.cardColors(containerColor = colorResource(id = R.color.fav_shoe_shadow)),
            elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(1.75.dp)
                    .background(colorResource(id = color), shape = CircleShape),
            )
        }
    }


    @Preview(showBackground = true)
    @Composable
    fun Preview() {
        BestSellerDesign()
    }

}