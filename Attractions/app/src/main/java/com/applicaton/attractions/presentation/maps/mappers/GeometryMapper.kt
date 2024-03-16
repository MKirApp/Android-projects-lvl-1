package com.applicaton.attractions.presentation.maps.mappers

import com.applicaton.attractions.data.api.dto.GeometryDto
import com.applicaton.attractions.presentation.maps.models.GeometryMaps

class GeometryMapper {
    fun mapToMaps(geometryDto: GeometryDto): GeometryMaps {
        return GeometryMaps(
            type = geometryDto.type,
            coordinates = geometryDto.coordinates
        )
    }
}