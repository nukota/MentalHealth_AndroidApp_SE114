import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.uplift.data.models.Diary
import com.example.uplift.data.repository.DiaryRepository
import com.google.firebase.database.FirebaseDatabase

class DiaryViewModel : ViewModel() {

    private val diaryRepository = DiaryRepository()

    // LiveData để lưu danh sách nhật ký
    private val _diaries = MutableLiveData<List<Diary>>()
    val diaries: LiveData<List<Diary>> = _diaries

    private val _searchQuery = MutableLiveData("")

    val searchQuery: LiveData<String> get() = _searchQuery

    // LiveData để lưu nhật ký hiện tại
    private val _currentDiary = MutableLiveData<Diary?>()
    val currentDiary: LiveData<Diary?> = _currentDiary

    init {
        // Load diaries initially (you can replace this with actual loading logic)
        getDiaries()
    }

    fun getDiaries() {
        Log.d("DiaryViewModel", "Fetching diaries...")  // Log khi bắt đầu lấy dữ liệu
        diaryRepository.getDiaries(
            onSuccess = { diariesList ->
                Log.d("DiaryViewModel", "Diaries fetched: $diariesList")  // Log dữ liệu nhận được
                _diaries.value = diariesList
            },
            onFailure = { errorMessage ->
                Log.d("DiaryViewModel", "Failed to fetch diaries: $errorMessage")  // Log nếu có lỗi
            }
        )
    }

    fun deleteDiary(diaryId: String) {
        diaryRepository.deleteDiary(
            diaryId = diaryId,
            onSuccess = {
                // Cập nhật lại danh sách nhật ký sau khi xóa
                _diaries.value = _diaries.value?.filter { it.diary_id != diaryId }
            },
            onFailure = { errorMessage ->
                Log.d("DiaryViewModel", "Failed to delete diary: $errorMessage")
            }
        )
    }

    // Lấy nhật ký theo ID
    fun getDiaryById(diaryId: String) {
        diaryRepository.getDiaryById(
            diaryId = diaryId,
            onSuccess = { diary ->
                _currentDiary.value = diary  // Cập nhật LiveData với nhật ký đã lấy
            },
            onFailure = { errorMessage ->
                // Xử lý lỗi nếu cần
                Log.d("DiaryViewModel", "Failed to fetch diary: $errorMessage")
            }
        )
    }

    // Lưu nhật ký mới và cập nhật trực tiếp vào _diaries
    fun saveDiary(diary: Diary, onSuccess: (String) -> Unit) {
        diaryRepository.saveDiary(diary) { diaryId ->
            // Khi lưu thành công vào Firebase, cập nhật lại _diaries trực tiếp
            val currentList = _diaries.value ?: emptyList()
            _diaries.value = currentList + diary // Thêm nhật ký mới vào danh sách
            onSuccess(diaryId)
        }
    }

    // Cập nhật nhật ký
    fun updateDiary(updatedDiary: Diary, onSuccess: () -> Unit) {
        diaryRepository.updateDiary(updatedDiary.diary_id, updatedDiary) { diaryId ->
            // Cập nhật lại LiveData sau khi thay đổi thành công
            getDiaryById(diaryId) // Gọi lại hàm lấy nhật ký theo ID để làm mới _currentDiary
            onSuccess() // Callback sau khi cập nhật thành công
        }
    }

    // Tìm nhật ký theo tiêu đề hoặc nội dung
    fun searchDiaries(query: String) {
        _searchQuery.value = query
        diaryRepository.searchDiaries(query,
            onSuccess = { diariesList ->
                _diaries.value = diariesList // Cập nhật LiveData với danh sách nhật ký tìm được
            },
            onFailure = { errorMessage ->
                Log.d("DiaryViewModel", "Failed to search diaries: $errorMessage")
            }
        )
    }
}

