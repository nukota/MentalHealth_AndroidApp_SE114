package com.example.uplift.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Diary(
    var diary_id: String = "",  // You can generate a unique ID later on Firebase
    val uid: String = "",   // User ID for the diary
    val title: String = "",
    val content: String = "",
    val date_created: Long = System.currentTimeMillis(),
    val date_modified: Long = System.currentTimeMillis()
) : Parcelable
