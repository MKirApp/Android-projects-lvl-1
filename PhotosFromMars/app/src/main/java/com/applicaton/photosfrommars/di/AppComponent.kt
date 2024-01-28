package com.applicaton.photosfrommars.di

import com.applicaton.photosfrommars.presentation.MainViewModelFactory
import dagger.Component

@Component(
    modules = [
        DataModule::class,
        DomainModule::class,
        PresentationModule::class,
        MappersModule::class
    ]
)
interface AppComponent {
    fun mainViewModelFactory(): MainViewModelFactory
}