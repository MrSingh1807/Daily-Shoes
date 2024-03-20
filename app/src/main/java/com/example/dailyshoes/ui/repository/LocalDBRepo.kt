package com.example.dailyshoes.ui.repository

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.dailyshoes.ui.dao.LocalDBDao
import com.example.dailyshoes.ui.modals.AboutOrder
import com.example.dailyshoes.ui.modals.User
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@ViewModelScoped
class LocalDBRepo @Inject constructor(private val localDb: LocalDBDao) {

    var cartList = mutableStateOf(listOf<AboutOrder>())

    suspend fun saveUserDetails(name: String, email: String, password: String) {
        withContext(Dispatchers.IO) {
            localDb.addUser(User(name, email, password))
        }
    }

    suspend fun getUsers(aboutUsers: (List<User>) -> Unit) {
        withContext(Dispatchers.IO) {
            val users = localDb.getUsers()
            aboutUsers.invoke(users)
            Log.d("Local DB", "getUsers: $users")
        }
    }

    suspend fun updatePassword(email: String, password: String, updated: (Boolean) -> Unit) {
        withContext(Dispatchers.IO) {
            launch { localDb.updateUserPassword(email, password) }.join()

            getUsers {
                it.forEach { user ->
                    if (user.email == email)
                        if (user.password == password) updated.invoke(true)
                }
            }
        }
    }

    suspend fun getShoes() {
        withContext(Dispatchers.IO) {
            val shoes = localDb.getShoesOnCart()
            Log.d("Local DB", "getShoes: $shoes")
            withContext(Dispatchers.Main) { cartList.value = shoes }
        }
    }

    suspend fun updateQuantity(id: Int, quantity: Int) {
        withContext(Dispatchers.IO) {
            localDb.updateShowQuantity(id, quantity)
        }
    }

    suspend fun addShoeOnCart(
        orderName: String, price: Double, quantity: Int = 1,
        shoeSizeCountry: String, shoeSize: Int, shoeImg: Int? = null
    ) {
        withContext(Dispatchers.IO) {
            val aboutOrder =
                AboutOrder(orderName, price, quantity, shoeSizeCountry, shoeSize, shoeImg)
            localDb.addShoeOnCart(aboutOrder)
        }
    }

    suspend fun deleteCartShoe(id: Int) {
        withContext(Dispatchers.IO) {
            localDb.deleteShoeOnCart(id)
        }
    }


}

