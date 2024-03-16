package com.applicaton.attractions.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [PhotoDbo::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun photoDao() : PhotoDao
}