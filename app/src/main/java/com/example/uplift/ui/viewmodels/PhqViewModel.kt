import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.uplift.data.database.GadDatabase
import com.example.uplift.data.database.MhiDatabase
import com.example.uplift.data.database.PhqDatabase
import com.example.uplift.data.models.Gadquestions
import com.example.uplift.data.models.Mhiquestions
import com.example.uplift.data.models.Phqquestions
import com.example.uplift.logic.repository.GadRepository
import com.example.uplift.logic.repository.MhiRepository
import com.example.uplift.logic.repository.PhqRepository
import kotlinx.coroutines.launch

class PhqViewModel(application: Application) : AndroidViewModel(application) {
    private val phqRepository: PhqRepository
    private val _allQuestions  = MutableLiveData<List<Phqquestions>>()
    val allQuestions: LiveData<List<Phqquestions>> get() = _allQuestions

    init {
        val phqQuestionsDao = PhqDatabase.getDatabase(application).phqQuestionsDao()
        phqRepository = PhqRepository(phqQuestionsDao)
        getAllQuestions()
    }
    private fun getAllQuestions() {
        viewModelScope.launch {
            phqRepository.getAllQuestions().observeForever { gadquestions ->
                _allQuestions.postValue(gadquestions)
            }
        }
    }
    fun insertQuestion(phqQuestions: Phqquestions) {
        viewModelScope.launch {
            phqRepository.insertQuestion(phqQuestions)
            getAllQuestions()
        }
    }
    fun updateQuestions(phqQuestionsList: List<Phqquestions>) {
        viewModelScope.launch {
            phqRepository.updateQuestions(phqQuestionsList)
            getAllQuestions()
        }
    }
    fun deleteAllQuestions() {
        viewModelScope.launch {
            phqRepository.deleteAllQuestions()
            _allQuestions.postValue(emptyList())
        }
    }
    fun deleteQuestionById(questionId:Int) {
        viewModelScope.launch {
            phqRepository.deleteQuestionById(questionId)
            getAllQuestions()
        }
    }
    fun getQuestionById(questionId: Int){
        viewModelScope.launch {
            val test = phqRepository.getQuestionById(questionId)
        }
    }
}