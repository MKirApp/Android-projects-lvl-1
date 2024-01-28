package com.applicaton.photosfrommars.presentation.mappers

import com.applicaton.photosfrommars.data.dto.RoverDto
import com.applicaton.photosfrommars.presentation.models.RoverModel

class RoverMapper {
    fun map(roverDto: RoverDto): RoverModel =
        RoverModel(
            roverDto.id,
            roverDto.name,
            roverDto.landing_date,
            roverDto.launch_date,
            roverDto.status
        )
}