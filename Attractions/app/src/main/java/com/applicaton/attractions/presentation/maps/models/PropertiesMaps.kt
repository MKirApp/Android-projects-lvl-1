package com.applicaton.attractions.presentation.maps.models

import android.os.Parcelable
import com.applicaton.attractions.entity.Properties
import kotlinx.parcelize.Parcelize

@Parcelize
data class PropertiesMaps(
    val xid: String,
    val name: String,
    val dist: Double,
    val rate: Int,
    val osm: String?,
    val kinds: String
) : Parcelable