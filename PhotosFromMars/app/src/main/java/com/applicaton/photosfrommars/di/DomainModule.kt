package com.applicaton.photosfrommars.di

import com.applicaton.photosfrommars.data.PhotosPagingSource
import com.applicaton.photosfrommars.data.PhotosRepository
import com.applicaton.photosfrommars.domain.GetPhotoListUseCase
import com.applicaton.photosfrommars.domain.PhotosPagingSourceInterface
import dagger.Module
import dagger.Provides

@Module
class DomainModule {

    @Provides
    fun providePhotosPagingSourceInterface(
        photosPagingSource: PhotosPagingSource
    ) : PhotosPagingSourceInterface {
        return photosPagingSource
    }

    @Provides
    fun provideGetPhotoListUseCase(
        photosPagingSourceInterface: PhotosPagingSourceInterface
    ): GetPhotoListUseCase {
        return GetPhotoListUseCase(photosPagingSourceInterface)
    }

}