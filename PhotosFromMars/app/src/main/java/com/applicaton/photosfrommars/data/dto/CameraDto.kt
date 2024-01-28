package com.applicaton.photosfrommars.data.dto

import com.applicaton.photosfrommars.entity.Camera
import com.google.gson.annotations.SerializedName

data class CameraDto(
    @SerializedName("id")
    override val id: Int,
    @SerializedName("name")
    override val name: String,
    @SerializedName("rover_id")
    override val rover_id: Int,
    @SerializedName("full_name")
    override val full_name: String
) : Camera {
    override fun toString(): String {
        return "Camera(id=$id, name=$name, rover_id=$rover_id, launch_date=$full_name)"
    }
}