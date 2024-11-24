package com.example.uplift.data.models

import android.os.Parcelable
import androidx.room.Entity
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "specialists",primaryKeys = ["specialist_id"])
data class Specialists(
    val specialist_id: Int,
    val full_name: String,
    val age: Int,
    val profession: String,
    val years_of_experience: Int,
    val location: String,
    val email: String,
    val rating: Double,
    val review_count: Int,
    val avartar: String,
    val specializations: List<String>,
    val educations: List<String>,
    val certifications: List<String>
):Parcelable
