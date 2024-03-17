package com.example.dailyshoes.ui.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.dailyshoes.ui.modals.AboutOrder


@Dao
interface LocalDBDao {

    @Insert
    suspend fun addShoeOnCart(aboutOrder: AboutOrder)

    @Query("UPDATE cart_table SET quantity = :quantity WHERE id = :id")
    suspend fun updateShowQuantity(id: Int, quantity: Int)

    @Query("SELECT * FROM cart_table")
    suspend fun getShoesOnCart(): List<AboutOrder>

    @Query("Delete FROM cart_table where id = :id")
    suspend fun deleteShoeOnCart(id: Int)

}