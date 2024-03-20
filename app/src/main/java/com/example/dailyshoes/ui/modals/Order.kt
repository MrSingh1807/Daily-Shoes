package com.example.dailyshoes.ui.modals

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Orders")
data class Order(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val orderID: String,
    val trackingID: String,
    val orderDate: String,
    val totalPrice: Double,
    val status: String,
    val deliveryStatus: String
)


