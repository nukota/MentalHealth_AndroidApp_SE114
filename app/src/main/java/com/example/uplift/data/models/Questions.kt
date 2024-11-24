package com.example.uplift.data.models;

import android.os.Parcelable
import androidx.room.Entity
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "questions",  primaryKeys = ["test_id", "question_order"])
data class Questions (
    val test_id: Int,
    val question_text: String,
    val question_order: Int
) : Parcelable