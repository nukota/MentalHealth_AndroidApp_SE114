package com.example.uplift.data.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.text.DateFormat
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "users")
data class User (
    @PrimaryKey(autoGenerate = true)
    val id: String,
    val date_created: DateFormat,
    val username: String,
    val password: String
) : Parcelable