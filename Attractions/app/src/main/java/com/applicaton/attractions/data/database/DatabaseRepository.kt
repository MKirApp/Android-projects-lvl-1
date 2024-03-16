package com.applicaton.attractions.data.database

import android.util.Log
import com.applicaton.attractions.domain.databaseUseCases.PhotoRepository
import com.applicaton.attractions.entity.Photo
import kotlinx.coroutines.flow.Flow

class DatabaseRepository(private val photoDao: PhotoDao) : PhotoRepository {

    override fun getAllPhotos(): Flow<List<Photo>> {
        Log.d("Tag","новое фото в БД")
        return photoDao.getAll()
    }

    override suspend fun insertPhoto(photo: Photo) {
        Log.d("Tag","фото добавилось в БД")
        photoDao.insert(photo as PhotoDbo)
    }

    override suspend fun deletePhoto(photo: Photo) {
        photoDao.delete(photo as PhotoDbo)
    }

}