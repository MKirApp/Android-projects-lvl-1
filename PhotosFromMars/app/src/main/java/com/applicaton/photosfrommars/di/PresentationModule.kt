package com.applicaton.photosfrommars.di

import com.applicaton.photosfrommars.domain.GetPhotoListUseCase
import com.applicaton.photosfrommars.presentation.MainViewModelFactory
import com.applicaton.photosfrommars.presentation.mappers.PhotoMapper
import dagger.Module
import dagger.Provides

@Module
class PresentationModule {

    @Provides
    fun provideMainViewModelFactory(getPhotoListUseCase: GetPhotoListUseCase, photoMapper: PhotoMapper): MainViewModelFactory {
        return MainViewModelFactory(getPhotoListUseCase, photoMapper)
    }

}