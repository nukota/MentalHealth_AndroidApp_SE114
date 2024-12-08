package com.example.uplift.data.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Diary(
    val diary_id: Int = 0,
    val uid: String = "",
    val title: String = "",
    val content: String = "",
    val date_created: String = "",
    val date_modified: String = "",
) : Parcelable