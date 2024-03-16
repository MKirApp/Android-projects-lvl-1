package com.applicaton.attractions.data.api.dto

import com.applicaton.attractions.entity.FeatureInfo
import com.squareup.moshi.Json

data class FeatureInfoDto(
    @Json(name = "xid")
    override val xid: String,
    @Json(name = "rate")
    override val rate: String,
    @Json(name = "kinds")
    override val kinds: String,
    @Json(name = "otm")
    override val otm: String,
    @Json(name = "point")
    override val point: PointDto
) : FeatureInfo<PointDto>
