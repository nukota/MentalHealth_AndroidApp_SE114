package com.example.uplift.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.uplift.data.models.Story
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class StoryRepository {
    private val databaseReference: DatabaseReference = FirebaseDatabase.getInstance().getReference("stories")

    private val _stories = MutableLiveData<List<Story>>()
    val stories: LiveData<List<Story>> get() = _stories

    init {
        fetchStories()
    }

    fun getStoryById(storyId: Int): LiveData<Story?> {
        val story = _stories.value?.find { it.story_id == storyId }
        val liveData = MutableLiveData<Story?>()
        liveData.value = story
        return liveData
    }

    private fun fetchStories() {
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val storyList = mutableListOf<Story>()
                for (storySnapshot in snapshot.children) {
                    val story = storySnapshot.getValue(Story::class.java)
                    if (story != null) {
                        storyList.add(story)
                    }
                }
                _stories.value = storyList
                Log.d("StoryRepository", "Fetched stories")
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("StoryRepository", "Error fetching stories", error.toException())
            }
        })
    }
}