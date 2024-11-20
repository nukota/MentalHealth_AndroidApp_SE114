package com.example.uplift.data.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.text.DateFormat
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "habit_table")
data class Diary (
    @PrimaryKey(autoGenerate = true)
    val id: String = "",
    val title: String = "",
    val content: String = "",
    val date: String = ""
) : Parcelable