package com.applicaton.attractions.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface PhotoDao {
    @Query("SELECT * FROM photodbo")
    fun getAll(): Flow<List<PhotoDbo>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(photoDbo: PhotoDbo)

    @Delete
    suspend fun delete(photoDbo: PhotoDbo)
}