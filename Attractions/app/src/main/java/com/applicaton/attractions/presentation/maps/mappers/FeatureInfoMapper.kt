package com.applicaton.attractions.presentation.maps.mappers

import com.applicaton.attractions.data.api.dto.FeatureInfoDto
import com.applicaton.attractions.entity.FeatureInfo
import com.applicaton.attractions.presentation.maps.models.FeatureInfoMaps

class FeatureInfoMapper(
   private val pointMapper: PointMapper
) {
    fun mapToMaps(featureInfo: FeatureInfo<*>) : FeatureInfoMaps {
        return FeatureInfoMaps(
            xid = featureInfo.xid,
            rate = featureInfo.rate,
            kinds = featureInfo.kinds,
            otm = featureInfo.otm,
            point = pointMapper.mapToMaps(featureInfo.point)
        )
    }
}