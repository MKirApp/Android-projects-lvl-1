package com.applicaton.attractions.di

import android.content.Context
import androidx.room.Room
import com.applicaton.attractions.data.api.FeaturesDataSource
import com.applicaton.attractions.data.api.FeaturesRepository
import com.applicaton.attractions.data.api.retrofit
import com.applicaton.attractions.data.database.AppDatabase
import com.applicaton.attractions.data.database.DatabaseRepository
import com.applicaton.attractions.data.database.PhotoDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule {

    @Provides
    @Singleton
    fun provideAppDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "database"
        ).build()
    }

    @Provides
    fun providePhotoDao(database: AppDatabase): PhotoDao {
        return database.photoDao()
    }

    @Provides
    fun provideDatabaseRepository(photoDao: PhotoDao): DatabaseRepository {
        return DatabaseRepository(photoDao)
    }

    @Provides
    fun provideFeaturesDataSource(): FeaturesDataSource {
        return retrofit
    }

    @Provides
    fun provideFeaturesRepository(featuresDataSource: FeaturesDataSource): FeaturesRepository {
        return FeaturesRepository(featuresDataSource)
    }
}