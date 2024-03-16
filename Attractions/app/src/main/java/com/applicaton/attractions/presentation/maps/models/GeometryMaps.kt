package com.applicaton.attractions.presentation.maps.models

import android.os.Parcelable
import com.applicaton.attractions.entity.Geometry
import kotlinx.parcelize.Parcelize

@Parcelize
data class GeometryMaps(
    val type: String,
    val coordinates: List<Double>
) : Parcelable