package com.example.dailyshoes.ui.activities.authScreens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
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
import com.example.dailyshoes.ui.activities.authScreens.SignIn.AboutUserItem
import com.example.dailyshoes.ui.theme.Poppins_MEDIUM
import com.example.dailyshoes.ui.theme.Poppins_Regular
import com.example.dailyshoes.ui.viewModel.AuthViewModel

object RecoveryPassword {


    lateinit var authVM: AuthViewModel

    @Composable
    fun RecoveryPassScreen(authViewModel: AuthViewModel, navController: NavHostController) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    colorResource(id = R.color.home_background),
                ),
        ) {

            var showRecoveryEmail by remember { mutableStateOf(false) }

            val emailErrorVisible = remember { mutableStateOf(false) }
            val passErrorVisible = remember { mutableStateOf(false) }
            val confirmErrorVisible = remember { mutableStateOf(false) }

            val email = remember { mutableStateOf("") }
            val newPassword = remember { mutableStateOf("") }
            val confirmPassword = remember { mutableStateOf("") }

            val mContext = LocalContext.current


            TitleBar("", backPressed = {
                if (!showRecoveryEmail) showRecoveryEmail = true
                else navController.popBackStack()
            })

            val title1 = "Recovery Password"
            val title2 = "Please Enter Your Email Address To \nReceive a Verification Code"

            val emailModifier = if (showRecoveryEmail) Modifier else Modifier.size(0.dp)
            val confirmPasswordModifier = if (!showRecoveryEmail) Modifier else Modifier.size(0.dp)

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 25.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = title1,
                    style = TextStyle(fontFamily = Poppins_MEDIUM, fontSize = 25.sp),
                )
                Text(
                    text = title2,
                    style = TextStyle(
                        fontFamily = Poppins_Regular,
                        color = Color.Gray,
                        fontSize = 16.sp
                    ),
                    textAlign = TextAlign.Center
                )

            }

            Spacer(modifier = Modifier.padding(vertical = 30.dp))

            ResetPassword(
                modifier = emailModifier,
                itemTitle = "Email Address",
                emailErrorVisible.value,
                "Email Address is not valid",
                editableValue = {
                    emailErrorVisible.value = if (authViewModel.isValidEmail(it)) {
                        email.value = it
                        false
                    } else true
                },
            )

            ResetPassword(
                modifier = confirmPasswordModifier,
                itemTitle = "New Password",
                passErrorVisible.value,
                "Your password have at least one letter & number",
                editableValue = {
                    passErrorVisible.value = if (authViewModel.isValidPassword(it)) {
                        newPassword.value = it
                        false
                    } else true
                },
            )
            ResetPassword(
                modifier = confirmPasswordModifier.padding(top = 10.dp),
                itemTitle = "Confirm Password", confirmErrorVisible.value,
                "Your password is not matched",
                editableValue = {
                    confirmErrorVisible.value =
                        if (authViewModel.isValidPassword(it) && it == newPassword.value) {
                            confirmPassword.value = it
                            false
                        } else true
                },
            )

            Button(
                onClick = {
                    if (showRecoveryEmail) {
                        if (authViewModel.checkEmailForResetPassword(email = email.value)) {
                            showRecoveryEmail = false
                        } else {
                            Toast.makeText(mContext, "Please your email first", Toast.LENGTH_LONG)
                                .show()
                        }
                    } else {
                        if (!passErrorVisible.value && !confirmErrorVisible.value && newPassword.value == confirmPassword.value) {
                            authViewModel.resetPassword(email.value, newPassword.value) {
                                navController.popBackStack()
                            }
                        }
                    }
                }, modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.intro_get_started))
            ) {
                Text(text = "Continue", style = TextStyle(fontFamily = Poppins_MEDIUM))
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun ResetPassword(
        modifier: Modifier,
        itemTitle: String,
        isErrorVisible: Boolean,
        errorValue: String,
        editableValue: (String) -> Unit = {},
        onPasswordVisibleClick: () -> Unit = {},
        passwordVisibleIcon: Int? = null
    ) {
        Column(
            modifier = modifier
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
                    text = errorValue,
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

}

@Preview(showBackground = true)
@Composable
fun RecoveryPasswordPreview() {
    RecoveryPassword.RecoveryPassScreen(RecoveryPassword.authVM, rememberNavController())
}