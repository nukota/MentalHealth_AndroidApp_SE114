package com.example.uplift.data.models

import android.os.Parcelable
import androidx.room.Entity
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity( tableName = "answers",
    primaryKeys = ["answer_order", "test_id","question_id"])
data class Answer(
    val answer_order: Int,
    val test_id: Int,
    val question_id: Int,
    val answer_text: String,
    val answer_value: Double
) : Parcelable