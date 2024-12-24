package com.example.uplift.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.uplift.data.models.Question
import com.example.uplift.data.models.Answer
import com.example.uplift.data.repository.AnswersRepository
import com.example.uplift.data.repository.QuestionsRepository
import com.example.uplift.data.repository.TestResultsRepository

class QuestionsViewModel() : ViewModel() {
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
    private val testScores = mutableMapOf<Int, Double>()
    private val testAnswers = mutableMapOf<Int, MutableMap<Int, Double>>()

    private var currentTestId: Int = 0
    private val _currentQuestionIndex = MutableLiveData(0)
    val currentQuestionIndex: LiveData<Int> = _currentQuestionIndex
    private val _score = MutableLiveData(0.0)
    val score: LiveData<Double> get() = _score
    private var currentScore = 0.0

    private val selectedAnswers = mutableMapOf<Int, Double>()

    init {
        _currentQuestionIndex.value = 0
        _score.value = 0.0
    }
    fun startNewTest(testId: Int) {
        currentTestId = testId
        _currentQuestionIndex.value = 0
        _score.value = 0.0

        if (!testScores.containsKey(testId)) {
            testScores[testId] = 0.0
            testAnswers[testId] = mutableMapOf()
        }
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
    fun resetScore() {
        _score.value = 0.0
        currentScore = 0.0
        selectedAnswers.clear()
    }
    fun updateScore(questionId: Int, newValue: Double, questionCount: Int) {
        val previousValue = selectedAnswers[questionId] ?: 0.0
        currentScore -= previousValue
        currentScore += newValue
        _score.value = currentScore
        selectedAnswers[questionId] = newValue

        if (currentQuestionIndex.value!! < questionCount - 1) {
            moveToNextQuestion()
        }
    }
}