package com.applicaton.attractions.presentation.maps

sealed class MapsState {
    object Success: MapsState()
    object Error: MapsState()
}
