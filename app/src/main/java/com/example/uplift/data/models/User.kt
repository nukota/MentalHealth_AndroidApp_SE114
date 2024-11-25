package com.example.uplift.data.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.text.DateFormat
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "user_table")
data class User (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val date_created: Long ,
    val username: String,
    val password: String
) : Parcelable