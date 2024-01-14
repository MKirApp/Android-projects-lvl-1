package com.applicaton.activityforday.data

import android.util.Log
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query
import javax.inject.Inject

private const val BASE_URL = "https://www.boredapi.com/"

class UsefulActivityDataSource @Inject constructor() {

    init {
        Log.d("Retrofit","Retrofit class")
    }

     object RetrofitInstance {
         init {
             Log.d("Retrofit","Retrofit object")
         }
        private val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

        val searchActivityApi: SearchActivityApi = retrofit.create(SearchActivityApi::class.java)
    }

    interface SearchActivityApi {
        @Headers(
            "Accept: application/json",
            "Content-type: application/json"
        )
        @GET("api/activity/")
        fun getActivityApi(@Query("limit") limit: Int = 1): Call<UsefulActivityDto>
    }

    fun getRetrofitInstance(): RetrofitInstance {
        return RetrofitInstance
    }
}