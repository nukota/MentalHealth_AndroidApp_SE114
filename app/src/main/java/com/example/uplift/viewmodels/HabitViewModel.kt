package com.example.uplift.viewmodels

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.uplift.data.models.Habit
import com.example.uplift.data.models.HabitLog
import com.example.uplift.data.repository.HabitRepository
import java.time.LocalDate
import java.time.temporal.ChronoField

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

    fun updateHabitLogStatus(habitLogId: Int, newStatus: Int, onSuccess: (String) -> Unit, onFailure: (String) -> Unit) {
        repository.updateHabitLogStatus(habitLogId, newStatus, onSuccess, onFailure)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getHabitLogByDate(date: LocalDate): List<Quintuple<String, String, Int, String, Int>> {
        val exampleDate = LocalDate.of(2024, 11, 16)
        val habitList = habitLogs.value?.filter { LocalDate.parse(it.date) == exampleDate }
            ?.mapNotNull { log ->
                val habit = habits.value?.find { it.habit_id == log.habit_id }
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
                    category = selectedCategory,
                    frequency = selectedFrequency,

                )
                repository.saveHabit(habit, onSuccess, onFailure)
            },
            onFailure = onFailure
        )
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getStatusListForLatestWeek(habitLog: List<HabitLog>, habitId: Int): List<Any> {
        val currentDate = LocalDate.now()
        val firstDayOfWeek = currentDate.with(ChronoField.DAY_OF_WEEK, 1)
        val lastDayOfWeek = firstDayOfWeek.plusDays(6)

        val filteredLogs = habitLog.filter { it.habit_id == habitId && LocalDate.parse(it.date) in firstDayOfWeek..lastDayOfWeek }
        val statusMap = filteredLogs.associateBy { LocalDate.parse(it.date) }

        return (0..6).map { dayOffset ->
            val date = firstDayOfWeek.plusDays(dayOffset.toLong())
            statusMap[date]?.status ?: "not yet"
        }
    }
}