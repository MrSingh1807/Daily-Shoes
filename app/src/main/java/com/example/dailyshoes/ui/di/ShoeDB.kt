package com.example.dailyshoes.ui.di

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.dailyshoes.ui.dao.LocalDBDao
import com.example.dailyshoes.ui.modals.AboutOrder
import com.example.dailyshoes.ui.modals.Order
import com.example.dailyshoes.ui.modals.OrderDetails
import com.example.dailyshoes.ui.modals.User


@Database(
    entities = [AboutOrder::class, User::class, Order::class, OrderDetails::class],
    version = 1
)
abstract class ShoeDB : RoomDatabase() {

    abstract fun localDB(): LocalDBDao

}

val DB_NAME = "SHOE_DB"