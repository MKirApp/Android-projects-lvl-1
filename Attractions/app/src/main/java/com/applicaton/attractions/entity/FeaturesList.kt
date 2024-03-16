package com.applicaton.attractions.entity

interface FeaturesList<F: Feature<*,*>> {
    val type: String
    val features: List<F>
}