package com.example.dailyshoes.ui.viewModel

import android.util.Log
import android.util.Patterns
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dailyshoes.ui.dataStore.LocalDataStore
import com.example.dailyshoes.ui.modals.User
import com.example.dailyshoes.ui.repository.LocalDBRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.regex.Pattern
import javax.inject.Inject


@HiltViewModel
class AuthViewModel @Inject constructor(
    private val localPrefDataStore: LocalDataStore,
    private val repository: LocalDBRepo
) : ViewModel() {
    val AUTH_TAG = "AUTH_VM_TAG"
    val checkIsLogin = localPrefDataStore.checkIsLogin

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


    fun checkEmailForResetPassword(email: String): Boolean {
        var isValidated = false
        viewModelScope.launch {
            repository.getUsers {
                Log.d(AUTH_TAG, "checkEmailForResetPassword : $it ")
                it.forEach { user ->
                    if (user.email == email) isValidated = true
                }
            }
        }
        return isValidated
    }

    fun resetPassword(email: String, password: String, isUpdated: () -> Unit) {
        viewModelScope.launch {
            repository.updatePassword(email, password, isUpdated)
        }
    }

    fun isLoginValidate() {
        viewModelScope.launch { localPrefDataStore.saveIsLogin(true) }
    }

    fun validateUserSignIn(
        email: String,
        password: String,
        isValidate: (Boolean) -> Unit
    ) {
        var isValidated = false
        viewModelScope.launch {
            repository.getUsers {
                Log.d(AUTH_TAG, "validateUserSignIn : $it ")
                it.forEach { user ->
                    if (user.email == email && user.password == password) isValidated = true
                }
                isValidate.invoke(isValidated)
            }
        }
    }


}