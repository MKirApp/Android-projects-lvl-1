package com.applicaton.photosfrommars.presentation.models

data class PhotoModel(
    val id: Int,
    val sol: String,
    val camera: CameraModel,
    val img_src: String,
    val earth_date: String,
    val rover: RoverModel,
)



