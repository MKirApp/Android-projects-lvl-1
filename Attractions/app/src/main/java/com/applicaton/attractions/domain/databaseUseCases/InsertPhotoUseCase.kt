package com.applicaton.attractions.domain.databaseUseCases

import android.util.Log
import com.applicaton.attractions.entity.Photo

class InsertPhotoUseCase(
    private val photoRepository: PhotoRepository
) {
    suspend fun execute(photo: Photo) {
        Log.d("Tag","юзкейс")
        photoRepository.insertPhoto(photo)
    }
}