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
    val avatar: String = "",
    val specializations: List<String>,
    val educations: List<String>,
    val certifications: List<String>
):Parcelable
