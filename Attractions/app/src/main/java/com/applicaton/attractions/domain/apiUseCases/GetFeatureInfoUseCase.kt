package com.applicaton.attractions.domain.apiUseCases

import com.applicaton.attractions.entity.FeatureInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetFeatureInfoUseCase(
    private val featureListRepository: FeatureListRepository
) {
    suspend fun execute(xid: String) : FeatureInfo<*> = withContext(Dispatchers.IO) {
        featureListRepository.getFeatureInfo(xid)
    }
}