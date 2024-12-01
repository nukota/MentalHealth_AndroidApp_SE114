package com.example.uplift.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.uplift.data.models.Question
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class QuestionsRepository() {
    private val databaseReference: DatabaseReference = FirebaseDatabase.getInstance().getReference("questions")

    private val _questions = MutableLiveData<List<Question>>()
    val questions: LiveData<List<Question>> get() = _questions

    init {
        fetchQuestions()
    }

    fun getQuestionByTestIdAndOrder(testId: Int, questionOrder: Int): LiveData<Question?> {
        val question = _questions.value?.find { it.test_id == testId && it.question_order == questionOrder }
        val liveData = MutableLiveData<Question?>()
        liveData.value = question
        return liveData
    }

    private fun fetchQuestions() {
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val questionList = mutableListOf<Question>()
                for (questionSnapshot in snapshot.children) {
                    val question = questionSnapshot.getValue(Question::class.java)
                    if (question != null) {
                        questionList.add(question)
                    }
                }
                _questions.value = questionList
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("QuestionRepository", "Error fetching questions", error.toException())
            }
        })
    }
}