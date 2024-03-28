package com.example.dailyshoes.ui.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.ViewCompat
import androidx.lifecycle.lifecycleScope
import com.example.dailyshoes.R
import com.example.dailyshoes.ui.theme.DailyShoesTheme
import com.example.dailyshoes.ui.viewModel.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SplashActivity : ComponentActivity() {

    private val authVM: AuthViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        window.statusBarColor = android.R.color.transparent
        setContent {
            DailyShoesTheme {
                Greeting()
            }
        }
    }

    override fun onResume() {
        super.onResume()

        Handler(mainLooper).postDelayed({
            lifecycleScope.launch {
                authVM.checkIsLogin.collect {
                    if (it) startActivity(Intent(this@SplashActivity, HomeActivity::class.java))
                    else startActivity(Intent(this@SplashActivity, IntroActivity::class.java))
                }
            }
            finish()
        }, 2000)
    }

    @Composable
    fun Greeting() {
        Surface(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = R.drawable.splash_screen),
                contentDescription = "Splash Screen",
                modifier = Modifier.background(color = Color.Black)
            )
        }

    }

    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {
        DailyShoesTheme {
            Greeting()
        }
    }
}

