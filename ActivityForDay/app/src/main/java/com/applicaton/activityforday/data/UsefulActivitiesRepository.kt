package com.applicaton.activityforday.data

import com.applicaton.activityforday.entity.UsefulActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UsefulActivitiesRepository @Inject constructor(
    private val usefulActivityDataSource: UsefulActivityDataSource
) {
    suspend fun getUsefulActivity(): UsefulActivity? = withContext(Dispatchers.IO) {
        usefulActivityDataSource.getRetrofitInstance().searchActivityApi.getActivityApi().execute().body()
    }
}