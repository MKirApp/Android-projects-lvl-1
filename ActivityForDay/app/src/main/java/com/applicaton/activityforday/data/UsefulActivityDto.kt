package com.applicaton.activityforday.data

import com.applicaton.activityforday.entity.UsefulActivity


data class UsefulActivityDto (
    override val activity: String,
    override val type: String,
    override val participants: Int,
    override val price: Double,
    override val link: String,
    override val key: String,
    override val accessibility: Double
) : UsefulActivity