package com.applicaton.attractions.domain.databaseUseCases

import com.applicaton.attractions.entity.Photo
import kotlinx.coroutines.flow.Flow

class GetAllPhotosUseCase(
    private val photoRepository: PhotoRepository
) {
    fun execute() : Flow<List<Photo>> {
        return photoRepository.getAllPhotos()
    }
}