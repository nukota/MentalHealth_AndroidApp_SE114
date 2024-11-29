package com.example.uplift.data.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    val id: Int = 0,
    val date_created: String = "",
    val username: String = "",
    val password: String = ""
) : Parcelable