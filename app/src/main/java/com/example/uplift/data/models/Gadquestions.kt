package com.example.uplift.data.models;

import android.os.Parcelable
import androidx.room.Entity
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "gadquestions")
data class Gadquestions (
    val question_id: Int,
    val test_id: String,
    val question_text: Int,
    val question_order: Int
) : Parcelable