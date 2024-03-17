package com.example.dailyshoes.ui.viewModel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dailyshoes.ui.repository.LocalDBRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CartVM @Inject constructor(private val repository: LocalDBRepo) : ViewModel() {

    val cartList = repository.cartList
    var prices = mutableStateOf(Pair(0.0, 0.0))

    init {
        getShoes()
    }

    private fun getShoes() {
        viewModelScope.launch {
            launch { repository.getShoes() }.join()

            val subTotal = repository.cartList.component1().sumOf { it.price * it.quantity }
            val tax = subTotal * 0.13

            prices.value = Pair(subTotal, tax)
        }
    }

    fun updateQuantity(id: Int, quantity: Int) {
        viewModelScope.launch {
            launch { repository.updateQuantity(id, quantity) }.join()
        }
        getShoes()
    }

    fun deleteShoe(id: Int) {
        viewModelScope.launch {
            launch { repository.deleteCartShoe(id) }
        }
        getShoes()
    }

}