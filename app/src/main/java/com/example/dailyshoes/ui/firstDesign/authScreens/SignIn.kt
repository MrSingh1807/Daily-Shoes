package com.example.dailyshoes.ui.firstDesign.authScreens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
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
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.dailyshoes.R
import com.example.dailyshoes.ui.activities.CartActivity.Companion.TitleBar
import com.example.dailyshoes.ui.theme.Poppins_MEDIUM
import com.example.dailyshoes.ui.theme.Poppins_Regular


class SignIn {

    @Composable
    fun SignInScreen() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    colorResource(id = R.color.home_background),
                ),
        ) {

            TitleBar("")
            AuthTitle(title1 = "Hello Again!", title2 = "Welcome Back You’ve Been Missed!")
            Column(
                modifier = Modifier.padding(top = 50.dp),
            ) {

                AboutUserItem(itemTitle = "Email Address", itemValue = "AlissonBecker@gmail.com")
                Spacer(modifier = Modifier.padding(15.dp))
                AboutUserItem(
                    itemTitle = "Password",
                    itemValue = " ******** ",
                    passwordVisibleIcon = R.drawable.ic_password_invisible
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 5.dp),
                    horizontalArrangement = Arrangement.End
                ) {
                    Text(
                        text = "Password Recovery",
                        style = TextStyle(
                            fontFamily = Poppins_Regular,
                            color = Color.Gray,
                        ),
                    )
                }
            }

            BottomButtons(btmButton1 = "Don’t have an account? ", btmButton2 = "Sign Up for free")

        }
    }


    companion object {

        @Composable
        fun AuthTitle(
            title1: String,
            title2: String
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 25.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = title1,
                    style = TextStyle(fontFamily = Poppins_MEDIUM, fontSize = 30.sp),
                )
                Text(
                    text = title2,
                    style = TextStyle(fontFamily = Poppins_Regular, color = Color.Gray),
                    textAlign = TextAlign.Center
                )

            }
        }

        @OptIn(ExperimentalMaterial3Api::class)
        @Composable
        fun AboutUserItem(
            itemTitle: String = "Full Name",
            itemValue: String = "Mr Singh",
            passwordVisibleIcon: Int? = null,
            editableValue: (String) -> Unit = {},
            onPasswordVisibleClick: () -> Unit = {}
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
            ) {
                Text(
                    text = itemTitle,
                    style = TextStyle(fontFamily = Poppins_MEDIUM, fontSize = 20.sp)
                )
                val textFieldColors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    cursorColor = Color.Black,
                    containerColor = Color.White
                )

                ConstraintLayout {
                    val (textField, passwordIcon) = createRefs()

                    TextField(
                        value = itemValue,
                        onValueChange = { newText -> editableValue.invoke(newText) },
                        colors = textFieldColors,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(CircleShape)
                            .constrainAs(textField) {
                                top.linkTo(parent.top)
                                bottom.linkTo(parent.bottom)
                            }
                            .background(color = Color.White),
                        textStyle = TextStyle(
                            fontFamily = Poppins_Regular,
                            color = Color.Black,
                        ),
                    )

                    val modifier =
                        if (passwordVisibleIcon == null) Modifier.size(0.dp) else Modifier

                    Image(
                        modifier = modifier
                            .padding(end = 15.dp)
                            .clickable { onPasswordVisibleClick.invoke() }
                            .constrainAs(passwordIcon) {
                                top.linkTo(textField.top)
                                bottom.linkTo(textField.bottom)
                                end.linkTo(textField.end)
                            },
                        painter = painterResource(
                            id = passwordVisibleIcon ?: R.drawable.ic_password_invisible
                        ),
                        contentDescription = " "
                    )

                }

            }
        }

        @Composable
        fun BottomButtons(
            btmButton1: String,
            btmButton2: String,
            signInClick: () -> Unit = {},
            signInWithGoogleClick: () -> Unit = {},
            alreadyAccountClick: () -> Unit = {}
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {

                Button(
                    onClick = { signInClick.invoke() },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.intro_get_started))
                ) {
                    Text(
                        text = "Sign In", modifier = Modifier.padding(vertical = 5.dp),
                        style = TextStyle(fontFamily = Poppins_MEDIUM)
                    )
                }

                Button(
                    onClick = { signInWithGoogleClick.invoke() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 20.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.White)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_google_sign_in),
                        contentDescription = "Google Icon"
                    )
                    Text(
                        text = "Sign in with google",
                        modifier = Modifier
                            .padding(start = 10.dp)
                            .padding(vertical = 5.dp),
                        style = TextStyle(fontFamily = Poppins_MEDIUM, color = Color.Black)
                    )
                }

                Spacer(modifier = Modifier.weight(1f))

                Row(
                    modifier = Modifier
                        .clickable { alreadyAccountClick.invoke() }
                        .padding(top = 20.dp),
                ) {
                    Text(
                        text = btmButton1,
                        style = TextStyle(
                            fontFamily = Poppins_Regular,
                            fontSize = 12.sp,
                            color = Color.Gray
                        )
                    )
                    Text(
                        text = btmButton2,
                        style = TextStyle(fontFamily = Poppins_Regular, fontSize = 12.sp)
                    )
                }
            }
        }

    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        SignInScreen()
    }


}