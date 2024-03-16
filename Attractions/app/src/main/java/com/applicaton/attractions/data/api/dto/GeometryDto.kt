package com.applicaton.attractions.data.api.dto

import com.applicaton.attractions.entity.Geometry
import com.squareup.moshi.Json

data class GeometryDto(
    @Json(name = "type")
    override val type: String,
    @Json(name = "coordinates")
    override val coordinates: List<Double>
) : Geometry