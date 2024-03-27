package com.example.dailyshoes.ui.activities

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.dailyshoes.R
import com.example.dailyshoes.ui.theme.DailyShoesTheme
import com.example.dailyshoes.ui.theme.Poppins_MEDIUM
import com.example.dailyshoes.ui.theme.Poppins_Regular
import com.example.dailyshoes.ui.theme.intro_desc_1
import com.example.dailyshoes.ui.theme.intro_desc_2
import com.example.dailyshoes.ui.theme.intro_desc_3
import com.example.dailyshoes.ui.theme.intro_title_1
import com.example.dailyshoes.ui.theme.intro_title_2
import com.example.dailyshoes.ui.theme.intro_title_3
import com.example.dailyshoes.ui.utils.navigateToActivity
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class IntroActivity : ComponentActivity() {

    var textTitle by mutableStateOf(intro_title_1)
    var textDesc by mutableStateOf(intro_desc_1)

    @OptIn(ExperimentalFoundationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val pagerState = rememberPagerState(pageCount = { 3 })

            DailyShoesTheme {
                HorizontalPager(state = pagerState) { page ->
                    IntroScreen(page = page)
                }
            }
        }
    }


    @SuppressLint("UnrememberedMutableState")
    @Composable
    fun IntroScreen(page: Int, moveAnotherScreen: () -> Unit = {}) {

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

                val introImages = when (page) {
                    1 -> R.drawable.intro_2
                    2 -> R.drawable.intro_3
                    else -> R.drawable.intro_1
                }
                Image(
                    painter = painterResource(id = introImages), contentDescription = "",
                    modifier = Modifier
                        .padding(top = 160.dp)
                        .fillMaxWidth()
                        .height(300.dp)
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


            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(30.dp),
                verticalAlignment = Alignment.CenterVertically, // Aligns items vertically
                horizontalArrangement = Arrangement.Center
            ) {

                val selectedColor = R.color.intro_get_started
                val unSelected = R.color.intro_small_line
                val selectedWidth = 50
                val unSelectedWidth = 10

                BottomChip(selectedColor, unSelected, selectedWidth, unSelectedWidth, page)
                Spacer(
                    modifier = Modifier
                        .width(0.dp)
                        .weight(1f)
                )
                Button(
                    onClick = { navigateToActivity(AuthActivity::class.java) },
                    colors = ButtonDefaults.buttonColors(colorResource(id = R.color.intro_get_started))
                ) {
                    Text(
                        text = "Get Started",
                        fontFamily = Poppins_MEDIUM, color = Color.Black
                    )
                }
            }

        }
    }

    @Composable
    fun BottomChip(
        selectedColor: Int, unselectedColor: Int,
        selectedWidth: Int, unselectedWidth: Int, page: Int
    ) {
        Row {
            Box(
                modifier = Modifier
                    .height(6.dp)
                    .width((if (page == 0) selectedWidth else unselectedWidth).dp)
                    .background(
                        colorResource(if (page == 0) selectedColor else unselectedColor),
                        shape = RoundedCornerShape(2.5.dp)
                    )
            )

            Box(
                modifier = Modifier
                    .height(6.dp)
                    .padding(start = 5.dp)
                    .width((if (page == 1) selectedWidth else unselectedWidth).dp)
                    .background(
                        colorResource(if (page == 1) selectedColor else unselectedColor),
                        shape = RoundedCornerShape(2.5.dp)
                    )
            )

            Box(
                modifier = Modifier
                    .height(6.dp)
                    .padding(start = 5.dp)
                    .width((if (page == 2) selectedWidth else unselectedWidth).dp)
                    .background(
                        colorResource(if (page == 2) selectedColor else unselectedColor),
                        shape = RoundedCornerShape(2.5.dp)
                    )
            )


//        E5EEF7
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        DailyShoesTheme {
            IntroScreen(0)
        }
    }
}
