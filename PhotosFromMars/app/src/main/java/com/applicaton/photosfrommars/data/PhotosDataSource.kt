package com.applicaton.photosfrommars.data

import com.applicaton.photosfrommars.data.dto.PhotoListDto
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface PhotosDataSource {

    @Headers("X-API-KEY:$api_key")
    @GET("api/v1/rovers/curiosity/photos?earth_date=2024-1-1")
    suspend fun getPhotosFromMars(@Query("page") page: Int) : PhotoListDto

    private companion object {
        private const val api_key = "X49Wj0OMRdgy0F00IjMcpqXA27SlA68ATjc8d29u"
    }
}

val retrofit = Retrofit
    .Builder()
    .client(OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor().also {
        it.level = HttpLoggingInterceptor.Level.BODY
    }).build()
    )
    .baseUrl("https://api.nasa.gov/mars-photos/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()
    .create(PhotosDataSource::class.java)