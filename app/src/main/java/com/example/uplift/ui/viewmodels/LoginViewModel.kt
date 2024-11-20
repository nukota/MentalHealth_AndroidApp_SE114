package com.example.uplift.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uplift.logic.repository.UserRepository
import kotlinx.coroutines.launch

class LoginViewModel(private val userRepository: UserRepository) : ViewModel() {

    var loginResult: Boolean = false

    fun login(username: String, password: String) {
        viewModelScope.launch {
            loginResult = userRepository.verifyUser(username, password)
        }
    }
}