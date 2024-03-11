package com.example.dailyshoes.ui.firstDesign.orderScreens

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dailyshoes.R
import com.example.dailyshoes.ui.firstDesign.CartScreen
import com.example.dailyshoes.ui.firstDesign.orderScreens.TrackOrder.Companion.RatingBar
import com.example.dailyshoes.ui.theme.Poppins_MEDIUM
import com.example.dailyshoes.ui.theme.Poppins_Regular


class RateProduct {

    @Composable
    fun RateProductScreen() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    colorResource(id = R.color.home_background),
                ),
        ) {
            CartScreen.TitleBar("Rate Product")

            TrackOrderStatus()

            RatingBar(
                rowModifier = Modifier
                    .padding(20.dp)
                    .fillMaxWidth(),
                starModifier = Modifier
                    .width(60.dp)
                    .height(60.dp)
            )

            SearchBar()
            PickImage()
            Button(
                onClick = { }, modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.intro_get_started))
            ) {
                Text(text = "Submit Review", style = TextStyle(fontFamily = Poppins_MEDIUM))
            }

        }
    }

    @Composable
    fun TrackOrderStatus(submitReview: () -> Unit = {}) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp, horizontal = 20.dp)
                .clickable { submitReview.invoke() },
            colors = CardDefaults.cardColors(containerColor = colorResource(id = R.color.intro_get_started)),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
            shape = RoundedCornerShape(20.dp)
        ) {
            Row(
                modifier = Modifier
                    .padding(horizontal = 20.dp, vertical = 15.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_submit_review),
                    contentDescription = "On the way"
                )
                Text(
                    text = "Submit your review to get 5 points",
                    style = TextStyle(
                        fontFamily = Poppins_MEDIUM,
                        fontSize = 12.sp,
                        color = Color.White
                    )
                )
                Image(
                    painter = painterResource(id = R.drawable.ic_right_arrow),
                    contentDescription = "On the way"
                )
            }
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
            val textFieldColors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                cursorColor = Color.Black,
                containerColor = Color.White
            )
            TextField(
                value = "- Would you like to write anything about this product? ",
                onValueChange = { newText -> },
                colors = textFieldColors,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .background(color = Color.White),
                textStyle = TextStyle(
                    fontFamily = Poppins_Regular,
                    fontSize = 13.sp
                )

            )
        }
    }

    @Composable
    fun PickImage(
        onCameraClick: () -> Unit = {},
        onGalleryClick: () -> Unit = {},
        pickedImages: List<Bitmap> = emptyList()
    ) {

        Row(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth()
                .height(50.dp),
        ) {
            Image(
                modifier = Modifier.clickable { onGalleryClick.invoke() },
                painter = painterResource(id = R.drawable.ic_open_gallery),
                contentDescription = "Open Camera"
            )

            Image(
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .clickable { onCameraClick.invoke() },
                painter = painterResource(id = R.drawable.ic_open_camera),
                contentDescription = "Open Camera"
            )
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(pickedImages) {
                    Image(
                        bitmap = it.asImageBitmap(),
                        contentDescription = "Open Camera"
                    )
                }
            }
        }


    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        RateProductScreen()
    }

}