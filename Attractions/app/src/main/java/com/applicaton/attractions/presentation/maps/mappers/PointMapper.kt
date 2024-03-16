package com.applicaton.attractions.presentation.maps.mappers

import com.applicaton.attractions.entity.Point
import com.applicaton.attractions.presentation.maps.models.PointMaps

class PointMapper {
    fun mapToMaps(point: Point): PointMaps {
        return PointMaps(
            lon = point.lon,
            lat = point.lat
        )
    }
}