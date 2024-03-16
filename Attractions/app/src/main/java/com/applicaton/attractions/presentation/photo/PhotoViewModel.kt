package com.applicaton.attractions.presentation.photo

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.applicaton.attractions.domain.databaseUseCases.InsertPhotoUseCase
import com.applicaton.attractions.presentation.gallery.models.PhotoGallery
import com.applicaton.attractions.presentation.gallery.models.PhotoMapper
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PhotoViewModel(
    private val insertPhotoUseCase: InsertPhotoUseCase,
    private val photoMapper: PhotoMapper
) : ViewModel() {

    private val _state = MutableStateFlow<PhotoState>(PhotoState.Success)
    val state = _state.asStateFlow()

    override fun onCleared() {
        super.onCleared()
        Log.d("Tag", "onCleared PhotoViewModel")
    }

    fun addPhoto(uri: String) {
        val photoGallery = PhotoGallery(uri = uri)
        viewModelScope.launch {
            _state.value = PhotoState.Loading
            delay(800)
            insertPhotoUseCase.execute(
                photoMapper.mapToDbo(photoGallery)
            )
            _state.value = PhotoState.Success
        }
    }
}