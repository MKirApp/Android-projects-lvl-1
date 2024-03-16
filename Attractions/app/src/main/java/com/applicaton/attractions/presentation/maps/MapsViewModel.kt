package com.applicaton.attractions.presentation.maps

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.applicaton.attractions.domain.apiUseCases.GetFeatureInfoUseCase
import com.applicaton.attractions.domain.apiUseCases.GetFeaturesUseCase
import com.applicaton.attractions.presentation.maps.mappers.FeatureInfoMapper
import com.applicaton.attractions.presentation.maps.mappers.FeatureListMapper
import com.applicaton.attractions.presentation.maps.models.FeatureInfoMaps
import com.applicaton.attractions.presentation.maps.models.FeatureMaps
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MapsViewModel(
    private val getFeaturesUseCase: GetFeaturesUseCase,
    private val getFeatureInfoUseCase: GetFeatureInfoUseCase,
    private val featureListMapper: FeatureListMapper,
    private val featureInfoMapper: FeatureInfoMapper
) : ViewModel() {

    private val _state = MutableStateFlow<MapsState?>(null)
    val state = _state.asStateFlow()

    private val _features = MutableStateFlow<List<FeatureMaps>>(emptyList())
    val features = _features.asStateFlow()

    private val _featureInfo = MutableStateFlow<List<FeatureInfoMaps>>(emptyList())

    private val _combinedFeatureList = MutableStateFlow<Map<FeatureMaps,FeatureInfoMaps>>(emptyMap())
    val combinedFeatureList = _combinedFeatureList.asStateFlow()

    fun getFeatures(lon: Double, lat: Double) {
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                getFeaturesUseCase.execute(lon, lat)
            }.fold(
                onSuccess = { list ->
                    _state.value = MapsState.Success
                    _features.value = featureListMapper.mapEntityToMaps(list)
                    val featuresInfo = _features.value.map {
                        feature ->
                        async(Dispatchers.IO) {
                            getFeatureInfoUseCase.execute(feature.properties.xid)
                        }
                    }
                    val featureInfoList = featuresInfo.awaitAll()
                    val featureInfoMaps = featureInfoList.map {
                        featureInfoMapper.mapToMaps(it)
                    }
                    _featureInfo.value = featureInfoMaps
                    _combinedFeatureList.value = _features.value.zip(featureInfoMaps).toMap()
                },
                onFailure = {
                    _state.value = MapsState.Error
                }
            )
        }
    }
}