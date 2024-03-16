package com.applicaton.attractions.presentation.maps.mappers

import com.applicaton.attractions.data.api.dto.PropertiesDto
import com.applicaton.attractions.presentation.maps.models.PropertiesMaps

class PropertiesMapper {
    fun mapToMaps(propertiesDto: PropertiesDto): PropertiesMaps {
        return PropertiesMaps(
            xid = propertiesDto.xid,
            name = propertiesDto.name,
            dist = propertiesDto.dist,
            rate = propertiesDto.rate,
            osm = propertiesDto.osm,
            kinds = propertiesDto.kinds
        )
    }
}