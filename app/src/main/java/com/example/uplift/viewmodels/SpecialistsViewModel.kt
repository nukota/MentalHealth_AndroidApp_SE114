package com.example.uplift.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.uplift.data.models.Specialist
import com.example.uplift.data.repository.SpecialistsRepository

class SpecialistsViewModel : ViewModel() {
    private val repository = SpecialistsRepository()
    val specialists: LiveData<List<Specialist>> = repository.specialists

    fun getSpecialistById(specialistId: Int): LiveData<Specialist?> {
        return repository.getSpecialistById(specialistId)
    }
}