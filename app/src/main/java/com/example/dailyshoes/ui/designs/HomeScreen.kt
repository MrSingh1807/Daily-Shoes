package com.example.dailyshoes.ui.designs

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dailyshoes.R
import com.example.dailyshoes.ui.theme.Poppins_Regular
import com.example.dailyshoes.ui.theme.intro_title_1

var searchText by mutableStateOf("Looking For Shoes")

@Composable
fun HomeScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                colorResource(id = R.color.home_background),
                shape = RoundedCornerShape(2.5.dp)
            )
    ) {
        TitleBar()
        SearchBar()
        BrandBar()
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
                )
            }
        }

        Image(
            modifier = Modifier.clickable { },
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
            .wrapContentHeight()
    ) {
        val brandItemModifier = Modifier

        SelectedBrandItem(brandItemModifier, "Nike", R.drawable.ic_nike)
        BrandLogo(brandLogo = R.drawable.ic_puma)
        BrandLogo(brandLogo = R.drawable.ic_under_armour)
        BrandLogo(brandLogo = R.drawable.ic_adidas)
        BrandLogo(brandLogo = R.drawable.ic_converse)

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
fun BrandLogo(modifier: Modifier = Modifier, brandLogo: Int) {
    Image(
        modifier = modifier,
        painter = painterResource(id = brandLogo),
        contentDescription = ""
    )
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}
