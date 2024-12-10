package com.example.uplift.data.repository

import android.util.Log
import com.example.uplift.data.models.Diary
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.auth.FirebaseAuth

class DiaryRepository {

    private val database = FirebaseDatabase.getInstance().reference
    private val auth = FirebaseAuth.getInstance()

    // Lấy UID của người dùng hiện tại
    private fun getUserId(): String {
        return auth.currentUser?.uid ?: ""
    }

    // Lưu nhật ký mới lên Firebase Realtime Database
    fun saveDiary(diary: Diary, onSuccess: (String) -> Unit) {
        val userId = getUserId()
        val diaryId = database.child("diaries").push().key ?: return

        val diaryToSave = diary.copy(
            diary_id = diaryId,  // Giữ lại diary_id
            uid = userId,
            date_created = System.currentTimeMillis(),
            date_modified = System.currentTimeMillis()
        )

        // Lưu dữ liệu vào Realtime Database
        database.child("diaries").child(diaryId).setValue(diaryToSave)
            .addOnSuccessListener {
                onSuccess(diaryId)
            }
            .addOnFailureListener {
                // Xử lý lỗi nếu cần
            }
    }

    fun deleteDiary(diaryId: String, onSuccess: () -> Unit, onFailure: (String) -> Unit) {
        val userId = getUserId()

        database.child("diaries").child(diaryId).removeValue()
            .addOnSuccessListener {
                onSuccess()
            }
            .addOnFailureListener { exception ->
                onFailure(exception.message ?: "Unknown error")
            }
    }

    // Lấy danh sách tất cả các nhật ký của người dùng
    fun getDiaries(onSuccess: (List<Diary>) -> Unit, onFailure: (String) -> Unit) {
        val userId = getUserId()

        database.child("diaries").orderByChild("uid").equalTo(userId)
            .get()
            .addOnSuccessListener { snapshot ->
                val diaries = mutableListOf<Diary>()
                for (data in snapshot.children) {
                    val diary = data.getValue(Diary::class.java)
                    if (diary != null) {
                        diaries.add(diary)
                    }
                }
                Log.d("DiaryRepository", "Retrieved diaries: $diaries")
                onSuccess(diaries)
            }
            .addOnFailureListener { exception ->
                onFailure(exception.message ?: "Unknown error")
            }
    }

    // Tìm nhật ký theo tiêu đề hoặc nội dung
    fun searchDiaries(query: String, onSuccess: (List<Diary>) -> Unit, onFailure: (String) -> Unit) {
        val userId = getUserId()

        database.child("diaries").orderByChild("uid").equalTo(userId)
            .get()
            .addOnSuccessListener { snapshot ->
                val diaries = mutableListOf<Diary>()
                for (data in snapshot.children) {
                    val diary = data.getValue(Diary::class.java)
                    if (diary != null && (diary.title.contains(query, ignoreCase = true) || diary.content.contains(query, ignoreCase = true))) {
                        diaries.add(diary)
                    }
                }
                onSuccess(diaries)
            }
            .addOnFailureListener { exception ->
                onFailure(exception.message ?: "Unknown error")
            }
    }

    fun getDiaryById(diaryId: String, onSuccess: (Diary) -> Unit, onFailure: (String) -> Unit) {
        val userId = getUserId()

        // Truy vấn Firebase để lấy nhật ký theo ID
        database.child("diaries").child(diaryId).get()
            .addOnSuccessListener { snapshot ->
                val diary = snapshot.getValue(Diary::class.java)
                if (diary != null) {
                    onSuccess(diary)  // Gọi callback onSuccess với dữ liệu nhật ký
                } else {
                    onFailure("Diary not found")  // Nếu không tìm thấy nhật ký
                }
            }
            .addOnFailureListener { exception ->
                onFailure(exception.message ?: "Unknown error")  // Nếu có lỗi
            }
    }

    fun updateDiary(diaryId: String, updatedDiary: Diary, onSuccess: (String) -> Unit) {
        val userId = getUserId()

        val updatedDiaryData = updatedDiary.copy(
            uid = userId,
            date_modified = System.currentTimeMillis()
        )
        database.child("diaries").child(diaryId).setValue(updatedDiaryData)
            .addOnSuccessListener {
                onSuccess(diaryId) // Gọi callback với diaryId sau khi cập nhật thành công
            }
            .addOnFailureListener {
                // Xử lý lỗi nếu cần
            }
    }
}

