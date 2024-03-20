package com.example.dailyshoes.ui.modals

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "OrderDetails")
data class OrderDetails(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val orderID: String,
    val orderName: String,
    val quantity: Int,
    val price: Double
)
