package com.applicaton.attractions.presentation.maps.models

import android.os.Parcelable
import com.applicaton.attractions.entity.Feature
import kotlinx.parcelize.Parcelize

@Parcelize
data class FeatureMaps(
    val type: String,
    val id: String,
    val geometry: GeometryMaps,
    val properties: PropertiesMaps
) : Parcelable