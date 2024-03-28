package com.example.dailyshoes.ui.activities.homeScreens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.dailyshoes.R
import com.example.dailyshoes.ui.activities.AboutShoeActivity
import com.example.dailyshoes.ui.activities.CartActivity
import com.example.dailyshoes.ui.designUtils.CurvedTopLineShape
import com.example.dailyshoes.ui.modals.Quad
import com.example.dailyshoes.ui.theme.Poppins_MEDIUM
import com.example.dailyshoes.ui.theme.Poppins_Regular
import com.example.dailyshoes.ui.theme.Poppins_SEMI_BOLD
import com.example.dailyshoes.ui.utils.navigateToActivity

object HomeScreen {

    var searchText by mutableStateOf("Looking For Shoes")
    var selection by mutableStateOf(0)

    @Composable
    fun Home() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    colorResource(id = R.color.home_background),
                    shape = RoundedCornerShape(2.5.dp)
                )
        )
        {
            TitleBar()
            SearchBar()
            BrandBar()

            Symbols(first = "Popular Shoes")
            val mContext = LocalContext.current

            val shoeList = listOf(
                Quad("Best Seller", "Nike Jordan", 493.00, R.drawable.ic_shoe_2),
                Quad("Best Seller", "Nike Air Max", 897.00, R.drawable.ic_shoe_1),
            )
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
            ) {
                items(shoeList) {
                    ShoesBox(it.t1, it.t2, it.t3, it.t4, itemClick = {
                        mContext.navigateToActivity(AboutShoeActivity::class.java)
                    })
                }
            }

            Symbols(first = "New Arrivals")

            //LazyRow
            NewArrival("Nike Air Jordan", 849.00, R.drawable.ic_shoe_3) {
                mContext.navigateToActivity(AboutShoeActivity::class.java)
            }

            Spacer(modifier = Modifier.weight(1f))
        }
    }


    @Composable
    fun TitleBar() {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp)
                .wrapContentHeight()
                .padding(horizontal = 20.dp)
        ) {

            Image(
                modifier = Modifier
                    .width(50.dp)
                    .height(50.dp)
                    .clickable { },
                painter = painterResource(id = R.drawable.ic_home_drawer),
                contentDescription = "Drawer"
            )

            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Store Location",
                    fontFamily = Poppins_Regular,
                    textAlign = TextAlign.Center,
                    color = Color.Gray
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_location),
                        contentDescription = null
                    ) // You can provide contentDescription as needed
                    Text(
                        modifier = Modifier.padding(start = 10.dp),
                        text = "Rajiv Chowk, New Delhi",
                        fontFamily = Poppins_Regular,
                        textAlign = TextAlign.Center,
                        color = Color.Black
                    )
                }
            }

            val context = LocalContext.current
            Image(
                modifier = Modifier.clickable { context.navigateToActivity(CartActivity::class.java) },
                painter = painterResource(id = R.drawable.ic_cart),
                contentDescription = "Drawer"
            )
        }
    }


    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun SearchBar() {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp)
                .wrapContentHeight()
                .padding(horizontal = 20.dp)
                .clip(shape = RoundedCornerShape(20.dp))
        ) {
            Box(
                modifier = Modifier
                    .width(50.dp)
                    .height(50.dp)
                    .background(color = Color.White),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_search),
                    contentDescription = ""
                )
            }
            val textFieldColors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                cursorColor = Color.Black,
                containerColor = Color.White
            )
            TextField(
                value = searchText,
                onValueChange = { newText -> },
                colors = textFieldColors,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .background(color = Color.White),
                textStyle = TextStyle(fontFamily = Poppins_Regular, color = Color.Gray)

            )
        }
    }

    @Composable
    fun BrandBar() {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .padding(top = 20.dp)
                .wrapContentHeight(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Absolute.SpaceEvenly
        ) {
            val viewVisible = Modifier
                .wrapContentSize()
                .padding(start = 8.dp)
            val viewGone = Modifier
                .width(0.dp)
                .height(0.dp)

            BrandLogo(
                if (selection == 0) viewGone else Modifier, brandLogo = R.drawable.ic_nike
            ) { selection = 0 }
            SelectedBrandItem(
                if (selection == 0) Modifier else viewGone, "Nike", R.drawable.ic_nike
            )


            BrandLogo(
                if (selection == 1) viewGone else viewVisible,
                brandLogo = R.drawable.ic_puma
            ) { selection = 1 }
            SelectedBrandItem(
                if (selection == 1) viewVisible else viewGone,
                "Puma",
                R.drawable.ic_puma
            )

            BrandLogo(
                if (selection == 2) viewGone else viewVisible,
                brandLogo = R.drawable.ic_under_armour
            ) { selection = 2 }
            SelectedBrandItem(
                if (selection == 2) viewVisible else viewGone,
                "Under",
                R.drawable.ic_under_armour
            )

            BrandLogo(
                if (selection == 3) viewGone else viewVisible,
                brandLogo = R.drawable.ic_adidas
            ) { selection = 3 }
            SelectedBrandItem(
                if (selection == 3) viewVisible else viewGone,
                "Adidas",
                R.drawable.ic_adidas
            )

            BrandLogo(
                if (selection == 4) viewGone else viewVisible,
                brandLogo = R.drawable.ic_converse
            ) { selection = 4 }
            SelectedBrandItem(
                if (selection == 4) viewVisible else viewGone,
                "Conv",
                R.drawable.ic_converse
            )

        }
    }

    @Composable
    fun SelectedBrandItem(modifier: Modifier, brandName: String, brandLogo: Int) {
        Box(
            modifier = modifier
                .clip(shape = RoundedCornerShape(25.dp))
                .background(color = colorResource(id = R.color.intro_get_started))
        ) {
            Row(
                modifier = Modifier.padding(5.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                BrandLogo(brandLogo = brandLogo)
                Text(
                    text = brandName,
                    modifier = Modifier.padding(start = 10.dp, end = 12.dp),
                    fontFamily = Poppins_Regular,
                    style = TextStyle(fontSize = 18.sp, color = Color.White)
                )
            }
        }
    }

    @Composable
    fun BrandLogo(modifier: Modifier = Modifier, brandLogo: Int, onBrandClick: () -> Unit = {}) {
        Image(
            modifier = modifier.clickable(onClick = onBrandClick),
            painter = painterResource(id = brandLogo),
            contentDescription = ""
        )
    }

    @Composable
    fun Symbols(first: String, clickSeeAll: () -> Unit = {}) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp, horizontal = 20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = first,
                style = TextStyle(
                    fontFamily = Poppins_SEMI_BOLD,
                    fontSize = 15.sp,
                    color = Color.Black
                )
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                modifier = Modifier.clickable(onClick = clickSeeAll),
                textAlign = TextAlign.Center,
                text = "See all",
                style = TextStyle(
                    fontFamily = Poppins_Regular,
                    fontSize = 14.sp,
                    color = colorResource(id = R.color.intro_get_started)
                )
            )
        }

    }

    @Composable
    fun ShoesBox(
        tag: String, shoeName: String,
        shoePrice: Double, img: Int,
        itemClick: () -> Unit = {},
        plusClick: () -> Unit = {}
    ) {
        val screenWid = LocalConfiguration.current.screenWidthDp / 2
        Box(
            modifier = Modifier
                .width(screenWid.dp - 20.dp)
                .height(260.dp)
                .padding(8.dp)
                .clip(shape = RoundedCornerShape(20.dp))
                .background(color = Color.White)
                .clickable(onClick = itemClick),
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                Image(
                    painter = painterResource(id = img), contentDescription = "Shoe Image",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp)
                )

                ConstraintLayout(
                    modifier = Modifier
                        .padding(start = 15.dp)
                        .fillMaxSize()
                ) {
                    val (tagText, shoeNameText, shoePriceText, plusBox) = createRefs()

                    Text(
                        text = tag.uppercase(), style = TextStyle(
                            fontSize = 13.sp, fontFamily = Poppins_Regular,
                            color = colorResource(id = R.color.intro_get_started)
                        ),
                        modifier = Modifier
                            .constrainAs(tagText) {
                                start.linkTo(parent.start)
                                top.linkTo(parent.top)
                            }
                    )
                    Text(
                        text = shoeName, style = TextStyle(
                            fontFamily = Poppins_MEDIUM, fontSize = 16.sp,
                            color = Color.Black
                        ),
                        modifier = Modifier.constrainAs(shoeNameText) {
                            start.linkTo(tagText.start)
                            top.linkTo(tagText.bottom)
                        }
                    )
                    Text(
                        text = "$$shoePrice", style = TextStyle(
                            fontFamily = Poppins_MEDIUM, fontSize = 16.sp, color = Color.Black
                        ), modifier = Modifier
                            .padding(top = 10.dp)
                            .constrainAs(shoePriceText) {
                                start.linkTo(shoeNameText.start)
                                top.linkTo(shoeNameText.bottom)
                            }
                    )

                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .width(40.dp)
                            .height(45.dp)
                            .clickable(onClick = plusClick)
                            .clip(shape = RoundedCornerShape(topStart = 20.dp))
                            .background(color = colorResource(id = R.color.intro_get_started))
                            .constrainAs(plusBox) {
                                end.linkTo(parent.end)
                                bottom.linkTo(parent.bottom)
                            }) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_plus),
                            contentDescription = "plus",
                            modifier = Modifier.wrapContentSize()
                        )
                    }
                }
            }
        }
    }

    @Composable
    fun NewArrival(
        shoeName: String,
        shoePrice: Double,
        newArrivalShoeImage: Int,
        onItemClick: () -> Unit = {}
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .padding(horizontal = 20.dp)
                .clickable { }
                .clip(shape = RoundedCornerShape(20.dp))
                .background(
                    color = Color.White,
                ),
        ) {
            Column(modifier = Modifier.padding(start = 15.dp)) {
                Text(
                    text = "BEST CHOICE",
                    style = TextStyle(fontFamily = Poppins_Regular),
                    color = colorResource(
                        id = R.color.intro_get_started
                    )
                )
                Text(
                    text = shoeName,
                    style = TextStyle(
                        fontFamily = Poppins_MEDIUM,
                        fontSize = 16.sp,
                        color = Color.Black
                    ),
                )
                Text(
                    text = "$$shoePrice",
                    style = TextStyle(
                        fontFamily = Poppins_SEMI_BOLD,
                        fontSize = 16.sp,
                        color = Color.Black
                    )
                )
            }
            Image(painter = painterResource(id = newArrivalShoeImage), contentDescription = "")
        }
    }

    @Composable
    fun BottomNav() {

        val btmBarList =
            listOf(
                "Home" to R.drawable.ic_home_btm_nav,
                "Favourite" to R.drawable.ic_fav_btm_nav,
                "Notification" to R.drawable.ic_notification_btm_nav,
                "Profile" to R.drawable.ic_profile_btm_nav
            )

        var selectedItemIndex by rememberSaveable { mutableStateOf(0) }

        NavigationBar(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .padding(top = 20.dp)
                .clip(shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)),
            containerColor = Color.White,
        ) {
            btmBarList.forEachIndexed { index, pair ->
                NavigationBarItem(modifier = Modifier.padding(top = 10.dp),
                    selected = selectedItemIndex == index,
                    onClick = {
//                              navController.navigate(pair.first)
                    },
                    label = { Text(text = pair.first) },
                    icon = {
                        BadgedBox(badge = {
//                            Badge()
                        }) {
                            Icon(
                                painter = painterResource(id = pair.second),
                                contentDescription = pair.first
                            )
                        }
                    }
                )
            }
        }
    }

    fun Modifier.curvedTopLine(): Modifier = this.then(
        Modifier.clip(CurvedTopLineShape())
    )

}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen.Home()
}
