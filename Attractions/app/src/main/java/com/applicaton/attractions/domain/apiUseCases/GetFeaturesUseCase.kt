package com.applicaton.attractions.domain.apiUseCases

import com.applicaton.attractions.entity.Feature
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetFeaturesUseCase(
    private val featureListRepository: FeatureListRepository
) {
    suspend fun execute(lon: Double, lat: Double): List<Feature<*, *>> = withContext(Dispatchers.IO) {
        featureListRepository.getFeatureList(lon, lat)
    }
}