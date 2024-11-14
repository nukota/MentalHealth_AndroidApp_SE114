package com.example.uplift.data.models

import android.os.Parcelable
import androidx.room.Entity
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "testresults")
data class Testresults(
    val result_id: Int,
    val test_id: Int,
    val result_description: String,
    val result_recommendation: String
):Parcelable
