package com.applicaton.attractions.presentation.gallery

import android.net.Uri
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.applicaton.attractions.data.database.PhotoDbo
import com.applicaton.attractions.domain.databaseUseCases.DeletePhotoUseCase
import com.applicaton.attractions.domain.databaseUseCases.GetAllPhotosUseCase
import com.applicaton.attractions.presentation.gallery.models.PhotoGallery
import com.applicaton.attractions.presentation.gallery.models.PhotoMapper
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.io.File

class GalleryViewModel(
    getAllPhotosUseCase: GetAllPhotosUseCase,
    private val deletePhotoUseCase: DeletePhotoUseCase,
    private val photoMapper: PhotoMapper
) : ViewModel() {


    val allPhotos = getAllPhotosUseCase.execute().map {
        it.map { photo ->
            photoMapper.mapToGallery(photo as PhotoDbo)
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = emptyList()
    )

    override fun onCleared() {
        super.onCleared()
        Log.d("Tag", "onCleared GalleryViewModel")
    }

    fun onDeleteButton(photoGallery: PhotoGallery) {
        viewModelScope.launch {
            deletePhotoUseCase.execute(
                photoMapper.mapToDbo(photoGallery)
            )
            val file = File(Uri.parse(photoGallery.uri).path!!)
            if (file.exists()) {
                file.delete()
            }

        }

    }

}
