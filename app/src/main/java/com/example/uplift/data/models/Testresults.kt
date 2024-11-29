package com.example.uplift.data.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Testresults(
    val result_id: Int = 0,
    val test_id: Int = 0,
    val result_description: String = "",
    val result_recommendation: String = ""
):Parcelable
