package com.example.uplift.data.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TestResult(
    val result_id: Int = 0,
    val test_id: Int = 0,
    val score_min: Int = 0,
    val score_max: Int = 0,
    val picture_url: String = "",
    val result_description: String = "",
    val result_recommendation: String = ""
) : Parcelable
