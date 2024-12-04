package com.example.uplift.data.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Habit(
    val habit_id: Int = 0,
    val habit_name: String = "",
    val uid: String = "",
    val date_from: String = "",
    val date_to: String = "",
    val date_created: String = "",
    val completion_rate: Double = 0.0,
    val frequency: Int = 0,
    val time: String = "",
    val streak: Int = 0,
    val category: String = "",
) : Parcelable