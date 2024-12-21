package com.example.uplift.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.uplift.data.models.User
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class UserRepository {
    private val databaseReference: DatabaseReference = FirebaseDatabase.getInstance().getReference("users")

    private val _users = MutableLiveData<List<User>>()
    val users: LiveData<List<User>> get() = _users

    init {
        fetchUsers()
    }

    fun getUserById(UId: String): LiveData<User?> {
        val user = _users.value?.find { it.uid == UId }
        val liveData = MutableLiveData<User?>()
        liveData.value = user
        return liveData
    }

    private fun fetchUsers() {
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val userList = mutableListOf<User>()
                for (userSnapshot in snapshot.children) {
                    val user = userSnapshot.getValue(User::class.java)
                    if (user != null) {
                        userList.add(user)
                    }
                }
                _users.value = userList
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("UserRepository", "Error fetching users", error.toException())
            }
        })
    }
    fun getUserDetails(userId: String): LiveData<User?> {
        val userLiveData = MutableLiveData<User?>()
        databaseReference.child(userId).get().addOnSuccessListener { dataSnapshot ->
            val user = dataSnapshot.getValue(User::class.java)
            userLiveData.value = user
        }
        return userLiveData
    }
}