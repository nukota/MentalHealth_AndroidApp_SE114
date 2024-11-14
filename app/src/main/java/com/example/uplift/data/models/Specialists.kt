package com.example.uplift.data.models

import android.os.Parcelable
import androidx.room.Entity
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "specialists")
data class Specialists(
    val specialist_id: Int,
    val full_name: String,
    val age: Int,
    val profession: String,
    val years_of_experience: Int,
    val location: String,
    val rating: Double,
    val review_count: Int,
    val avartar: Int,
    val explore_spec_id: Int
):Parcelable
