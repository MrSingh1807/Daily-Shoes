package com.example.dailyshoes.ui.modals

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Users")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val email: String,
    val password: String
) {
    constructor(name: String, email: String, password: String) : this(0, name, email, password)
}