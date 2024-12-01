package com.example.uplift.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.uplift.data.models.Question
import com.example.uplift.data.models.Answer
import com.example.uplift.data.repository.AnswersRepository
import com.example.uplift.data.repository.QuestionsRepository

class QuestionsViewModel(private val testId: Int = 0) : ViewModel() {
    private val questionRepository = QuestionsRepository()
    private val answersRepository = AnswersRepository()
    val questions: LiveData<List<Question>> = questionRepository.questions
    val answers: LiveData<List<Answer>> = answersRepository.answers


    fun getQuestionByTestIdAndOrder(testId: Int, questionOrder: Int): LiveData<Question?> {
        return questionRepository.getQuestionByTestIdAndOrder(testId, questionOrder)
    }

    fun getAnswerByTestIdQuestionIdAndAnswerOder(
        testId: Int,
        questionId: Int,
        answerOrder: Int
    ): LiveData<Answer?> {
        return answersRepository.getAnswerByTestIdQuestionIdAndAnswerOder(
            testId,
            questionId,
            answerOrder
        )
    }

    private val _currentQuestionIndex = MutableLiveData(0)
    val currentQuestionIndex: LiveData<Int> = _currentQuestionIndex
    private val _score = MutableLiveData(0.0)
    val score: LiveData<Double> get() = _score
    private var currentScore = 0.0

    // Store selected answers and their values
    private val selectedAnswers = mutableMapOf<Int, Double>()

    init {
        _currentQuestionIndex.value = 0
        _score.value = 0.0
    }

    fun moveToNextQuestion() {
        val currentIndex = _currentQuestionIndex.value ?: 0
        _currentQuestionIndex.value = currentIndex + 1
    }

    fun moveToPreviousQuestion() {
        val currentIndex = _currentQuestionIndex.value ?: 0
        if (currentIndex > 0) {
            _currentQuestionIndex.value = currentIndex - 1
        }
    }

    fun updateScore(questionId: Int, newValue: Double, questionCount: Int) {
        // Subtract the previous value if it exists
        val previousValue = selectedAnswers[questionId] ?: 0.0
        currentScore -= previousValue
        // Add the new value
        currentScore += newValue
        _score.value = currentScore
        // Update the selected answer
        selectedAnswers[questionId] = newValue
        Log.d("QuestionsViewModel", "Score: $currentScore")
        if (currentQuestionIndex.value!! < questionCount - 1) {
            moveToNextQuestion()
        }
    }
}