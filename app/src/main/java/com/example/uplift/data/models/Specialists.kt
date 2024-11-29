package com.example.uplift.data.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Specialists(
    val specialist_id: Int = 0,
    val full_name: String = "",
    val age: Int = 0,
    val profession: String = "",
    val years_of_experience: Int = 0,
    val location: String = "",
    val rating: Double = 0.0,
    val review_count: Int = 0,
    val avartar: Int = 0,
    val explore_spec_id: Int = 0
):Parcelable
