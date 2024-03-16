package com.applicaton.attractions.presentation.gallery

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.applicaton.attractions.domain.databaseUseCases.DeletePhotoUseCase
import com.applicaton.attractions.domain.databaseUseCases.GetAllPhotosUseCase
import com.applicaton.attractions.presentation.gallery.models.PhotoMapper

class GalleryViewModelFactory(
    private val getAllPhotosUseCase: GetAllPhotosUseCase,
    private val deletePhotoUseCase: DeletePhotoUseCase,
    private val photoMapper: PhotoMapper
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GalleryViewModel::class.java))  {
            return GalleryViewModel(getAllPhotosUseCase, deletePhotoUseCase, photoMapper) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}