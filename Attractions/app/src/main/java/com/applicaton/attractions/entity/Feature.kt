package com.applicaton.attractions.entity

interface Feature<T: Properties, K: Geometry> {
    val type: String
    val id: String
    val geometry: K
    val properties: T
}