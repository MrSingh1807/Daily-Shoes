package com.example.dailyshoes.ui.designs

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.dailyshoes.R
import com.example.dailyshoes.ui.theme.Poppins_Regular
import com.example.dailyshoes.ui.theme.intro_desc_1
import com.example.dailyshoes.ui.theme.intro_desc_2
import com.example.dailyshoes.ui.theme.intro_desc_3
import com.example.dailyshoes.ui.theme.intro_title_1
import com.example.dailyshoes.ui.theme.intro_title_2
import com.example.dailyshoes.ui.theme.intro_title_3

var selectedItem by mutableStateOf(0)
var textTitle by mutableStateOf(intro_title_1)
var textDesc by mutableStateOf(intro_desc_1)


@SuppressLint("UnrememberedMutableState")
@Composable
fun IntroScreen(moveAnotherScreen: () -> Unit) {

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            val (backImage, introImg) = createRefs()
            Image(
                painter = painterResource(R.drawable.intro_top_right),
                contentDescription = "",
                modifier = Modifier
                    .width(200.dp)
                    .height(250.dp)
                    .constrainAs(backImage) {
                        top.linkTo(parent.top)
                        end.linkTo(parent.end)
                    }
            )
            Image(
                painter = painterResource(id = R.drawable.intro_1), contentDescription = "",
                modifier = Modifier
                    .padding(top = 160.dp)
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .constrainAs(introImg) {
                        top.linkTo(parent.top)
                    }
            )
        }

        Text(
            text = textTitle,
            fontFamily = Poppins_Regular,
            modifier = Modifier.padding(start = 20.dp),
            style = TextStyle(fontSize = 45.sp, fontWeight = FontWeight.ExtraBold)
        )
        Text(
            text = textDesc,
            fontFamily = Poppins_Regular,
            modifier = Modifier.padding(start = 20.dp, top = 10.dp),
            style = TextStyle(fontSize = 22.sp, color = Color.Gray)
        )
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(0.dp)
                .weight(1f)
        )

        val selectedColor = R.color.intro_get_started
        val unSelected = R.color.intro_small_line
        val selectedWidth = 50
        val unSelectedWidth = 10

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(30.dp),
            verticalAlignment = Alignment.CenterVertically, // Aligns items vertically
            horizontalArrangement = Arrangement.Center
        ) {
            BottomChip(selectedColor, unSelected, selectedWidth, unSelectedWidth, selectedItem)
            Spacer(
                modifier = Modifier
                    .width(0.dp)
                    .weight(1f)
            )
            Button(
                onClick = {
                    if (selectedItem > 2) selectedItem = 0 else selectedItem++
                    Log.d("Mr_Singh", "selectedItem: $selectedItem")
                    when (selectedItem) {
                        0 -> {
                            textTitle = intro_title_1
                            textDesc = intro_desc_1
                        }

                        1 -> {
                            textTitle = intro_title_2
                            textDesc = intro_desc_2
                        }

                        2 -> {
                            textTitle = intro_title_3
                            textDesc = intro_desc_3
                        }
                    }
                },
                colors = ButtonDefaults.buttonColors(colorResource(id = R.color.intro_get_started))
            ) {
                Text(
                    text = "Get Started",
                    fontFamily = Poppins_Regular
                )
            }
        }
    }
}

@Composable
fun BottomChip(
    selectedColor: Int, unselectedColor: Int,
    selectedWidth: Int, unselectedWidth: Int,
    selectedItem: Int
) {

    Row {
        Box(
            modifier = Modifier
                .height(6.dp)
                .width((if (selectedItem == 0) selectedWidth else unselectedWidth).dp)
                .background(
                    colorResource(if (selectedItem == 0) selectedColor else unselectedColor),
                    shape = RoundedCornerShape(2.5.dp)
                )
        )

        Box(
            modifier = Modifier
                .height(6.dp)
                .padding(start = 5.dp)
                .width((if (selectedItem == 1) selectedWidth else unselectedWidth).dp)
                .background(
                    colorResource(if (selectedItem == 1) selectedColor else unselectedColor),
                    shape = RoundedCornerShape(2.5.dp)
                )
        )

        Box(
            modifier = Modifier
                .height(6.dp)
                .padding(start = 5.dp)
                .width((if (selectedItem == 2) selectedWidth else unselectedWidth).dp)
                .background(
                    colorResource(if (selectedItem == 2) selectedColor else unselectedColor),
                    shape = RoundedCornerShape(2.5.dp)
                )
        )


//        E5EEF7
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    IntroScreen({})
}