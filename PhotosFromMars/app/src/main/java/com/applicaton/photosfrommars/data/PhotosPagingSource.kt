package com.applicaton.photosfrommars.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.applicaton.photosfrommars.domain.PhotosPagingSourceInterface
import com.applicaton.photosfrommars.entity.Photo
import kotlinx.coroutines.flow.Flow

class PhotosPagingSource(
    private val photosRepository: PhotosRepository
) : PagingSource<Int, Photo<*, *>>(), PhotosPagingSourceInterface {
    override fun getRefreshKey(state: PagingState<Int, Photo<*, *>>): Int = FIRST_PAGE

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Photo<*, *>> {
        val page = params.key ?: 0
        return kotlin.runCatching {
            photosRepository.getPhotoList(page)
        }.fold(
            onSuccess = {
                LoadResult.Page(
                    data = it,
                    prevKey = null,
                    nextKey = if (it.isEmpty()) null else page + 1,
                )
            },
            onFailure = {
                LoadResult.Error(it)
            }
        )
    }

    override fun getPagedPhotos(): Flow<PagingData<Photo<*, *>>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = { PhotosPagingSource(photosRepository) }
        ).flow
    }

    private companion object {
        private const val FIRST_PAGE = 0
    }
}

