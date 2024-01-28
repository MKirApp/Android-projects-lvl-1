package com.applicaton.photosfrommars.presentation.mappers

import com.applicaton.photosfrommars.data.dto.PhotoDto
import com.applicaton.photosfrommars.presentation.models.PhotoModel

class PhotoMapper(
    private val cameraMapper: CameraMapper,
    private val roverMapper: RoverMapper
) {
    fun map(photoDto: PhotoDto): PhotoModel =
        PhotoModel(
            photoDto.id,
            photoDto.sol,
            cameraMapper.map(photoDto.camera),
            photoDto.img_src,
            photoDto.earth_date,
            roverMapper.map(photoDto.rover)
        )
}