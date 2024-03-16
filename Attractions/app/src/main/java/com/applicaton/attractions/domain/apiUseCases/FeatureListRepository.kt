package com.applicaton.attractions.domain.apiUseCases

import com.applicaton.attractions.entity.Feature
import com.applicaton.attractions.entity.FeatureInfo

interface FeatureListRepository {
    suspend fun getFeatureList(lon: Double, lat: Double) : List<Feature<*,*>>
    suspend fun getFeatureInfo(xid: String) : FeatureInfo<*>
}