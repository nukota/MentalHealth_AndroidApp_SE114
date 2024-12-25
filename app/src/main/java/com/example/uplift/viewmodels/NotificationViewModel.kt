// NotificationViewModel.kt
package com.example.uplift.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class NotificationViewModel : ViewModel() {
    val allowSMS = mutableStateOf(false)
    val allowPopUp = mutableStateOf(false)
}