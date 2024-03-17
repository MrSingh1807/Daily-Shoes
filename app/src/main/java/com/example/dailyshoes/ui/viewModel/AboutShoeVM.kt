package com.example.dailyshoes.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dailyshoes.ui.modals.AboutOrder
import com.example.dailyshoes.ui.repository.LocalDBRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class AboutShoeVM @Inject constructor(private val repository: LocalDBRepo) : ViewModel() {


    fun addShoeOnCart(
        orderName: String, price: Double, quantity: Int,
        shoeSizeCountry: String, shoeSize: Int, shoeImg: Int? = null
    ) {
        viewModelScope.launch {
            repository.addShoeOnCart(orderName, price, quantity, shoeSizeCountry, shoeSize, shoeImg)
        }
    }

}