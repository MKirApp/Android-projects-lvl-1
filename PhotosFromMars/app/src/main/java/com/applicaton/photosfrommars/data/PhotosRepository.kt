package com.applicaton.photosfrommars.data

import com.applicaton.photosfrommars.entity.Photo
import kotlinx.coroutines.delay

class PhotosRepository(
    private val photosDataSource: PhotosDataSource
) {
    suspend fun getPhotoList(page: Int): List<Photo<*,*>> {
        delay(2000)
        return photosDataSource.getPhotosFromMars(page).photos
    }
}