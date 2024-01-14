package com.applicaton.activityforday.presentation

sealed class State {
    object Success : State()
    object Loading : State()
    object Error : State()
}