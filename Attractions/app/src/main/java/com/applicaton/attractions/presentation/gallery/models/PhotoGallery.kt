package com.applicaton.attractions.presentation.gallery.models

import com.applicaton.attractions.entity.Photo

data class PhotoGallery(
    val id: Int? = null,
    var uri: String,
    var isSelected: Boolean = false
)
