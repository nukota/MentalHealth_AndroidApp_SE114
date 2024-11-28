package com.example.uplift.ui.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.uplift.data.models.story
import com.example.uplift.logic.repository.StoryRepository

class StoryViewModel : ViewModel() {
    private val repository = StoryRepository()
    val stories: LiveData<List<story>> = repository.stories

    fun getStoryById(storyId: Int): LiveData<story?> {
        return repository.getStoryById(storyId)
    }
}