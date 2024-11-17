import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.uplift.data.database.GadDatabase
import com.example.uplift.data.database.PhqDatabase
import com.example.uplift.data.models.Gadquestions
import com.example.uplift.data.models.Phqquestions
import com.example.uplift.logic.repository.GadRepository
import com.example.uplift.logic.repository.PhqRepository
import kotlinx.coroutines.launch

class PhqViewModel(application: Application) : AndroidViewModel(application) {
    private val phqRepository: PhqRepository
    private val allQuestions: LiveData<List<Phqquestions>>

    init {
        val phqQuestionsDao = PhqDatabase.getDatabase(application).phqQuestionsDao()
        phqRepository = PhqRepository(phqQuestionsDao)
        allQuestions = phqRepository.getAllQuestions()
    }

    fun insertAll(listPhqQuestions: List<Phqquestions>) {
        viewModelScope.launch {
            phqRepository.insertAll(listPhqQuestions)
        }
    }
    fun addQuestions(phqQuestions: Phqquestions) {
        viewModelScope.launch {
            phqRepository.addQuestions(phqQuestions)
        }
    }
    fun updateQuestions(phqQuestions: List<Phqquestions>) {
        viewModelScope.launch {
            phqRepository.updateQuestions(phqQuestions)
        }
    }
    fun deleteQuestions(phqQuestions: Phqquestions) {
        viewModelScope.launch {
            phqRepository.deleteQuestions(phqQuestions)
        }
    }
    fun deleteAllQuestions() {
        viewModelScope.launch {
            phqRepository.deleteAllQuestions()
        }
    }
}