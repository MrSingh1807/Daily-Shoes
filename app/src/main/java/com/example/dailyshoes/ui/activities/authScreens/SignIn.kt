package com.example.dailyshoes.ui.activities.authScreens

import android.widget.Toast
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.dailyshoes.R
import com.example.dailyshoes.ui.activities.CartActivity.Companion.TitleBar
import com.example.dailyshoes.ui.activities.HomeActivity
import com.example.dailyshoes.ui.navigation.AuthNav
import com.example.dailyshoes.ui.theme.Black
import com.example.dailyshoes.ui.theme.Poppins_MEDIUM
import com.example.dailyshoes.ui.theme.Poppins_Regular
import com.example.dailyshoes.ui.utils.navigateToActivity
import com.example.dailyshoes.ui.viewModel.AuthViewModel
import kotlinx.coroutines.launch


object SignIn {

    @Composable
    fun SignInScreen(authViewModel: AuthViewModel? = null, navController: NavHostController) {

        val emailErrorVisible = remember { mutableStateOf(false) }
        val passErrorVisible = remember { mutableStateOf(false) }

        val email = remember { mutableStateOf("") }
        val password = remember { mutableStateOf("") }

        val mContext = LocalContext.current

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

                AboutUserItem(
                    itemTitle = "Email Address",
                    isErrorVisible = emailErrorVisible.value,
                    editableValue = {
                        emailErrorVisible.value = if (authViewModel!!.isValidEmail(it)) {
                            email.value = it
                            false
                        } else true
                    },
                )
                Spacer(modifier = Modifier.padding(15.dp))
                AboutUserItem(
                    itemTitle = "Password",
                    passwordVisibleIcon = R.drawable.ic_password_invisible,
                    isErrorVisible = passErrorVisible.value,
                    editableValue = {
                        passErrorVisible.value = if (authViewModel!!.isValidPassword(it)) {
                            password.value = it
                            false
                        } else true
                    },
                    onPasswordVisibleClick = { passErrorVisible.value = false }
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 5.dp),
                    horizontalArrangement = Arrangement.End
                ) {
                    Text(
                        modifier = Modifier.clickable { navController.navigate(AuthNav.PasswordRecovery.route) },
                        text = "Password Recovery",
                        style = TextStyle(
                            fontFamily = Poppins_Regular,
                            color = Color.Gray,
                        ),
                    )
                }
            }

            val mContext = LocalContext.current
            BottomButtons(
                authButtonText = "Sign In",
                bottomButtonText = "Don’t have an account? " to "Sign Up for free",
                signInClick = {
                    val condition = !emailErrorVisible.value && !passErrorVisible.value
                            && email.value.isNotEmpty() && password.value.isNotEmpty()
                    if (condition) {
                        authViewModel!!.validateUserSignIn(email.value, password.value) {
                            if (it) {
                                authViewModel.isLoginValidate()
                                mContext.navigateToActivity(HomeActivity::class.java)
                            } else Toast.makeText(
                                mContext,
                                "Please enter correct Email and Password",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    } else {
                        Toast.makeText(
                            mContext,
                            "Please enter a valid email & password",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                },
                signInWithGoogleClick = { },
                alreadyAccountClick = { navController.navigate(AuthNav.SignUp.route) })

        }
    }


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
                style = TextStyle(fontFamily = Poppins_MEDIUM, fontSize = 30.sp, color = Black),
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
        isErrorVisible: Boolean = false,
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
                style = TextStyle(fontFamily = Poppins_MEDIUM, fontSize = 20.sp, color = Black)
            )
            val textFieldColors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                cursorColor = Color.Black,
                containerColor = Color.White
            )

            var itemText by remember { mutableStateOf("") }

            ConstraintLayout {
                val (textField, passwordIcon, errorText) = createRefs()

                TextField(
                    value = itemText,
                    onValueChange = { newText ->
                        itemText = newText
                        editableValue.invoke(newText)
                    },
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
                    placeholder = {
                        Text(
                            text = "Enter Your $itemTitle Here!",
                            style = TextStyle(color = Color.Gray, fontStyle = FontStyle.Italic)
                        )
                    },
                )
                val errorTextModifier = if (isErrorVisible) Modifier else Modifier.size(0.dp)
                Text(
                    text = "$itemTitle is not valid",
                    modifier = errorTextModifier
                        .padding(end = 20.dp)
                        .constrainAs(errorText) {
                            bottom.linkTo(textField.bottom)
                            end.linkTo(textField.end)
                        },
                    style = TextStyle(
                        color = Color.Red,
                        fontFamily = Poppins_Regular,
                        fontSize = 10.sp
                    )
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
        authButtonText: String,
        bottomButtonText: Pair<String, String>,
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
                    text = authButtonText, modifier = Modifier.padding(vertical = 5.dp),
                    style = TextStyle(fontFamily = Poppins_MEDIUM, color = Color.Black)
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
                    text = "$authButtonText with google",
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
                    text = bottomButtonText.first,
                    style = TextStyle(
                        fontFamily = Poppins_Regular,
                        fontSize = 12.sp,
                        color = Color.Gray
                    )
                )
                Text(
                    text = bottomButtonText.second,
                    style = TextStyle(fontFamily = Poppins_MEDIUM, fontSize = 12.sp, color = Black)
                )
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SignIn.SignInScreen(
        navController = rememberNavController()
    )
}
