package com.example.uplift.data.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.text.DateFormat
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "habit")
data class Habit (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val date_created: String,
    val description: String,
) : Parcelable