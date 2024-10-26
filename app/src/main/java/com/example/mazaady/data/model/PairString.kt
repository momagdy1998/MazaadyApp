package com.example.mazaady.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PairString(
    val fistValue: String,
    val secondValue: String
) : Parcelable
