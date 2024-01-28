package com.applicaton.photosfrommars.presentation.mappers

import com.applicaton.photosfrommars.data.dto.CameraDto
import com.applicaton.photosfrommars.presentation.models.CameraModel

class CameraMapper {
    fun map(cameraDto: CameraDto): CameraModel =
        CameraModel(
            cameraDto.id,
            cameraDto.name,
            cameraDto.rover_id,
            cameraDto.full_name
        )
}