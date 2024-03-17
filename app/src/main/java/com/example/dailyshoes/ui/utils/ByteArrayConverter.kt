package com.example.dailyshoes.ui.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import java.io.ByteArrayOutputStream


@TypeConverters
class ByteArrayConverter {

    @TypeConverter
    fun fromByteArray(bytes: ByteArray): Bitmap {
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
    }

    @TypeConverter
    fun toByteArray(bitmap: Bitmap): ByteArray {
        val stream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
        return stream.toByteArray()
    }
}

private fun loadAndDisplayImage(btArray: ByteArray) =
    ByteArrayConverter().fromByteArray(btArray)


private fun Context.saveImageFromDrawable(drawableId: Int): ByteArray {
    val drawable = resources.getDrawable(drawableId, null)
    val bitmap = (drawable as BitmapDrawable).bitmap
    return ByteArrayConverter().toByteArray(bitmap)
}
