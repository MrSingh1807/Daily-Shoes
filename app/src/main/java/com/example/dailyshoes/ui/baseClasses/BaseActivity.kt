package com.example.dailyshoes.ui.baseClasses

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import com.example.dailyshoes.ui.theme.DailyShoesTheme


abstract class BaseComposeActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initView()
        setContent {
            DailyShoesTheme { InitScreen() }
        }
    }

    open fun initView() {}

    @Composable
    abstract fun InitScreen()

}