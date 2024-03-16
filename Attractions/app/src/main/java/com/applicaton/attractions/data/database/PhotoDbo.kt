package com.applicaton.attractions.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.applicaton.attractions.entity.Photo

@Entity
data class PhotoDbo(
    @PrimaryKey
    @ColumnInfo(name = "id")
    override val id: Int? = null,
    @ColumnInfo(name = "text")
    override val uri: String
) : Photo