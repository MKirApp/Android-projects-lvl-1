package com.applicaton.activityforday.domain

import com.applicaton.activityforday.data.UsefulActivitiesRepository
import com.applicaton.activityforday.entity.UsefulActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetUsefulActivityUseCase @Inject constructor(private val usefulActivitiesRepository: UsefulActivitiesRepository) {
    suspend fun execute(): UsefulActivity? = withContext(Dispatchers.IO) {
        usefulActivitiesRepository.getUsefulActivity()
    }

}