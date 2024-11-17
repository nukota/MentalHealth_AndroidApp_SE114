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
    private val allQuestions: LiveData<List<Gadquestions>>

    init {
        val gadQuestionsDao = GadDatabase.getDatabase(application).gadQuestionsDao()
        gadRepository = GadRepository(gadQuestionsDao)
        allQuestions = gadRepository.getAllQuestions()
    }

    fun insertAll(listGadQuestions: List<Gadquestions>) {
        viewModelScope.launch {
            gadRepository.insertAll(listGadQuestions)
        }
    }
    fun addQuestions(gadQuestions: Gadquestions) {
        viewModelScope.launch {
            gadRepository.addQuestions(gadQuestions)
        }
    }
    fun updateQuestions(gadQuestions: List<Gadquestions>) {
        viewModelScope.launch {
            gadRepository.updateQuestions(gadQuestions)
        }
    }
    fun deleteQuestions(gadQuestions: Gadquestions) {
        viewModelScope.launch {
            gadRepository.deleteQuestions(gadQuestions)
        }
    }
    fun deleteAllQuestions() {
        viewModelScope.launch {
            gadRepository.deleteAllQuestions()
        }
    }
}