package com.example.uplift.viewmodels

import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uplift.data.models.Habit
import com.example.uplift.data.models.HabitLog
import com.example.uplift.data.repository.HabitRepository
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.temporal.ChronoField
import java.time.temporal.ChronoUnit

class HabitViewModel : ViewModel() {
    private val repository = HabitRepository()
    var habits: LiveData<List<Habit>> = repository.habits
    var habitLogs: LiveData<List<HabitLog>> = repository.habitLogs

    fun getHabits() {
        repository.fetchHabits()
        habits = repository.habits
    }

    fun getHabitLogs() {
        repository.fetchHabitLog()
        habitLogs = repository.habitLogs
    }

    init {
        getHabits()
        getHabitLogs()
    }

    fun getHabitById(habitId: Int): LiveData<Habit?> {
        return repository.getHabitById(habitId)
    }

    fun getHabitLogByHabitLogId(habitLogId: Int): LiveData<HabitLog?> {
        return repository.getHabitLogByHabitLogId(habitLogId)
    }

    fun getHabitLogsForHabit(habitId: Int): List<HabitLog> {
        return habitLogs.value?.filter { it.habit_id == habitId } ?: emptyList()
    }

    fun deleteHabit(habitId: Int, onSuccess: () -> Unit, onFailure: (String) -> Unit) {
        repository.deleteHabit(habitId, {
            // Delete associated habit logs
            val logsToDelete = habitLogs.value?.filter { it.habit_id == habitId } ?: emptyList()
            logsToDelete.forEach { log ->
                repository.deleteHabitLog(log.habitLog_id, {
                    Log.d("HabitLog", "Deleted habit log with ID ${log.habitLog_id}")
                }, {
                    Log.d("HabitLog", "Failed to delete habit log with ID ${log.habitLog_id}")
                })
            }
            getHabits() // Refresh the habits list
            getHabitLogs() // Refresh the habit logs list
            onSuccess()
        }, onFailure)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun updateHabitLogStatus(
        habitLogId: Int,
        newStatus: Int,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        repository.updateHabitLogStatus(habitLogId, newStatus, {
            getHabits()
            getHabitLogs()
            val habitLog = habitLogs.value?.find { it.habitLog_id == habitLogId }
            habitLog?.let {
                Log.d("HabitViewModel", "Updating habit log status for habit ID ${it.habit_id}")
                val habitId = it.habit_id
                val habit = habits.value?.find { it.habit_id == habitId }
                habit?.let {
                    Log.d("HabitViewModel", "Updating habit streak and completion rate for habit ID ${it.habit_id}")
                    val updatedStreak = calculateStreak(habitId)
                    val updatedCompletionRate = calculateCompletionRate(habitId)
                    Log.d("HabitViewModel", "Updated streak: $updatedStreak, updated completion rate: $updatedCompletionRate")
                    val updatedHabit =
                        habit.copy(streak = updatedStreak, completion_rate = updatedCompletionRate)
                    repository.updateHabit(updatedHabit, onSuccess, onFailure)
                }
            }
        }, onFailure)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun calculateStreak(habitId: Int): Int {
        val logs = habitLogs.value?.filter { it.habit_id == habitId && it.status == 1 } ?: return 0
        val sortedLogs = logs.sortedBy { LocalDate.parse(it.date) }
        var streak = 0
        var currentDate = LocalDate.now()
        for (log in sortedLogs.reversed()) {
            if (LocalDate.parse(log.date) == currentDate) {
                streak++
                currentDate = currentDate.minusDays(1)
            } else {
                break
            }
        }
        return streak
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun calculateCompletionRate(habitId: Int): Double {
        val habit = habits.value?.find { it.habit_id == habitId } ?: return 0.0
        val startDate = LocalDate.parse(habit.date_created)
        val totalDays = ChronoUnit.DAYS.between(startDate, LocalDate.now()).toInt() + 1
        val totalWeeks = (totalDays / 7.0).coerceAtLeast(1.0)
        val expectedCompletions = totalWeeks * habit.frequency
        val completedLogs = habitLogs.value?.count { it.habit_id == habitId && it.status == 1 } ?: 0
        return if (expectedCompletions > 0) {
            (completedLogs.toDouble() / expectedCompletions) * 100
        } else {
            0.0
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getHabitLogByDate(date: LocalDate, currentUserUid: String?): List<Quintuple<String, String, Int, String, Int>> {
        val habitList = habitLogs.value?.filter { LocalDate.parse(it.date) == date }
            ?.mapNotNull { log ->
                val habit = habits.value?.find { it.habit_id == log.habit_id && it.uid == currentUserUid }
                habit?.let {
                    Quintuple(it.habit_name, it.time, log.status, it.category, log.habitLog_id)
                }
            } ?: emptyList()

        // Convert the habit list to a string and print it in the log
        Log.d("HabitList", habitList.toString())
        return habitList
    }

    data class Quintuple<A, B, C, D, E>(
        val first: A,
        val second: B,
        val third: C,
        val fourth: D,
        val fifth: E
    )

    @RequiresApi(Build.VERSION_CODES.O)
    fun createMissingHabitLogs(habitId: Int, dateCreated: String, habitLogs: List<HabitLog>, createHabitLog: (HabitLog) -> Unit) {
        val parsedDateCreated = LocalDate.parse(dateCreated)
        val today = LocalDate.now()
        val existingDates = habitLogs.map { it.date }.toSet()
        val existingLogIds = habitLogs.map { it.habitLog_id }.toMutableSet()
        Log.d("HabitViewModel", "Existing dates: $existingDates") // Log the existing dates
        var nextHabitLogId = (existingLogIds.maxOrNull() ?: 0) + 1

        var currentDate = parsedDateCreated
        while (!currentDate.isAfter(today)) {
            if (currentDate.toString() !in existingDates) {
                while (nextHabitLogId in existingLogIds) {
                    nextHabitLogId++
                }
                val newHabitLog = HabitLog(
                    habitLog_id = nextHabitLogId,
                    habit_id = habitId,
                    date = currentDate.toString(),
                    status = 2 // Initial value
                )
                createHabitLog(newHabitLog)
                existingLogIds.add(nextHabitLogId)
            }
            currentDate = currentDate.plusDays(1)
        }
    }

    fun insertHabitLog(habitLog: HabitLog, onSuccess: () -> Unit, onFailure: (String) -> Unit) {
        viewModelScope.launch {
            try {
                repository.insertHabitLog(habitLog, onSuccess, onFailure)
                onSuccess()
            } catch (e: Exception) {
                onFailure(e.message ?: "Unknown error")
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun saveHabit(
        habitName: String,
        uid: String,
        habitTime: String,
        selectedCategory: String,
        selectedFrequency: Int,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        repository.fetchHabitIds(
            onSuccess = { habitIds ->
                val newHabitId = (0..Int.MAX_VALUE).first { it !in habitIds }
                val habit = Habit(
                    habit_id = newHabitId,
                    habit_name = habitName,
                    uid = uid,
                    time = habitTime,
                    streak = 0,
                    completion_rate = 0.0,
                    date_from = "",
                    date_to = "",
                    date_created = LocalDate.now().toString(),
                    category = selectedCategory,
                    frequency = selectedFrequency,

                    )
                repository.saveHabit(habit, onSuccess, onFailure)
            },
            onFailure = onFailure
        )
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getHabitLogListForLatestWeek(habitLog: List<HabitLog>, habitId: Int): List<HabitLog> {
        val currentDate = LocalDate.now()
        val firstDayOfWeek = currentDate.with(ChronoField.DAY_OF_WEEK, 1)
        val lastDayOfWeek = firstDayOfWeek.plusDays(6)

        val filteredLogs = habitLog.filter { it.habit_id == habitId && LocalDate.parse(it.date) in firstDayOfWeek..lastDayOfWeek }
        val logMap = filteredLogs.associateBy { LocalDate.parse(it.date) }

        return (0..6).map { dayOffset ->
            val date = firstDayOfWeek.plusDays(dayOffset.toLong())
            logMap[date] ?: HabitLog(
                habitLog_id = -1, // Assuming -1 represents a placeholder ID
                habit_id = habitId,
                date = date.toString(),
                status = -1
            )
        }
    }

}