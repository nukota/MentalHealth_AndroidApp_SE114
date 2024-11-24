import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.uplift.data.models.Answer
import com.example.uplift.data.models.Questions
import com.example.uplift.logic.repository.QuestionsRepository

class QuestionsViewModel(private val questionsRepository: QuestionsRepository) : ViewModel() {
    val allQuestions: LiveData<List<Questions>> = questionsRepository.getAllQuestions()

    val allAnswers: LiveData<List<Answer>> = questionsRepository.getAllAnswers()

    private val _currentQuestionIndex = MutableLiveData(0)
    val currentQuestionIndex: LiveData<Int> get() = _currentQuestionIndex

    private val _score = MutableLiveData(0)
    val score: LiveData<Int> get() = _score
    private var currentScore = 0
    init {
        _currentQuestionIndex.value=0
        _score.value=0
    }

    fun moveToNextQuestion() {
        val newIndex = (_currentQuestionIndex.value ?: 0) + 1
        _currentQuestionIndex.value = newIndex
    }
    fun moveToPreviousQuestion() {
        val newIndex = (_currentQuestionIndex.value ?: 0) - 1
        _currentQuestionIndex.value = newIndex
    }

    fun updateScore(value: Int) {
        currentScore += value
        _score.value = currentScore
    }

}