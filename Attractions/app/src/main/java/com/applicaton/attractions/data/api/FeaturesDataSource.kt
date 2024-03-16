package com.applicaton.attractions.data.api

import com.applicaton.attractions.data.api.dto.FeatureInfoDto
import com.applicaton.attractions.data.api.dto.FeaturesListDto
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

private const val BASE_URL = "https://api.opentripmap.com/0.1/"

interface FeaturesDataSource {
    @GET("ru/places/radius")
    suspend fun getPlacesList(
        @Query("radius") radius: Int = 1000,
        @Query("lon") lon: Double,
        @Query("lat") lat: Double,
        @Query("limit") limit: Int = 8,
        @Query("apikey") apikey: String = API_KEY
    ): FeaturesListDto

    @GET("ru/places/xid/{xid}")
    suspend fun getInfoPlace(
        @Path("xid") xid: String,
        @Query("apikey") apikey: String = API_KEY
    ) : FeatureInfoDto

    companion object {
        private const val API_KEY = "YOUR_API_KEY"
    }
}

val retrofit = Retrofit
    .Builder()
    .client(
        OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor().also {
            it.level = HttpLoggingInterceptor.Level.BODY
        }).build()
    )
    .baseUrl(BASE_URL)
    .addConverterFactory(MoshiConverterFactory.create())
    .build()
    .create(FeaturesDataSource::class.java)
