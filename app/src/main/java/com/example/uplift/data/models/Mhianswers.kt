package com.example.uplift.data.models
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Mhianswers(
    val answer_id: Int = 0,
    val question_id: Int = 0,
    val answer_text: String = "",
    val answer_value: Int = 0,
    val answer_order: Int = 0
):Parcelable
