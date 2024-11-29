package com.example.uplift.viewmodels

import androidx.lifecycle.ViewModel
import com.example.uplift.data.repository.UserRepository

class LoginViewModel() : ViewModel() {
    private val userRepository = UserRepository()

}