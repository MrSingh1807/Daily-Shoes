package com.example.dailyshoes.ui.utils

import android.app.Activity
import android.content.Context
import android.content.Intent


infix fun <T> Context.navigateToActivity(anotherActivity: Class<T>) {
    val intent = Intent(this, anotherActivity)
    startActivity(intent)
}
