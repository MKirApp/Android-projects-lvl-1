package com.applicaton.attractions.di

import com.applicaton.attractions.domain.apiUseCases.GetFeatureInfoUseCase
import com.applicaton.attractions.domain.databaseUseCases.DeletePhotoUseCase
import com.applicaton.attractions.domain.databaseUseCases.GetAllPhotosUseCase
import com.applicaton.attractions.domain.apiUseCases.GetFeaturesUseCase
import com.applicaton.attractions.domain.databaseUseCases.InsertPhotoUseCase
import com.applicaton.attractions.presentation.gallery.GalleryViewModelFactory
import com.applicaton.attractions.presentation.gallery.models.PhotoMapper
import com.applicaton.attractions.presentation.maps.MapsViewModelFactory
import com.applicaton.attractions.presentation.maps.mappers.FeatureInfoMapper
import com.applicaton.attractions.presentation.maps.mappers.FeatureListMapper
import com.applicaton.attractions.presentation.maps.mappers.FeatureMapper
import com.applicaton.attractions.presentation.maps.mappers.GeometryMapper
import com.applicaton.attractions.presentation.maps.mappers.PointMapper
import com.applicaton.attractions.presentation.maps.mappers.PropertiesMapper
import com.applicaton.attractions.presentation.photo.PhotoViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class PresentationModule {

    @Provides
    fun providePhotoMapper(): PhotoMapper {
        return PhotoMapper()
    }

    @Provides
    fun provideGalleryViewModelFactory(
        getAllPhotosUseCase: GetAllPhotosUseCase,
        deletePhotoUseCase: DeletePhotoUseCase,
        photoMapper: PhotoMapper
    ): GalleryViewModelFactory {
        return GalleryViewModelFactory(getAllPhotosUseCase, deletePhotoUseCase, photoMapper)
    }

    @Provides
    fun providePhotoViewModelFactory(
        insertPhotoUseCase: InsertPhotoUseCase,
        photoMapper: PhotoMapper
    ) : PhotoViewModelFactory {
        return PhotoViewModelFactory(insertPhotoUseCase, photoMapper)
    }

    @Provides
    fun providePropertiesMapper(): PropertiesMapper {
        return PropertiesMapper()
    }

    @Provides
    fun provideGeometryMapper(): GeometryMapper {
        return GeometryMapper()
    }

    @Provides
    fun provideFeatureMapper(geometryMapper: GeometryMapper, propertiesMapper: PropertiesMapper): FeatureMapper {
        return FeatureMapper(geometryMapper, propertiesMapper)
    }

    @Provides
    fun provideFeaturesListMapper(featureMapper: FeatureMapper): FeatureListMapper {
        return FeatureListMapper(featureMapper)
    }

    @Provides
    fun providePointMapper(): PointMapper {
        return PointMapper()
    }

    @Provides
    fun provideFeatureInfoMapper(pointMapper: PointMapper): FeatureInfoMapper {
        return FeatureInfoMapper(pointMapper)
    }

    @Provides
    fun provideMapsViewModelFactory(
        getFeaturesUseCase: GetFeaturesUseCase,
        getFeatureInfoUseCase: GetFeatureInfoUseCase,
        featureListMapper: FeatureListMapper,
        featureInfoMapper: FeatureInfoMapper
    ): MapsViewModelFactory {
        return MapsViewModelFactory(getFeaturesUseCase, getFeatureInfoUseCase, featureListMapper, featureInfoMapper)
    }
}