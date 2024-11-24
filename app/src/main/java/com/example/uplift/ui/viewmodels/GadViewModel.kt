import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.uplift.data.models.Gadanswers
import com.example.uplift.data.models.Gadquestions
import com.example.uplift.logic.repository.GadRepository

class GadViewModel( private val gadRepository: GadRepository) : ViewModel() {
    val allQuestions: LiveData<List<Gadquestions>> = gadRepository.getAllQuestions()

    val allAnswers: LiveData<List<Gadanswers>> = gadRepository.getAllAnswers()

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