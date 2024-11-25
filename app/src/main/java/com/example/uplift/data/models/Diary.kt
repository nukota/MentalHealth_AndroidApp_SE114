package com.example.uplift.data.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.text.DateFormat
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "diary")
data class Diary (
    @PrimaryKey(autoGenerate = true)
    val diary_id: Int = 0,
    val title: String,
    val content: String,
    val date_created: Long ,
    val date_modified: Long ,

) : Parcelable