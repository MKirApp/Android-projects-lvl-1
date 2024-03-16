package com.applicaton.attractions.presentation.maps

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.applicaton.attractions.domain.apiUseCases.GetFeatureInfoUseCase
import com.applicaton.attractions.domain.apiUseCases.GetFeaturesUseCase
import com.applicaton.attractions.presentation.maps.mappers.FeatureInfoMapper
import com.applicaton.attractions.presentation.maps.mappers.FeatureListMapper

class MapsViewModelFactory(
    private val getFeaturesUseCase: GetFeaturesUseCase,
    private val getFeatureInfoUseCase: GetFeatureInfoUseCase,
    private val featureListMapper: FeatureListMapper,
    private val featureInfoMapper: FeatureInfoMapper
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MapsViewModel::class.java)) {
            return MapsViewModel(getFeaturesUseCase, getFeatureInfoUseCase, featureListMapper, featureInfoMapper) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}