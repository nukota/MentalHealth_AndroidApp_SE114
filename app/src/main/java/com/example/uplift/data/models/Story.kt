package com.example.uplift.data.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class story(
    val story_id: Int = 0,
    val title: String = "",
    val description: String = "",
    val author: String = "",
    val cover: String = "",
    val content: String = ""
) : Parcelable
