package com.example.uplift.data.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Question(
    val test_id: Int = 0,
    val question_text: String = "",
    val question_order: Int = 0
) : Parcelable