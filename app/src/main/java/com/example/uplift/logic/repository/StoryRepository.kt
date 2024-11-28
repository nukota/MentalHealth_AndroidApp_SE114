package com.example.uplift.logic.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.uplift.data.models.story
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class StoryRepository {
    private val databaseReference: DatabaseReference = FirebaseDatabase.getInstance().getReference("story")

    private val _stories = MutableLiveData<List<story>>()
    val stories: LiveData<List<story>> get() = _stories

    init {
        fetchStories()
    }

    fun getStoryById(storyId: Int): LiveData<story?> {
        val story = _stories.value?.find { it.story_id == storyId }
        val liveData = MutableLiveData<story?>()
        liveData.value = story
        return liveData
    }

    private fun fetchStories() {
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val storyList = mutableListOf<story>()
                for (storySnapshot in snapshot.children) {
                    val story = storySnapshot.getValue(story::class.java)
                    if (story != null) {
                        storyList.add(story)
                    }
                }
                _stories.value = storyList
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("StoryRepository", "Error fetching stories", error.toException())
            }
        })
    }
}