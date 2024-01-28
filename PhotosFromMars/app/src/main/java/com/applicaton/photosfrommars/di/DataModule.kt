package com.applicaton.photosfrommars.di

import com.applicaton.photosfrommars.data.PhotosDataSource
import com.applicaton.photosfrommars.data.PhotosPagingSource
import com.applicaton.photosfrommars.data.PhotosRepository
import com.applicaton.photosfrommars.data.retrofit
import com.applicaton.photosfrommars.domain.PhotosPagingSourceInterface
import dagger.Module
import dagger.Provides

@Module
class DataModule {

    @Provides
    fun providePhotosDataSource(): PhotosDataSource {
        return retrofit
    }

    @Provides
    fun providePhotosRepository(
        photosDataSource: PhotosDataSource
    ): PhotosRepository {
        return PhotosRepository(photosDataSource)
    }

    @Provides
    fun providePhotosPagingSource(
        photosRepository: PhotosRepository
    ): PhotosPagingSource {
        return PhotosPagingSource(photosRepository)
    }

}