package com.example.uplift.data.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    val uid: String = "",
    val role: String = "",
    val display_name: String = "",
    val email: String = "",
    val photo_url: String = ""
) : Parcelable