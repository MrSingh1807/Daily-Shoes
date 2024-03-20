package com.example.dailyshoes.ui.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.dailyshoes.ui.modals.AboutOrder
import com.example.dailyshoes.ui.modals.Order
import com.example.dailyshoes.ui.modals.OrderDetails
import com.example.dailyshoes.ui.modals.User


@Dao
interface LocalDBDao {

    /*todo: ***************   * Authenticate Order *   *******************/
    @Insert(entity = User::class)
    suspend fun addUser(user: User)

    @Query("SELECT * FROM users")
    suspend fun getUsers(): List<User>

    @Query("UPDATE users set password = :password WHERE email = :email")
    suspend fun updateUserPassword(email: String, password: String)


    /*todo: ***************   * Cart Order *   *******************/
    @Insert(entity = AboutOrder::class)
    suspend fun addShoeOnCart(aboutOrder: AboutOrder)

    @Query("UPDATE cart_table SET quantity = :quantity WHERE id = :id")
    suspend fun updateShowQuantity(id: Int, quantity: Int)

    @Query("SELECT * FROM cart_table")
    suspend fun getShoesOnCart(): List<AboutOrder>

    @Query("Delete FROM cart_table where id = :id")
    suspend fun deleteShoeOnCart(id: Int)


    /*todo: ***************   * Orders *   *******************/
    @Insert(entity = Order::class)
    suspend fun addAboutOrder(order: Order)

    @Query("SELECT * FROM orders")
    suspend fun getOrders(): List<Order>

    /*todo: ***************   * Orders Details *   *******************/
    @Insert(entity = OrderDetails::class)
    suspend fun addAboutOrderDetails(orderDetails: OrderDetails)

    @Query("SELECT * FROM orderdetails")
    suspend fun getOrdersDetails(): List<OrderDetails>

}

// Demo Add Query
// @Query("INSERT INTO Users (name, email, password) VALUES (:name, :email, :password)")
// suspend fun insertUser(name: String, email: String, password: String)
