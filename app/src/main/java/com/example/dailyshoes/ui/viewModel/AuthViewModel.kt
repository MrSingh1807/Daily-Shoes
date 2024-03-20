package com.example.dailyshoes.ui.viewModel

import android.util.Patterns
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dailyshoes.ui.modals.User
import com.example.dailyshoes.ui.repository.LocalDBRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.regex.Pattern
import javax.inject.Inject


@HiltViewModel
class AuthViewModel @Inject constructor(private val repository: LocalDBRepo) : ViewModel() {

    fun isValidEmail(email: CharSequence): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun isValidPassword(password: CharSequence): Boolean {
        val pattern: Pattern = Pattern.compile(
            "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$"
        )
        return pattern.matcher(password).matches()
    }

    fun insertUser(name: String, email: String, password: String) {
        viewModelScope.launch {
            repository.saveUserDetails(name, email, password)
        }
    }

    fun forgotPassword(email: String, password: String, updated: (Boolean) -> Unit) {
        viewModelScope.launch {
            repository.updatePassword(email, password, updated)
        }
    }

    fun validateUserSignIn(email: String, password: String): Boolean {
        var isValidated = false
        viewModelScope.launch {
            repository.getUsers {
                it.forEach { user ->
                    if (user.email == email)
                        if (user.password == password) isValidated = true
                }
            }
        }
        return isValidated
    }


}