package com.example.uplift.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.uplift.data.models.Habit
import com.example.uplift.data.models.HabitLog
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class HabitRepository {
    private val habitDatabaseReference: DatabaseReference = FirebaseDatabase.getInstance().getReference("habits")
    private val habitLogDatabaseReference: DatabaseReference = FirebaseDatabase.getInstance().getReference("habitLog")

    private val _habits = MutableLiveData<List<Habit>>()
    val habits: LiveData<List<Habit>> get() = _habits

    private val _habitLogs = MutableLiveData<List<HabitLog>>()
    val habitLogs: LiveData<List<HabitLog>> get() = _habitLogs

    init {
        fetchHabits()
        fetchHabitLog()
    }

    fun getHabitById(habitId: Int): LiveData<Habit?> {
        val habit = _habits.value?.find { it.habit_id == habitId }
        val liveData = MutableLiveData<Habit?>()
        liveData.value = habit
        return liveData
    }

    fun getHabitLogByHabitLogId(habitLogId: Int): LiveData<HabitLog?> {
        val habitLog = _habitLogs.value?.find { it.habitLog_id == habitLogId }
        val liveData = MutableLiveData<HabitLog?>()
        liveData.value = habitLog
        return liveData
    }

    fun saveHabit(habit: Habit, onSuccess: () -> Unit, onFailure: (String) -> Unit) {
        val habitId =
            habitDatabaseReference.push().key ?: return onFailure("Failed to generate habit ID")
        habitDatabaseReference.child(habitId).setValue(habit)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { exception -> onFailure(exception.message ?: "Unknown error") }
    }

    fun saveHabitLog(habitLog: HabitLog) {
        val habitLogId = habitLogDatabaseReference.push().key ?: return
        habitLogDatabaseReference.child(habitLogId).setValue(habitLog)
            .addOnSuccessListener {
                Log.d("HabitRepository", "HabitLog saved successfully")
            }
            .addOnFailureListener { exception ->
                Log.e("HabitRepository", "Error saving HabitLog", exception)
            }
    }

    fun fetchHabitIds(onSuccess: (List<Int>) -> Unit, onFailure: (String) -> Unit) {
        habitDatabaseReference.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val habitIds =
                    snapshot.children.mapNotNull { it.getValue(Habit::class.java)?.habit_id }
                onSuccess(habitIds)
            }

            override fun onCancelled(error: DatabaseError) {
                onFailure(error.message)
            }
        })
    }

    fun fetchHabits() {
        habitDatabaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val habitList = mutableListOf<Habit>()
                for (habitSnapshot in snapshot.children) {
                    val habit = habitSnapshot.getValue(Habit::class.java)
                    if (habit != null) {
                        habitList.add(habit)
                    }
                }
                _habits.value = habitList
                Log.d("HabitRepository", "Fetched habits")
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("HabitRepository", "Error fetching habits", error.toException())
            }
        })
    }

    fun fetchHabitLog() {
        habitLogDatabaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val habitLogList = mutableListOf<HabitLog>()
                for (habitLogSnapshot in snapshot.children) {
                    val habitLog = habitLogSnapshot.getValue(HabitLog::class.java)
                    if (habitLog != null) {
                        habitLogList.add(habitLog)
                    }
                }
                _habitLogs.value = habitLogList
                Log.d("HabitRepository", "Fetched habit logs")
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("HabitRepository", "Error fetching habit logs", error.toException())
            }
        })
    }

    fun deleteHabit(habitId: Int, onSuccess: () -> Unit, onFailure: (String) -> Unit) {
        habitDatabaseReference.orderByChild("habit_id").equalTo(habitId.toDouble())
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        for (childSnapshot in snapshot.children) {
                            val firebaseDatabaseId = childSnapshot.key
                            if (firebaseDatabaseId != null) {
                                habitDatabaseReference.child(firebaseDatabaseId).removeValue()
                                    .addOnSuccessListener { onSuccess() }
                                    .addOnFailureListener { exception ->
                                        onFailure(
                                            exception.message ?: "Unknown error"
                                        )
                                    }
                                return
                            }
                        }
                    } else {
                        onFailure("Habit not found")
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    onFailure(error.message)
                }
            })
    }

    fun deleteHabitLog(habitLogId: Int, onSuccess: () -> Unit, onFailure: (String) -> Unit) {
        habitLogDatabaseReference.orderByChild("habitLog_id").equalTo(habitLogId.toDouble())
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        for (childSnapshot in snapshot.children) {
                            val firebaseDatabaseId = childSnapshot.key
                            if (firebaseDatabaseId != null) {
                                habitLogDatabaseReference.child(firebaseDatabaseId).removeValue()
                                    .addOnSuccessListener { onSuccess() }
                                    .addOnFailureListener { exception ->
                                        onFailure(exception.message ?: "Unknown error")
                                    }
                                return
                            }
                        }
                    } else {
                        onFailure("HabitLog not found")
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    onFailure(error.message)
                }
            })
    }

    fun updateHabitLogStatus(
        habitLogId: Int,
        newStatus: Int,
        onSuccess: (String) -> Unit,
        onFailure: (String) -> Unit
    ) {
        habitLogDatabaseReference.orderByChild("habitLog_id").equalTo(habitLogId.toDouble())
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        for (childSnapshot in snapshot.children) {
                            val firebaseDatabaseId = childSnapshot.key
                            if (firebaseDatabaseId != null) {
                                habitLogDatabaseReference.child(firebaseDatabaseId).child("status")
                                    .setValue(newStatus)
                                    .addOnSuccessListener { onSuccess(firebaseDatabaseId) }
                                    .addOnFailureListener { exception ->
                                        onFailure(
                                            exception.message ?: "Unknown error"
                                        )
                                    }
                                return
                            }
                        }
                    } else {
                        onFailure("HabitLog not found")
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    onFailure(error.message)
                }
            })
    }

    fun updateHabit(habit: Habit, onSuccess: () -> Unit, onFailure: (String) -> Unit) {
        habitDatabaseReference.orderByChild("habit_id").equalTo(habit.habit_id.toDouble())
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        for (childSnapshot in snapshot.children) {
                            val firebaseDatabaseId = childSnapshot.key
                            if (firebaseDatabaseId != null) {
                                habitDatabaseReference.child(firebaseDatabaseId).setValue(habit)
                                    .addOnSuccessListener { onSuccess() }
                                    .addOnFailureListener { exception ->
                                        onFailure(
                                            exception.message ?: "Unknown error"
                                        )
                                    }
                                return
                            }
                        }
                    } else {
                        onFailure("Habit not found")
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    onFailure(error.message)
                }
            })
    }

    fun insertHabitLog(habitLog: HabitLog, onSuccess: () -> Unit, onFailure: (String) -> Unit) {
        val newHabitLogRef = habitLogDatabaseReference.push()
        newHabitLogRef.setValue(habitLog)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { exception -> onFailure(exception.message ?: "Unknown error") }
    }
}