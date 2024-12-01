package com.example.uplift.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.uplift.data.models.Answer
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class AnswersRepository() {
    private val databaseReference: DatabaseReference = FirebaseDatabase.getInstance().getReference("answers")

    private val _answers = MutableLiveData<List<Answer>>()
    val answers: LiveData<List<Answer>> get() = _answers

    init {
        fetchAnswers()
    }

    fun getAnswerByTestIdQuestionIdAndAnswerOder(testId: Int, questionId: Int, answerOrder : Int): LiveData<Answer?> {
        val answer = _answers.value?.find { it.test_id == testId && it.answer_order == answerOrder && it.question_id == questionId }
        val liveData = MutableLiveData<Answer?>()
        liveData.value = answer
        return liveData
    }

    private fun fetchAnswers() {
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val answerList = mutableListOf<Answer>()
                for (answerSnapshot in snapshot.children) {
                    val answer = answerSnapshot.getValue(Answer::class.java)
                    if (answer != null) {
                        answerList.add(answer)
                    }
                }
                _answers.value = answerList
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("AnswerRepository", "Error fetching answers", error.toException())
            }
        })
    }
}