package com.applicaton.photosfrommars.entity

interface PhotoList<T: Photo<*,*>> {
    val photos: List<T>
}

