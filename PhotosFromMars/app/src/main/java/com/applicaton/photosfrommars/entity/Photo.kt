package com.applicaton.photosfrommars.entity

interface Photo<С: Camera, R: Rover> {
    val id: Int
    val sol: String
    val camera: С
    val img_src: String
    val earth_date: String
    val rover: R
}






