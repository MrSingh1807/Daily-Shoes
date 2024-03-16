package com.example.dailyshoes.ui.utils

import android.app.Activity
import android.content.Intent


infix fun <T> Activity.navigateToActivity(anotherActivity: Class<T>) {
    val intent = Intent(this, anotherActivity)
    startActivity(intent)
}
