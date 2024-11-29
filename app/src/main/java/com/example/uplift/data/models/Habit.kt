package com.example.uplift.data.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Habit (
    val id: Int = 0,
    val name: String = "",
    val date_created: String = "",
    val description: String = "",
) : Parcelable