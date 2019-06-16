package com.tech.foursquareApiTest.data.remote

import com.tech.foursquareApiTest.data.model.Example
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GetApiService {

    @GET("explore?")
    fun getVenues(
        @Query("ll") latlng: String,
        @Query("client_id") id: String,
        @Query("client_secret") secret: String,
        @Query("v") date: String
    ): Call<Example>

}


// @GET("/posts")
//fun getPosts(@Query("userId") userId: Int): Call<List<Post>>

