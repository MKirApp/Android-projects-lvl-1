package com.applicaton.attractions.entity

interface FeatureInfo<P: Point> {
    val xid: String
    val rate: String
    val kinds: String
    val otm: String
    val point: P
}