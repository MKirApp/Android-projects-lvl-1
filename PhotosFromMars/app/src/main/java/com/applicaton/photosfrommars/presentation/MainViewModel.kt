package com.applicaton.photosfrommars.presentation

import android.os.Parcelable
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import com.applicaton.photosfrommars.data.dto.PhotoDto
import com.applicaton.photosfrommars.domain.GetPhotoListUseCase
import com.applicaton.photosfrommars.presentation.mappers.PhotoMapper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch


class MainViewModel(
    getPhotoListUseCase: GetPhotoListUseCase,
    private val photoMapper: PhotoMapper
) : ViewModel() {

    private val _state = MutableStateFlow<Parcelable?>(null)
    val state = _state.asStateFlow()

    init {
        Log.d("TAG", "MVM init")
    }

    val photos = getPhotoListUseCase.execute().map {
            it.map { photo ->
                photoMapper.map(photo as PhotoDto)
            }
    }.cachedIn(viewModelScope)

    fun setStateRecycler(state: Parcelable?) {
        _state.value = state
    }

}