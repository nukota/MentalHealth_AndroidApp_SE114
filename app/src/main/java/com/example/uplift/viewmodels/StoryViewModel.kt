package com.example.uplift.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.uplift.data.models.Story
import com.example.uplift.data.repository.StoryRepository

class StoryViewModel : ViewModel() {
    private val repository = StoryRepository()
    val stories: LiveData<List<Story>> = repository.stories

    fun getStoryById(storyId: Int): LiveData<Story?> {
        return repository.getStoryById(storyId)
    }
}