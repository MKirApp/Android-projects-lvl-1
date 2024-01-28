package com.applicaton.photosfrommars.data.dto

import com.applicaton.photosfrommars.entity.PhotoList
import com.google.gson.annotations.SerializedName

data class PhotoListDto(
    @SerializedName("photos")
    override val photos: List<PhotoDto>
    ) : PhotoList<PhotoDto>