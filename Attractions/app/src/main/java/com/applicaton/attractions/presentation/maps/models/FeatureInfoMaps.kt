package com.applicaton.attractions.presentation.maps.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FeatureInfoMaps(
    val xid: String,
    val rate: String,
    val kinds: String,
    val otm: String,
    val point: PointMaps
) : Parcelable
