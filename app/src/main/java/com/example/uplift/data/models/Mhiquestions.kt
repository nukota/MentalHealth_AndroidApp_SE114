package com.example.uplift.data.models

import android.os.Parcelable
import androidx.room.Entity
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "mhiquestions")
data class Mhiquestions(
    val question_id: Int,
    val test_id: Int,
    val question_text: String,
    val question_order: Int
):Parcelable
