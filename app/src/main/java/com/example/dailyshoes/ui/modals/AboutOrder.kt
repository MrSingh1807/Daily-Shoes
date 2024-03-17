package com.example.dailyshoes.ui.modals

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Cart_Table")
data class AboutOrder(
    @PrimaryKey(true)
    val id: Int = 0,
    val orderName: String,
    val price: Double,
    val quantity: Int,
    val shoeSizeCountry: String,
    val shoeSize: Int,
    val shoeImg: Int? = null
) {
    constructor(orderName: String, price: Double, quantity: Int)
            : this(0, orderName, price, quantity, "", 1) // dummy constructor

    constructor(
        orderName: String, price: Double, quantity: Int,
        shoeSizeCountry: String, shoeSize: Int, shoeImg: Int?
    ) : this(0, orderName, price, quantity, shoeSizeCountry, shoeSize, shoeImg)
}
