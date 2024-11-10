package com.example.uplift.data.models;

import android.os.Parcelable
import androidx.room.Entity
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "gadanswer")
data class Gadanswers (
    val answer_id: Int,
    val question_id: Int,
    val answer_text: String,
    val answer_value: Int,
    val answer_order: Int
) : Parcelable