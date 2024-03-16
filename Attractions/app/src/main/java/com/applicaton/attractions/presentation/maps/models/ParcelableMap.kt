package com.applicaton.attractions.presentation.maps.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ParcelableMap(
    val map: MutableMap<FeatureMaps,FeatureInfoMaps>
) : Parcelable
