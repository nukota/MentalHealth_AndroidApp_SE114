import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.uplift.data.database.GadDatabase
import com.example.uplift.data.models.Gadquestions
import com.example.uplift.logic.repository.GadRepository
import kotlinx.coroutines.launch

class GadViewModel(application: Application) : AndroidViewModel(application) {
    private val gadRepository: GadRepository
    private val _allQuestions  = MutableLiveData<List<Gadquestions>>()
    val allQuestions: LiveData<List<Gadquestions>> get() = _allQuestions

    init {
        val gadQuestionsDao = GadDatabase.getDatabase(application).gadQuestionsDao()
        gadRepository = GadRepository(gadQuestionsDao)
        getAllQuestions()
    }
    private fun getAllQuestions() {
        viewModelScope.launch {
            gadRepository.getAllQuestions().observeForever { gadquestions ->
                _allQuestions.postValue(gadquestions)
            }
        }
    }
    fun insertQuestion(gadQuestions: Gadquestions) {
        viewModelScope.launch {
            gadRepository.insertQuestion(gadQuestions)
            getAllQuestions()
        }
    }
    fun updateQuestions(gadQuestionsList: List<Gadquestions>) {
        viewModelScope.launch {
            gadRepository.updateQuestions(gadQuestionsList)
            getAllQuestions()
        }
    }
    fun deleteAllQuestions() {
        viewModelScope.launch {
            gadRepository.deleteAllQuestions()
            _allQuestions.postValue(emptyList())
        }
    }
    fun deleteQuestionById(questionId:Int) {
        viewModelScope.launch {
            gadRepository.deleteQuestionById(questionId)
            getAllQuestions()
        }
    }
    fun getQuestionById(questionId: Int){
        viewModelScope.launch {
            val test = gadRepository.getQuestionById(questionId)
        }
    }
}