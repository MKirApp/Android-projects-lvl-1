package com.applicaton.photosfrommars.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.applicaton.photosfrommars.domain.GetPhotoListUseCase
import com.applicaton.photosfrommars.presentation.mappers.PhotoMapper

class MainViewModelFactory(
    private val getPhotoListUseCase: GetPhotoListUseCase,
    private val photoMapper: PhotoMapper
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(getPhotoListUseCase, photoMapper) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}