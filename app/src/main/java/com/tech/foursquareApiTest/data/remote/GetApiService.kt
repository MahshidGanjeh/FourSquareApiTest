package com.tech.foursquareApiTest.data.remote

import com.tech.foursquareApiTest.data.model.Example
import retrofit2.Call
import retrofit2.http.GET

interface GetApiService {

    @GET()
    fun getVenues(): Call<Example>


    // @GET("/posts")
    //fun getPosts(@Query("userId") userId: Int): Call<List<Post>>
}
