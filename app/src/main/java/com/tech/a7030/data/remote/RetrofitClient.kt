package com.tech.a7030.data.remote

import retrofit2.Retrofit
import android.R.attr.data
import com.tech.a7030.data.model.Example
import com.tech.a7030.data.model.Response
import retrofit2.Call
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import android.R.attr.data




class RetrofitClient {

    private var retrofit: Retrofit? = null
    val BASE_URL = "https://api.foursquare.com/v2/venues/explore"

    fun getClient(): Retrofit? {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit
    }
}

interface RetrofitInterface {
    @GET()
    fun getVenues(): Call<Example>


    // @GET("/posts")
    //fun getPosts(@Query("userId") userId: Int): Call<List<Post>>
}
