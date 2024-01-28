package com.applicaton.photosfrommars.data.dto

import com.applicaton.photosfrommars.entity.Rover
import com.google.gson.annotations.SerializedName

data class RoverDto(
    @SerializedName("id")
    override val id: Int,
    override val name: String,
    override val landing_date: String,
    override val launch_date: String,
    override val status: String
) : Rover {

    override fun toString(): String {
        return "Rover(id=$id, name=$name, landing_date=$landing_date, launch_date=$launch_date, status=$status)"
    }
}