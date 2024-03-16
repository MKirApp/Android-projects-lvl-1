package com.applicaton.attractions.presentation.maps.mappers

import com.applicaton.attractions.data.api.dto.FeatureDto
import com.applicaton.attractions.entity.Feature
import com.applicaton.attractions.presentation.maps.models.FeatureMaps

class FeatureListMapper(
    private val featureMapper: FeatureMapper
) {
    fun mapEntityToMaps(feature: List<Feature<*, *>>): List<FeatureMaps> {
        return feature.map { featureMapper.mapToMaps(it as FeatureDto) }
    }
}