package com.applicaton.attractions.domain.databaseUseCases

import com.applicaton.attractions.entity.Photo

class DeletePhotoUseCase(
    private val photoRepository: PhotoRepository
) {
    suspend fun execute(photo: Photo) {
        photoRepository.deletePhoto(photo)
    }
}