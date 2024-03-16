package com.applicaton.attractions.presentation.gallery.models

import com.applicaton.attractions.data.database.PhotoDbo

class PhotoMapper {
    fun mapToDbo(photoGallery: PhotoGallery): PhotoDbo {
        return PhotoDbo(
            id = photoGallery.id,
            uri = photoGallery.uri
        )
    }

    fun mapToGallery(photoDbo: PhotoDbo): PhotoGallery {
        return PhotoGallery(
            id = photoDbo.id,
            uri = photoDbo.uri,
            isSelected = false
        )
    }
}