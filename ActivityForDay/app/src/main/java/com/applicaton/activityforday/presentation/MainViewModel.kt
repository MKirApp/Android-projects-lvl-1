package com.applicaton.activityforday.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.applicaton.activityforday.domain.GetUsefulActivityUseCase
import com.applicaton.activityforday.entity.UsefulActivity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getUsefulActivityUseCase: GetUsefulActivityUseCase
) : ViewModel() {

    private var _response = MutableStateFlow<UsefulActivity?>(null)
    val response = _response.asStateFlow()
    private var _state = MutableStateFlow<State?>(null)
    val state = _state.asStateFlow()
    private var _channel = Channel<String>()
    val channel = _channel.receiveAsFlow()

    fun reloadUsefulActivity() {
        viewModelScope.launch {
            _state.value = State.Loading
            _response.value = getUsefulActivityUseCase.execute()
            if (_response.value == null) {
                _channel.send("Problem with server")
                _state.value = State.Error
            }
            else _state.value = State.Success
        }
    }

}