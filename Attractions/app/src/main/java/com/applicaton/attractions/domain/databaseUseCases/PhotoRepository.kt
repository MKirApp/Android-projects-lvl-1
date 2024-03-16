package com.applicaton.attractions.domain.databaseUseCases

import com.applicaton.attractions.entity.Photo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface PhotoRepository {

    fun getAllPhotos(): Flow<List<Photo>>

    suspend fun insertPhoto(photo: Photo)

    suspend fun deletePhoto(photo: Photo)
}