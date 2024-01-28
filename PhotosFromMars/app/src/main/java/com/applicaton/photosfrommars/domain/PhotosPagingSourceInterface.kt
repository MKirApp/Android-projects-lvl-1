package com.applicaton.photosfrommars.domain

import androidx.paging.PagingData
import com.applicaton.photosfrommars.entity.Photo
import kotlinx.coroutines.flow.Flow

interface PhotosPagingSourceInterface {
    fun getPagedPhotos(): Flow<PagingData<Photo<*, *>>>
}