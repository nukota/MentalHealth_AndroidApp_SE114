package com.example.uplift.data.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class HabitLog(
    val habit_id: Int = 0,
    val habitLog_id: Int = 0,
    val status: Int = 0,
    var date: String = "",
) : Parcelable