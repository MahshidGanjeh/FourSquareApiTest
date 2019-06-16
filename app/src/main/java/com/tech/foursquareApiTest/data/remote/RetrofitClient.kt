package com.tech.foursquareApiTest.data.remote

import retrofit2.Retrofit
import com.tech.foursquareApiTest.data.model.Example
import retrofit2.Call
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


public class RetrofitClient {

    private var retrofit: Retrofit? = null
    val BASE_URL = "https://api.foursquare.com/v2/venues/"

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


