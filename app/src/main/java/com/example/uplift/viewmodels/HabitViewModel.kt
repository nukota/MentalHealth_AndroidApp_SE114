package com.example.uplift.viewmodels

import androidx.lifecycle.ViewModel
import com.example.uplift.data.repository.HabitRepository

class HabitViewModel : ViewModel() {
    private val repository = HabitRepository()

}