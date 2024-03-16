package com.applicaton.attractions.data.api.dto

import com.applicaton.attractions.entity.FeaturesList
import com.squareup.moshi.Json

data class FeaturesListDto(
    @Json(name = "type")
    override val type: String,
    @Json(name = "features")
    override val features: List<FeatureDto>
) : FeaturesList<FeatureDto>
