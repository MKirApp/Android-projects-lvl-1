package com.applicaton.attractions.data.api.dto

import com.applicaton.attractions.entity.Properties
import com.squareup.moshi.Json

data class PropertiesDto(
    @Json(name = "xid")
    override val xid: String,
    @Json(name = "name")
    override val name: String,
    @Json(name = "dist")
    override val dist: Double,
    @Json(name = "rate")
    override val rate: Int,
    @Json(name = "osm")
    override val osm: String,
    @Json(name = "kinds")
    override val kinds: String
) : Properties
