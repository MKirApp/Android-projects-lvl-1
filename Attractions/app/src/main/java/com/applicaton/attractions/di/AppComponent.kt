package com.applicaton.attractions.di

import com.applicaton.attractions.presentation.gallery.GalleryViewModelFactory
import com.applicaton.attractions.presentation.maps.MapsViewModelFactory
import com.applicaton.attractions.presentation.photo.PhotoViewModelFactory
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        DataModule::class,
        DomainModule::class,
        PresentationModule::class
    ]
)
interface AppComponent {
    fun galleryViewModelFactory(): GalleryViewModelFactory
    fun photoViewModelFactory(): PhotoViewModelFactory
    fun mapsViewModelFactory(): MapsViewModelFactory
}