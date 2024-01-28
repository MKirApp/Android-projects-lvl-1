package com.applicaton.photosfrommars.di

import com.applicaton.photosfrommars.presentation.mappers.CameraMapper
import com.applicaton.photosfrommars.presentation.mappers.PhotoMapper
import com.applicaton.photosfrommars.presentation.mappers.RoverMapper
import dagger.Module
import dagger.Provides

@Module
class MappersModule {

    @Provides
    fun provideCameraMapper(): CameraMapper {
        return CameraMapper()
    }

    @Provides
    fun provideRoverMapper(): RoverMapper {
        return RoverMapper()
    }

    @Provides
    fun providePhotoMapper(cameraMapper: CameraMapper, roverMapper: RoverMapper): PhotoMapper {
        return PhotoMapper(cameraMapper, roverMapper)
    }
}