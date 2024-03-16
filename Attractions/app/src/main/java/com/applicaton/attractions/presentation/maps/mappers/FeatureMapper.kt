package com.applicaton.attractions.presentation.maps.mappers

import com.applicaton.attractions.data.api.dto.FeatureDto
import com.applicaton.attractions.presentation.maps.models.FeatureMaps

class FeatureMapper(
    private val geometryMapper: GeometryMapper,
    private val propertiesMapper: PropertiesMapper
) {
   fun mapToMaps(featureDto: FeatureDto): FeatureMaps {
        return FeatureMaps(
            type = featureDto.type,
            id = featureDto.id,
            geometry = geometryMapper.mapToMaps(featureDto.geometry),
            properties = propertiesMapper.mapToMaps(featureDto.properties)
        )
    }
}