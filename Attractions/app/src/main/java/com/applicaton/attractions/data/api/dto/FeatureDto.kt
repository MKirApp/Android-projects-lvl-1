package com.applicaton.attractions.data.api.dto

import com.applicaton.attractions.entity.Feature
import com.applicaton.attractions.entity.Geometry
import com.applicaton.attractions.entity.Properties
import com.squareup.moshi.Json

data class FeatureDto(
    @Json(name = "type")
    override val type: String,
    @Json(name = "id")
    override val id: String,
    @Json(name = "geometry")
    override val geometry: GeometryDto,
    @Json(name = "properties")
    override val properties: PropertiesDto
) : Feature<PropertiesDto,GeometryDto>
