package com.applicaton.attractions.presentation.photo

sealed class PhotoState {
    object Loading: PhotoState()
    object Success: PhotoState()
}