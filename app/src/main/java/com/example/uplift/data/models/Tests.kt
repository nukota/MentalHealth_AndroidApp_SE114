package com.example.uplift.data.models;

import android.os.Parcelable
import androidx.room.Entity
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "tests", primaryKeys = ["test_id"])
data class Tests (
    val test_id: Int,
    val test_name: String,
    val test_purpose: String,
    val question_count: Int,
    val duration_minutes: Int,
    val icon_url:String,
    val explore_test_id :Int
) : Parcelable