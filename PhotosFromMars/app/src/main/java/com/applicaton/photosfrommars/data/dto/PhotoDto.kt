package com.applicaton.photosfrommars.data.dto

import com.applicaton.photosfrommars.entity.Camera
import com.applicaton.photosfrommars.entity.Photo
import com.applicaton.photosfrommars.entity.Rover

data class PhotoDto(
    override val id: Int,
    override val sol: String,
    override val camera: CameraDto,
    override val img_src: String,
    override val earth_date: String,
    override val rover: RoverDto
): Photo<CameraDto,RoverDto>
