package com.example.dailyshoes.ui.activities.authScreens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.dailyshoes.R
import com.example.dailyshoes.ui.activities.CartActivity.Companion.TitleBar
import com.example.dailyshoes.ui.activities.authScreens.SignIn.BottomButtons
import com.example.dailyshoes.ui.navigation.AuthNav
import com.example.dailyshoes.ui.viewModel.AuthViewModel


object CreateNewAC {

    @Composable
    fun CreateNewACScreen(authViewModel: AuthViewModel? = null, navController: NavHostController) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    colorResource(id = R.color.home_background),
                ),
        ) {
            val emailErrorVisible = remember { mutableStateOf(false) }
            val passErrorVisible = remember { mutableStateOf(false) }

            val name = remember { mutableStateOf("") }
            val email = remember { mutableStateOf("") }
            val password = remember { mutableStateOf("") }

            val mContext = LocalContext.current


            TitleBar("", backPressed = { navController.popBackStack() })
            SignIn.AuthTitle(title1 = "Create Account", title2 = "Let’s Create Account Together")
            Column(
                modifier = Modifier.padding(top = 50.dp),
            ) {
                SignIn.AboutUserItem(editableValue = {
                    name.value = it
                })
                Spacer(modifier = Modifier.padding(15.dp))
                SignIn.AboutUserItem(
                    itemTitle = "Email Address",
                    isErrorVisible = emailErrorVisible.value,
                    editableValue = {
                        if (authViewModel!!.isValidEmail(it)) {
                            emailErrorVisible.value = false
                            email.value = it
                        } else emailErrorVisible.value = true

                    }
                )
                Spacer(modifier = Modifier.padding(15.dp))
                SignIn.AboutUserItem(
                    itemTitle = "Password",
                    passwordVisibleIcon = R.drawable.ic_password_invisible,
                    isErrorVisible = passErrorVisible.value,
                    editableValue = {
                        if (authViewModel!!.isValidPassword(it)) {
                            passErrorVisible.value = false
                            password.value = it
                        } else passErrorVisible.value = true
                    }
                )
            }

            BottomButtons(
                authButtonText = "Sign Up",
                bottomButtonText = "Already have an account? " to "Sign In",
                alreadyAccountClick = {
                    navController.navigate(AuthNav.SignIn.route)
                },
                signInClick = {
                    val condition = !emailErrorVisible.value && !passErrorVisible.value
                            && name.value.isNotEmpty() && email.value.isNotEmpty() && password.value.isNotEmpty()
                    if (condition) {
                        authViewModel!!.insertUser(name.value, email.value, password.value)
                        Toast.makeText(
                            mContext,
                            "Account created successfully",
                            Toast.LENGTH_SHORT
                        ).show()
                        navController.popBackStack()
                    } else Toast.makeText(
                        mContext,
                        "Please check all filled details",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
fun CreateNewACScreenPreview() {
    CreateNewAC.CreateNewACScreen(
        navController = rememberNavController()
    )
}
