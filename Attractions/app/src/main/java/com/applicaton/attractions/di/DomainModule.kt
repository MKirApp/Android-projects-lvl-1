package com.applicaton.attractions.di

import com.applicaton.attractions.data.api.FeaturesRepository
import com.applicaton.attractions.data.database.DatabaseRepository
import com.applicaton.attractions.domain.databaseUseCases.DeletePhotoUseCase
import com.applicaton.attractions.domain.apiUseCases.FeatureListRepository
import com.applicaton.attractions.domain.apiUseCases.GetFeatureInfoUseCase
import com.applicaton.attractions.domain.databaseUseCases.GetAllPhotosUseCase
import com.applicaton.attractions.domain.apiUseCases.GetFeaturesUseCase
import com.applicaton.attractions.domain.databaseUseCases.InsertPhotoUseCase
import com.applicaton.attractions.domain.databaseUseCases.PhotoRepository
import dagger.Module
import dagger.Provides

@Module
class DomainModule {

    @Provides
    fun providePhotoRepository(databaseRepository: DatabaseRepository): PhotoRepository {
        return databaseRepository
    }

    @Provides
    fun provideGetAllPhotosUseCase(photoRepository: PhotoRepository): GetAllPhotosUseCase {
        return GetAllPhotosUseCase(photoRepository)
    }

    @Provides
    fun provideInsertPhotoUseCase(photoRepository: PhotoRepository): InsertPhotoUseCase {
        return InsertPhotoUseCase(photoRepository)
    }

    @Provides
    fun provideDeletePhotoUseCase(photoRepository: PhotoRepository): DeletePhotoUseCase {
        return DeletePhotoUseCase(photoRepository)
    }

    @Provides
    fun provideFeatureListRepository(featuresRepository: FeaturesRepository): FeatureListRepository {
        return featuresRepository
    }

    @Provides
    fun provideGetFeaturesUseCase(featureListRepository: FeatureListRepository): GetFeaturesUseCase {
        return GetFeaturesUseCase(featureListRepository)
    }

    @Provides
    fun provideGetFeatureInfoUseCase(featuresRepository: FeaturesRepository): GetFeatureInfoUseCase {
        return GetFeatureInfoUseCase(featuresRepository)
    }
}