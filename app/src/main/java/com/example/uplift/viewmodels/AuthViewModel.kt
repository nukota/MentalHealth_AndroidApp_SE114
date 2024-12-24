package com.example.uplift.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.uplift.data.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

open class AuthViewModel() : ViewModel() {
    private val auth : FirebaseAuth = FirebaseAuth.getInstance()

    private val _authState = MutableLiveData<AuthState>()
    val authState = _authState

    val currentUser = auth.currentUser
    init {
        checkAuthStatus()
    }


    ////// edit profile
    private val database = FirebaseDatabase.getInstance().getReference("users")

    val currentUserUid: String
        get() = FirebaseAuth.getInstance().currentUser?.uid ?: ""

    fun saveUserData(user: User, onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
        Log.d("AuthViewModel", "Attempting to save user data: $user")
        database.child(user.uid).setValue(user)
            .addOnSuccessListener {
                Log.d("AuthViewModel", "User data saved successfully")
                _userData.postValue(user)  // Cập nhật lại dữ liệu người dùng
                onSuccess()
            }
            .addOnFailureListener { exception ->
                Log.e("AuthViewModel", "Error saving user data: ${exception.message}")
                onFailure(exception)
            }
    }


    private val _userData = MutableLiveData<User?>()
    open val userData: LiveData<User?> = _userData

    fun loadUserData() {
        val uid = currentUserUid
        if (uid.isBlank()) {
            Log.e("AuthViewModel", "User UID is missing")
            return
        }

        database.child(uid)
            .get()
            .addOnSuccessListener { snapshot ->
                val user = snapshot.getValue(User::class.java)
                _userData.postValue(user)
            }
            .addOnFailureListener { exception ->
                Log.e("AuthViewModel", "Failed to fetch user data: ${exception.message}")
            }
    }

    fun getUsername(): String? {
        val user = FirebaseAuth.getInstance().currentUser
        return user?.displayName
    }



    ////////////////////////////


    fun checkAuthStatus() {
        if (auth.currentUser == null) {
            _authState.value = AuthState.Unauthenticated
        } else {
            _authState.value = AuthState.Authenticated
        }
    }

    fun login(email: String, password: String) {
        if (email.isEmpty() || password.isEmpty()) {
            _authState.value = AuthState.Error("Please fill in all fields")
            return
        }
        _authState.value = AuthState.Loading
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    _authState.value = AuthState.Authenticated
                } else {
                    _authState.value =
                        AuthState.Error(task.exception?.message ?: "An unknown error occurred")
                }
            }
    }

    fun signUp(email: String, password1: String, password2: String, callback: (Boolean) -> Unit) {
        if (email.isEmpty() || password1.isEmpty() || password2.isEmpty()) {
            _authState.value = AuthState.Error("Please fill in all fields")
            callback(false)
            return
        }
        _authState.value = AuthState.Loading
        if (password1 != password2) {
            _authState.value = AuthState.Error("Passwords do not match")
            callback(false)
            return
        }
        auth.createUserWithEmailAndPassword(email, password1)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    _authState.value = AuthState.Authenticated
                    signOut()
                    callback(true)
                } else {
                    _authState.value =
                        AuthState.Error(task.exception?.message ?: "An unknown error occurred")
                    callback(false)
                }
            }
    }

    fun signOut() {
        auth.signOut()
        _authState.value = AuthState.Unauthenticated
    }

    fun sendPasswordResetEmail(email: String, callback: (Boolean) -> Unit) {
        auth.sendPasswordResetEmail(email)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    callback(true)
                } else {
                    callback(false)
                }
            }
    }

    fun getUserEmail(): String? {
        val user = FirebaseAuth.getInstance().currentUser
        return user?.email
    }

    fun getUserUid(): String? {
        val user = FirebaseAuth.getInstance().currentUser
        return user?.uid
    }
}


sealed class AuthState {
    object Unauthenticated : AuthState()
    object Authenticated : AuthState()
    object Loading : AuthState()
    data class Error(val message: String) : AuthState()
}