package com.example.uplift.viewmodels

import android.content.Context
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uplift.data.models.Diary
import com.example.uplift.data.repository.DiaryRepository
import com.example.uplift.utils.NotificationHelper
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

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

    fun deleteDiary(diaryId: Int) {
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
    fun getDiaryById(diaryId: Int) {
        _currentDiary.value = null
        Log.d("DiaryViewModel", "Fetching diary with ID: $diaryId")
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
            getDiaries()
            onSuccess(diaryId)
        }
    }

    // Cập nhật nhật ký
    fun updateDiary(updatedDiary: Diary, onSuccess: () -> Unit) {
        diaryRepository.updateDiary(updatedDiary.diary_id, updatedDiary) {
            // Cập nhật lại LiveData sau khi thay đổi thành công
            getDiaryById(updatedDiary.diary_id) // Gọi lại hàm lấy nhật ký theo ID để làm mới _currentDiary
            getDiaries()
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

    @RequiresApi(Build.VERSION_CODES.O)
    fun addDiary(
        title: String,
        content: String,
        uid: String,
        context: Context,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        val existingIds = _diaries.value?.map { it.diary_id } ?: emptyList()
        val newDiaryId = (existingIds.maxOrNull() ?: 0) + 1
        val currentDate = LocalDateTime.now()
        val newDiary = Diary(
            diary_id = newDiaryId,
            title = title,
            content = content,
            uid = uid,
            date_created = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(currentDate),
            date_modified = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(currentDate)
        )

        // Save the new diary to the database
        FirebaseDatabase.getInstance().reference.child("diaries").child(newDiaryId.toString()).setValue(newDiary)
            .addOnSuccessListener {
                // Update LiveData with the new diary
                val currentList = _diaries.value ?: emptyList()
                _diaries.value = currentList + newDiary
                onSuccess()
                NotificationHelper.showNotification(
                    context,
                    "Diary Added",
                    "Your diary has been successfully added."
                )
            }
            .addOnFailureListener { exception ->
                onFailure(exception.message ?: "Unknown error")
                NotificationHelper.showNotification(
                    context,
                    "Diary Add Failed",
                    "Failed to add your diary."
                )
            }
        getDiaries()
    }
}

