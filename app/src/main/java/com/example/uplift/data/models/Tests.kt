package com.example.uplift.data.models;

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Tests (
    val test_id: Int = 0,
    val test_name: String = "",
    val test_purpose: String = "",
    val question_count: Int = 0,
    val duration_minutes: Int = 0,
    val test_link:String = "",
    val explore_test_id :Int = 0
) : Parcelable