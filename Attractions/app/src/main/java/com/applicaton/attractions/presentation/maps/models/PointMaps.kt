package com.applicaton.attractions.presentation.maps.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PointMaps(
    val lon: Double,
    val lat: Double
) : Parcelable
