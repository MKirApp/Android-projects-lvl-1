package com.applicaton.attractions.data.api.dto

import com.applicaton.attractions.entity.Point
import com.squareup.moshi.Json

data class PointDto(
    @Json(name = "lon")
    override val lon: Double,
    @Json(name = "lat")
    override val lat: Double
) : Point