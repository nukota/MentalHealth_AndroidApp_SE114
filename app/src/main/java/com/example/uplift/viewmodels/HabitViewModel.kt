package com.example.uplift.viewmodels

import android.os.Build
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