package com.example.uplift.data.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Answer(
    val answer_order: Int = 0,
    val question_id: Int = 0,
    val answer_text: String = "",
    val answer_value: Double = 0.0,
    val test_id: Int = 0
) : Parcelable