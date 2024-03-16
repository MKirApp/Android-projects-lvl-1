package com.applicaton.attractions.data.api

import com.applicaton.attractions.domain.apiUseCases.FeatureListRepository
import com.applicaton.attractions.entity.Feature
import com.applicaton.attractions.entity.FeatureInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FeaturesRepository(
    private val featuresDataSource: FeaturesDataSource
) : FeatureListRepository {
    override suspend fun getFeatureList(lon: Double, lat: Double): List<Feature<*, *>> = withContext(Dispatchers.IO) {
        featuresDataSource.getPlacesList(lon = lon, lat = lat).features
    }

    override suspend fun getFeatureInfo(xid: String): FeatureInfo<*> = withContext(Dispatchers.IO) {
        featuresDataSource.getInfoPlace(xid = xid)
    }

}