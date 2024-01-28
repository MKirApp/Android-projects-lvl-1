package com.applicaton.photosfrommars.domain

import androidx.paging.PagingData
import androidx.paging.PagingSource
import com.applicaton.photosfrommars.entity.Photo
import kotlinx.coroutines.flow.Flow

class GetPhotoListUseCase(
    private val photosPagingSource: PhotosPagingSourceInterface
) {
    fun execute(): Flow<PagingData<Photo<*, *>>> {
        return photosPagingSource.getPagedPhotos()
    }
}