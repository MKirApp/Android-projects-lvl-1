package com.applicaton.attractions.presentation.photo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.applicaton.attractions.domain.databaseUseCases.InsertPhotoUseCase
import com.applicaton.attractions.presentation.gallery.models.PhotoMapper

class PhotoViewModelFactory(
    private val insertPhotoUseCase: InsertPhotoUseCase,
    private val photoMapper: PhotoMapper
) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PhotoViewModel::class.java))
        {
            return PhotoViewModel(insertPhotoUseCase, photoMapper) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}